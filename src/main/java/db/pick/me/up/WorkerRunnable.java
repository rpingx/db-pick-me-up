package db.pick.me.up;

import db.pick.me.up.singleton.Config;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 5/7/2019.
 */
public class WorkerRunnable implements Runnable {
    private List<Map<String, Object>> dataBundle;
    private Map<String, String> dataTypeMap;

    private Config config = Config.getInstance();

    public WorkerRunnable(Map<String, String> dataTypeMap, List<Map<String, Object>> bundle) {
        this.dataBundle = bundle;
        this.dataTypeMap = dataTypeMap;
    }

    @Override
    public void run() {
        System.out.println(dataBundle.size());
    }
}
