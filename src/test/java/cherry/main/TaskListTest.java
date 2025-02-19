package cherry.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskListTest {
    private TaskList taskList = new TaskList();

    @Test
    void testAddTask() {
        Task task = new Task("todo read book /by Sunday");
        taskList.addTask(task);
        assertEquals(1, taskList.count());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void testRemoveTask() {
        Task task = new Task("deadline read book /by Sunday");
        taskList.addTask(task);
        taskList.removeTask(0);
        assertEquals(0, taskList.count());
    }

    @Test
    void testRemoveTaskInvalidIndex() {
        taskList.removeTask(0);
        assertEquals(0, taskList.count());
    }

    @Test
    void testFindTasks() {
        taskList.addTask(new Task("todo CS2103T assignment /by 2025-02-22"));
        taskList.addTask(new Task("deadline CS2109S assignment /by 2025-02-22"));
        taskList.addTask(new Task("deadline CS2109S problem set /by 2025-02-22"));

        String result = taskList.findTasks("assignment");
        assertTrue(result.contains("todo CS2103T assignment /by 2025-02-22"));
        assertTrue(result.contains("deadline CS2109S assignment /by 2025-02-22"));
        assertFalse(result.contains("deadline CS2109S problem set /by 2025-02-22"));
    }

    @Test
    void testFindTasksNoMatch() {
        taskList.addTask(new Task("todo CS2103T assignment /by 2025-02-22"));
        taskList.addTask(new Task("deadline CS2109S assignment /by 2025-02-22"));

        String result = taskList.findTasks("problem set");
        assertEquals("Sorry, no matching tasks found for the given keywords.", result);
    }
}

