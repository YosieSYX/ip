package cherry.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseInt() {
        Parser parser = new Parser();
        assertEquals(1, parser.parseInt("mark 1"));
        assertThrows(NumberFormatException.class, () -> parser.parseInt("mark abc"));
    }

    @Test
    public void testParseDeadline() {
        Parser parser = new Parser();
        String[] result = parser.parseDeadline("deadline submit report /by 2026-01-20");
        assertEquals(2, result.length);
        assertEquals("deadline submit report", result[0]);
        assertEquals("2026-01-20", result[1]);
    }

    @Test
    public void testParseEvents() {
        Parser parser = new Parser();
        String[] result = parser.parseEvents("event meeting /from 2026-10-10 /to 2026-10-11");
        assertEquals(3, result.length);
        assertEquals("event meeting ", result[0]);
        assertEquals("2026-10-10 ", result[1]);
        assertEquals("2026-10-11", result[2]);
    }
}