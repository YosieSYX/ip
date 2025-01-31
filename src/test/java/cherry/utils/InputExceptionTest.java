package cherry.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputExceptionTest {

    @Test
    public void testInputExceptionMessage() {
        String errorMessage = "This is an error message";
        InputException exception = new InputException(errorMessage);
        assertEquals(errorMessage, exception.toString());
    }
}