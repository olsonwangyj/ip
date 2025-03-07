package litchi.ui;

import litchi.exception.LitchiException;
import litchi.task.Task;
import litchi.task.TaskList;
import litchi.storage.Storage;

import java.io.IOException;

public class Litchi {

    private static Ui ui = new Ui();
    private static final String FILE_PATH = "./data/litchi.txt";
    private static Storage storage = new Storage(FILE_PATH);
    private static TaskList taskList;

    public static void main(String[] args) throws IOException {

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ui.showWelcome();

        while (true) {
            try {
                String in = ui.readCommand();

                if (in.equals("bye")) {
                    ui.showGoodbye();
                    break;
                } else if (in.equals("list")) {
                    ui.showTaskList(taskList);
                } else if (in.startsWith("mark")) {
                    handleMark(in);
                } else if (in.startsWith("unmark")) {
                    handleUnmark(in);
                } else if (in.startsWith("todo")) {
                    handleTodo(in);
                } else if (in.startsWith("deadline")) {
                    handleDeadline(in);
                } else if (in.startsWith("event")) {
                    handleEvent(in);
                } else if (in.startsWith("delete")) {
                    handleDelete(in);
                } else {
                    throw new LitchiException();
                }
            } catch (LitchiException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Invalid number format. Please enter a valid task number.");
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void handleMark(String in) throws LitchiException, IOException {
        int index = Parser.parseIndex(in, 5);
        taskList.markTaskAsDone(index);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskMarkedAsDone(taskList.getTask(index));
    }

    private static void handleUnmark(String in) throws LitchiException, IOException {
        int index = Parser.parseIndex(in, 7);
        taskList.markTaskAsNotDone(index);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskMarkedAsDone(taskList.getTask(index));
    }

    private static void handleTodo(String in) throws LitchiException, IOException {
        Task todo = Parser.parseTodoCommand(in);
        taskList.addTask(todo);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskAdded(todo, taskList.getTaskCount());
    }

    private static void handleDeadline(String in) throws LitchiException, IOException {
        Task deadline = Parser.parseDeadlineCommand(in);
        taskList.addTask(deadline);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskAdded(deadline, taskList.getTaskCount());
    }

    private static void handleEvent(String in) throws LitchiException, IOException {
        Task deadline = Parser.parseEventCommand(in);
        taskList.addTask(deadline);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskAdded(deadline, taskList.getTaskCount());
    }

    private static void handleDelete(String in) throws LitchiException, IOException {
        int index = Parser.parseIndex(in, 7);
        Task taskToDelete = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskDeleted(taskToDelete, taskList.getTaskCount());
    }
}
