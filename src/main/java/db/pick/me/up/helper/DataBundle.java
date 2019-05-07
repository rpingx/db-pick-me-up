package db.pick.me.up.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by admin on 5/7/2019.
 */
public class DataBundle {

    public static void scrambleField(List<Map<String, Object>> dataBundle, String field) {
        List<Object> fieldList = new ArrayList<>();
        for (Map<String, Object> row : dataBundle) {
            fieldList.add(row.get(field));
        }

        List<Object> newList = new ArrayList<>();

        while (fieldList.size() > 0) {
            int ind = ThreadLocalRandom.current()
                    .nextInt(fieldList.size());

            newList.add(fieldList.remove(ind));
        }

        for (Map<String, Object> row : dataBundle) {
            row.put(field, newList.remove(0));
        }
    }
}
