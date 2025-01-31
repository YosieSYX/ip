package cherry.utils;

import cherry.main.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            System.err.println("Error while initializing storage");
            return tasks;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            tasks = (ArrayList<Task>) ois.readObject();
            System.out.println("Tasks loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + filePath);
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
        }
    }
}