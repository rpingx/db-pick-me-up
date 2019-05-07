package db.pick.me.up;

import db.pick.me.up.helper.DataAccess;
import db.pick.me.up.helper.TransWrapper;
import db.pick.me.up.singleton.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 5/5/2019.
 */
public class Start {
    private static final int MAX_BUNDLE_SIZE = 25;
    private static final int MAX_THREAD = 5;

    public static void main(String[] args) {
        System.out.println("path: " + args[0]);

        Config config = Config.getInstance();
        config.init(args[0]);

        Integer total = DataAccess.getTotal();

        HashMap<String, String> dataTypeMap = DataAccess.getDataTypeMap();

        System.out.println(total);
        System.out.println(dataTypeMap);

        try {
            Connection conn = DataAccess.getConn();

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + config.getConnectionInfo().getTable());

            ResultSetMetaData rsmd = rs.getMetaData();

            List<Map<String, Object>> buffer = new ArrayList<>();
            ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD);

            List<String> hideList = null;

            while (rs.next()) {
                //  Creating hideList to stop adding certain columns
                //  Only want to do this once to stop repeating expensive actions
                if (hideList == null) {
                    hideList = new ArrayList<>();

                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String field = rsmd.getColumnLabel(i);
                        TransWrapper _t = config.getTransform(field);
                        if ("hide".equals(_t.getMethod())) {
                            hideList.add(field);
                        }
                    }
                }

                Map<String, Object> rowMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnLabel(i);
                    if (!hideList.contains(field)) {
                        rowMap.put(field, rs.getObject(i));
                    }
                }

                buffer.add(rowMap);

                if (buffer.size() >= MAX_BUNDLE_SIZE) {
                    pool.execute(new WorkerRunnable(dataTypeMap, buffer));
                    Thread.yield();
                    buffer = new ArrayList<>();
                }
            }

            //flush whatever is left
            pool.execute(new WorkerRunnable(dataTypeMap, buffer));

            pool.shutdown();
            rs.close();
         //   conn.close();
        } catch (SQLException e) {
            System.err.println("SQLException is main call: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
