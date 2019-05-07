package db.pick.me.up.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;

/**
 * Created by admin on 5/7/2019.
 */

@Data
@RequiredArgsConstructor
public class ConfigInfo {
    @AllArgsConstructor
    public enum DefaultAction {
        passthru("passthru"),
        hide("hide");

        @Getter
        private String value;
    }


    public ConfigInfo(JSONObject json) {
        String defaultAction;

        if (json == null) {
            defaultAction = "hide";

        } else {
            defaultAction =
                    (String) json.get("defaultAction");
            if (defaultAction == null) {
                defaultAction = "hide";
            }
        }
        this.defaultAction = DefaultAction.valueOf(defaultAction);
    }

    private final DefaultAction defaultAction;
}
