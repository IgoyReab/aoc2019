package aoc.helper;

import org.junit.Test;

import static aoc.helper.StringHelper.countChars;
import static org.junit.Assert.assertEquals;

public class StringHelperTest {

    @Test
    public void countCharsTest() {
        String inputString = "abbcccddddeeeee";
        int result;

        result = countChars(inputString, 'a');
        assertEquals(1, result);

        result = countChars(inputString, 'b');
        assertEquals(2, result);

        result = countChars(inputString, 'c');
        assertEquals(3, result);

        result = countChars(inputString, 'd');
        assertEquals(4, result);

        result = countChars(inputString, 'e');
        assertEquals(5, result);
    }
}
