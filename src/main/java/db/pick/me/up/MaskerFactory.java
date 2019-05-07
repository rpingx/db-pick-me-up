package db.pick.me.up;

import db.pick.me.up.helper.TransWrapper;
import db.pick.me.up.impl.PassthruMasker;
import db.pick.me.up.interfaces.Masker;
import db.pick.me.up.json.ConfigInfo;
import db.pick.me.up.json.ConnectionInfo;
import db.pick.me.up.singleton.Config;

/**
 * Created by admin on 5/7/2019.
 */
public class MaskerFactory {
    private static Config config = Config.getInstance();

    public static Masker getMasker(ConnectionInfo.DatabaseType type, String datatype, TransWrapper _t) {
        if ("passthru".equals(_t.getMethod().toLowerCase())) {
            return new PassthruMasker();
        }

        System.out.println("No masker implemented for these parameters. Using default("
                + config.getConfigInfo().getDefaultAction() + ") behaviour.");

        if (config.getConfigInfo().getDefaultAction() == ConfigInfo.DefaultAction.hide) {
            return null;
        } else {
            return new PassthruMasker();
        }
    }
}
