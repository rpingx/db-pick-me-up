package db.pick.me.up.singleton;

import db.pick.me.up.json.ConfigInfo;
import db.pick.me.up.json.ConnectionInfo;
import lombok.Getter;
import lombok.ToString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

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
        } catch (Exception e) {
            System.out.println("E: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
