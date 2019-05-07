package db.pick.me.up;

import db.pick.me.up.helper.DataBundle;
import db.pick.me.up.helper.TransWrapper;
import db.pick.me.up.singleton.Config;
import org.json.simple.JSONArray;

import java.util.ArrayList;
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
        List<String> unlinkedList = new ArrayList<>();

        //  Checking for unlinked fields
        //  Unlinked in this context means the field being tested is not tightly coupled with another field
        for (String field : dataBundle.get(0).keySet()) {
            TransWrapper _t = new TransWrapper(config.getTransform(field));

            if (_t.isUnlinked()) {
                unlinkedList.add(field);
            }
        }

        //  Since the field isn't tightly coupled, no reason to keep its order relative to other fields.
        //  Scramble it up.
        for (String field : unlinkedList) {
            DataBundle.scrambleField(dataBundle, field);
        }
    }
}
