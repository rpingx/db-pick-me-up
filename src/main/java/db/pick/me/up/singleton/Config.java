package db.pick.me.up.singleton;

import db.pick.me.up.helper.TransWrapper;
import db.pick.me.up.json.ConfigInfo;
import db.pick.me.up.json.ConnectionInfo;
import lombok.Getter;
import lombok.ToString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 5/7/2019.
 */
@ToString
public class Config {
    private static volatile Config instance;
    private static Object mutex = new Object();

    @Getter
    private ConnectionInfo connectionInfo;
    @Getter
    private ConfigInfo configInfo;
    @Getter
    private JSONArray transformations;

    private Map<String, TransWrapper> transMap = new HashMap<>();

    private Config() {
    }

    public static Config getInstance() {
        Config result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new Config();
            }
        }
        return result;
    }

    public void init(String path) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            JSONObject infoObj = (JSONObject) parser.parse(reader);

            this.connectionInfo = new ConnectionInfo((JSONObject) infoObj.get("connection"));
            this.configInfo = new ConfigInfo((JSONObject) infoObj.get("config"));
            this.transformations = (JSONArray) infoObj.get("transformations");

            for (Object transformation : this.transformations) {
                JSONObject transform = (JSONObject) transformation;

                this.transMap.put((String) transform.get("field"), new TransWrapper(transform));
            }
        } catch (Exception e) {
            System.err.println("E: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public TransWrapper getTransform(String field) {
        if (this.transMap.keySet().contains(field)) {
            return this.transMap.get(field);
        } else {

                System.out.println("No transformation defined for: " + field);
                System.out.println("Use default(" + this.configInfo.getDefaultAction() + ") behaviour.");



            JSONObject obj = new JSONObject();
            obj.put("field", field);
            obj.put("method", this.configInfo.getDefaultAction().getValue());

            TransWrapper wrap = new TransWrapper(obj);
            this.transMap.put(field, wrap);

            return wrap;
        }
    }
}
