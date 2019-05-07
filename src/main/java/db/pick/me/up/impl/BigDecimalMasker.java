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
public class BigDecimalMasker implements Masker {
    private final BigDecimal minValue;
    private final BigDecimal maxValue;
    private final Integer decimalPrecision;
    private final Integer integerPrecision;

    @Override
    public String mask(Object value, TransWrapper _t, Map<String, Object> row) {
        String method = _t.getMethod().toLowerCase();
        if ("random".equals(method)) {
            return DataBundle.textifyObject(
                    BigDecimalMasker.getRandom(
                            new BigDecimal(-1000000),
                            new BigDecimal(1000000),
                            10));
        } else if ("influence".equals(method)) {
            BigDecimal holderVal = (BigDecimal) value;
            int digits = (int) Math.log10(holderVal.floatValue());

            double delta = Math.pow(10, digits);

            return DataBundle.textifyObject(
                    BigDecimalMasker.getRandom(
                            holderVal.subtract(BigDecimal.valueOf(delta)),
                            holderVal.add(BigDecimal.valueOf(delta)),
                            holderVal.scale()));
        }


        return DataBundle.textifyObject(value);
    }

    private static BigDecimal getRandom(BigDecimal min, BigDecimal max, int scale) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
}
