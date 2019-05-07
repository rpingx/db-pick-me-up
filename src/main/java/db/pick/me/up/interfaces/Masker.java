package db.pick.me.up.interfaces;

import db.pick.me.up.helper.TransWrapper;

import java.util.Map;

/**
 * Created by admin on 5/7/2019.
 */
public interface Masker {
    String mask(Object value, TransWrapper _t, Map<String, Object> row);
}
