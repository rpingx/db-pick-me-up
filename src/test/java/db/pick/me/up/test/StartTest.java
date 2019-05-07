package db.pick.me.up.test;

import db.pick.me.up.Start;
import org.junit.Test;

/**
 * Created by admin on 5/5/2019.
 */
public class StartTest {

    @Test
    public void testMain() {
        String[] args = {"src/test/resources/config-test3.json"};
        Start.main(args);
    }
}
