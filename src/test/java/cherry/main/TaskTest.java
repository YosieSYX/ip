package cherry.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
