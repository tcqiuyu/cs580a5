package a1.yqiu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
/**
 * Created by Qiu on 3/7/16.
 */
public class MidTermTest {

    MidTerm instance;

    @Before
    public void setUp() {
        instance = new MidTerm();
    }

    @Test
    public void testA1() {
        int[] x={};
        System.out.println(x.length);
        int y = 0;
        int output = instance.findLast(x, y);
        assertEquals(output, -1);
    }
}
