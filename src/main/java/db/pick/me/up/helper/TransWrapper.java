package db.pick.me.up.helper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;

/**
 * Created by admin on 5/7/2019.
 */
@Data
@RequiredArgsConstructor
public class TransWrapper {
    private final JSONObject obj;

    public boolean isUnlinked() {
        Boolean isUnlinked = getBoolean("unlinked");
        return isUnlinked != null ? isUnlinked : false;
    }

    public String getField() {
        return getString("field");
    }

    public String getMethod() {
        return getString("method");
    }

    public Boolean getBoolean(String field) {
        return (Boolean) obj.get(field);
    }

    public String getString(String field) {
        return (String) obj.get(field);
    }

    public Integer getInteger(String field) {
        Long val = (Long) obj.get("field");
        return val != null ? val.intValue() : null;
    }

    public Double getDouble(String field) {
        Number val = (Number) obj.get("field");
        return val != null ? val.doubleValue() : null;
    }
}
