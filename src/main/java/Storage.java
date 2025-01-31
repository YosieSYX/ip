import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        try {
            if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
                System.err.println("Failed to create directory: " + parentDir.getAbsolutePath());
            }

            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("New task file created: " + filePath);
                } else {
                    System.err.println("Failed to create file: " + filePath);
                }
                return tasks;
            }
        } catch (IOException e) {
            System.err.println("Error while initializing storage" );
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = convert(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + filePath);
        }
        return tasks;
    }


    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                String[] parts = task.toString().split("] ", 2);
                char taskType = parts[0].charAt(1);
                int isDone = parts[0].contains("X") ? 1 : 0;
                String taskDescription = parts[1];
                String output = taskType + " " + isDone + " " + taskDescription;
                bw.write(output);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
        }
    }

    private Task convert(String string) {
        Task taskToAdd;
        String[] parts = string.split(getString() + " ", 3);
        char taskType = parts[0].charAt(0);
        String integer = parts[1];
        String taskDescription = parts[2];
        switch (taskType) {
        case 'E':
            String[] parts1 = taskDescription.split(" /from ");
            String start = parts1[1].split(" /to ")[0];
            String[] parts2 = taskDescription.split(" /to ");
            String end = parts2[1];
            taskToAdd = new Events(taskDescription, start, end);
            break;
        case 'D':
            String[] timePre = string.split(" /by ");
            String time = timePre[1];
            taskToAdd = new Deadline(taskDescription, time);
            break;
        default:
            taskToAdd = new ToDos(taskDescription);
            break;
        }
        if(integer.equals("1")){
            taskToAdd.markAsDone();
        }
        return taskToAdd;
    }

    private static String getString() {
        return "";
    }
}
