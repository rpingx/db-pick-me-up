package db.pick.me.up.impl;

import db.pick.me.up.helper.TransWrapper;
import db.pick.me.up.interfaces.Masker;

import java.util.Map;

/**
 * Created by admin on 5/7/2019.
 */
public class StringMasker implements Masker {
    @Override
    public String mask(Object value, TransWrapper _t, Map<String, Object> row) {
        return null;
    }
}
