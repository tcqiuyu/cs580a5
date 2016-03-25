package a1.yqiu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Qiu on 1/20/16.
 */

public class RomanTest {

    private Roman roman;

    @Before
    public void setUp() {
        roman = new Roman();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRomanToDecimal() throws InvalidNumberException {
        assertEquals(578, roman.toDecimal("DLXXVIII"));
        assertEquals(1984, roman.toDecimal("MCMLXXXIV"));
        assertEquals(3999, roman.toDecimal("MMMCMXCIX"));
//        assertEquals(1, roman.toDecimal(null));

        assertEquals(1984, roman.toDecimal("mcmlxxxiv"));

        boolean rule1;
        boolean rule2;
        boolean rule3;
        boolean rule4;

        try {
            roman.toDecimal("IIII");
            rule1 = false;
        } catch (InvalidNumberException e) {
            rule1 = true;
        }

        try {
            roman.toDecimal("IIIIV");
            rule1 = false;
        } catch (InvalidNumberException e) {
            rule1 = true;
        }

        try {
            roman.toDecimal("XCX");
            rule2 = false;
        } catch (InvalidNumberException e) {
            rule2 = true;
        }


        try {
            roman.toDecimal("VX");
            rule3 = false;
        } catch (InvalidNumberException e) {
            rule3 = true;
        }

        try {
            roman.toDecimal("IC");
            rule4 = false;
        } catch (InvalidNumberException e) {
            rule4 = true;
        }

        assertTrue(rule1);
        assertTrue(rule2);
        assertTrue(rule3);
        assertTrue(rule4);
    }

    @Test
    public void testDecimalToRoman() throws InvalidNumberException {

        assertEquals("LXXXIV", roman.toRoman(84));
        assertEquals("MMDCCLI", roman.toRoman(2751));
        assertEquals("MCMLXXXIV", roman.toRoman(1984));
        assertEquals("MMMCMXCIX", roman.toRoman(3999));

        try {
            roman.toRoman(0);
            fail("Did not throw InvalidNumberException for input 0");
        } catch (InvalidNumberException e) {
        }

        try {
            roman.toRoman(4000);
            fail("Did not throw InvalidNumberException for input 4000");
        } catch (InvalidNumberException e) {
        }
    }

    @Test
    public void testToDecimal() throws Exception {

    }

    @Test
    public void testToRoman() throws Exception {

    }
}
