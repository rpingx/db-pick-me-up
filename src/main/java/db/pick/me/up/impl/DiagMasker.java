package db.pick.me.up.impl;

import db.pick.me.up.helper.DataBundle;
import db.pick.me.up.helper.TransWrapper;
import db.pick.me.up.interfaces.Masker;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by admin on 5/7/2019.
 */
public class DiagMasker implements Masker {
    @Override
    public String mask(Object value, TransWrapper _t, Map<String, Object> row) {
        if (value != null) {
            System.out.println("Class: " + value.getClass());
        } else {
            System.out.println("null");
        }
        //  This is not really much of a masker as it doesn't mask
        return DataBundle.textifyObject(value);
    }
}
