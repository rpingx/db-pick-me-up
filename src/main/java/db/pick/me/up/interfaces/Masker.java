package db.pick.me.up.interfaces;

import db.pick.me.up.helper.TransWrapper;

/**
 * Created by admin on 5/7/2019.
 */
public interface Masker {
    String mask(Object value, TransWrapper _t);
}
