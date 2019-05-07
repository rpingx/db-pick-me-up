package db.pick.me.up.json;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;


/**
 * Created by admin on 5/5/2019.
 */
@Data
@RequiredArgsConstructor
public class ConnectionInfo {
    @AllArgsConstructor
    public enum DatabaseType {
        PostgreSQL("PostgreSQL"),
        MySQL("MySQL");

        @Getter
        private String value;
    }

    public ConnectionInfo(JSONObject json) {
        this(
                DatabaseType.valueOf((String) json.get("type")),
                (String) json.get("host"),
                ((Long) json.get("port")).intValue(),
                (String) json.get("database"),
                (String) json.get("table"),
                (String) json.get("user"),
                (String) json.get("pass")
        );
    }

    private final DatabaseType type;
    private final String host;
    private final int port;
    private final String database;
    private final String table;
    private final String user;
    private final String pass;


    public String getConnectionString() {
        StringBuffer sb = new StringBuffer("jdbc:");
        sb
                .append(type.getValue().toLowerCase())
                .append("://")
                .append(host)
                .append(":")
                .append(port)
                .append("/")
                .append(database);
        return sb.toString();
    }
}