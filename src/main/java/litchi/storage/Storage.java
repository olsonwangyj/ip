package litchi.storage;

import java.io.*;
import java.util.*;
import litchi.task.*;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
