package db.pick.me.up.helper;

import db.pick.me.up.singleton.Config;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by admin on 5/7/2019.
 */
public class DataAccess {
    private static Config config = Config.getInstance();

    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(
                config.getConnectionInfo().getConnectionString(),
                config.getConnectionInfo().getUser(),
                config.getConnectionInfo().getPass()
        );
    }

    public static Integer getTotal() {
        Integer result = null;

        try {
            Connection conn = getConn();

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT count(*) AS count FROM " + config.getConnectionInfo().getTable());

            if (rs.next()) {
                result = rs.getInt(1);
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("SQLException in getTotal: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public static HashMap<String, String> getDataTypeMap() {
        HashMap<String, String> result = new HashMap<>();

        try {
            Connection conn = getConn();

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select column_name, data_type from information_schema.columns where table_name = '"
                    + config.getConnectionInfo().getTable() + "'");


            while (rs.next()) {
                result.put(rs.getString(1), rs.getString(2));
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("SQLException in getDataTypeMap: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }
}
