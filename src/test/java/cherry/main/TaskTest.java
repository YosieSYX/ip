package cherry.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task("Test");
        assertEquals("Test", task.description);
        assertFalse(task.isDone);
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Test");
        task.markAsDone();
        assertTrue(task.isDone);
        assertEquals("X", task.getStatusIcon());
    }
}