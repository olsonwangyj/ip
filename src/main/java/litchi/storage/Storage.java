package litchi.storage;

import java.io.*;
import java.util.*;
import litchi.task.*;

/**
 * Store or load the data file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor of the Stroage
     * @param filePath The path of the file be loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new file for storing tasks if it does not exist.
     *
     * @throws IOException If an error occurs while creating the file.
     */
    public void createNewFile() throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parses a line from the file and converts it into a Task object.
     *
     * @param line The line containing task details in the file.
     * @return A Task object created from the line, or null if the format is invalid.
     */
    public Task parseTask(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    return null;
                }
                task = new Deadline(description, parts[3].trim());
                break;
            case "E":
                if (parts.length < 5) return null;
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                return null;
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Loads tasks from the file and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        final File file = new File(filePath);
        if (!file.exists()) {
            createNewFile();
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(formatTask(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    /**
     * Formats a Task object into a string suitable for file storage.
     *
     * @param task The task to be formatted.
     * @return A formatted string representation of the task.
     */
    private String formatTask(Task task) {
        String type = task instanceof Todo ? "T" :
                      task instanceof Deadline ? "D" :
                      task instanceof Event ? "E" : "?";
        String status = task.isDone() ? "1" : "0";

        if (task instanceof Deadline) {
            return String.format("%s | %s | %s | %s", type, status, task.getDescription(),
                    ((Deadline) task).getBy());
        } else if (task instanceof Event) {
            return String.format("%s | %s | %s | %s | %s", type, status, task.getDescription(),
                    ((Event) task).getFrom(), ((Event) task).getTo());
        } else {
            return String.format("%s | %s | %s", type, status, task.getDescription());
        }
    }
}
