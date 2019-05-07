package db.pick.me.up.impl;

import db.pick.me.up.helper.DataBundle;
import db.pick.me.up.helper.TransWrapper;
import db.pick.me.up.interfaces.Masker;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by admin on 5/7/2019.
 */
@RequiredArgsConstructor
public class FloatingMasker implements Masker {
    private final Double minValue;
    private final Double maxValue;

    @Override
    public String mask(Object value, TransWrapper _t, Map<String, Object> row) {
        String method = _t.getMethod().toLowerCase();
        if ("random".equals(method)) {
            return DataBundle.textifyObject(
                    getRandom(
                            -1000000,
                            1000000));
        } else if ("influence".equals(method)) {
            Float holderVal = (Float) value;
            if (holderVal != null) {
                int digits = (int) Math.log10(holderVal);

                double delta = Math.pow(10, digits);

                return DataBundle.textifyObject(
                        getRandom(
                                holderVal - delta,
                                holderVal + delta
                        ));
            }
        }

        return DataBundle.textifyObject(value);
    }

    private static double getRandom(double min, double max) {
        return min + Math.random() * (max - min);
    }
}
