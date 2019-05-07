package db.pick.me.up;

import db.pick.me.up.helper.DataBundle;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 5/7/2019.
 */
public class textTest {
    @Test
    public void testLoadConfig1() throws Exception {
        assertEquals(DataBundle.textifyObject("test"), "\"test\"");
        assertEquals(DataBundle.textifyObject("te\"st"), "\"te\"\"st\"");
    }
}
