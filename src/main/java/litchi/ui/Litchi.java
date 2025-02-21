package litchi.ui;

import litchi.exception.LitchiException;
import litchi.task.Deadline;
import litchi.task.Event;
import litchi.task.Task;
import litchi.task.Todo;
import litchi.storage.Storage;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Litchi {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int taskNum = 0;
    private final static String indentations = "_____________________________________________";
    private static final String FILE_PATH = "./data/litchi.txt";
    static Storage storage = new Storage(FILE_PATH);

    public static void main(String[] args) throws IOException {

        loadTasks();
        printHello();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String in = scanner.nextLine().trim();

                if (in.equals("bye")) {
                    printBye();
                    break;
                } else if (in.equals("list")) {
                    printTaskList();
                } else if (in.startsWith("mark")) {
                    markTask(in);
                } else if (in.startsWith("unmark")) {
                    unmarkTask(in);
                } else if (in.startsWith("todo")) {
                    addTodo(in);
                } else if (in.startsWith("deadline")) {
                    addDeadline(in);
                } else if (in.startsWith("event")) {
                    addEvent(in);
                } else if (in.startsWith("delete")) {
                    deleteTask(in);
                } else {
                    throw new LitchiException();
                }
            } catch (LitchiException e) {
                printError(e.getMessage());
            } catch (NumberFormatException e) {
                printError("Invalid number format. Please enter a valid task number.");
            } catch (Exception e) {
                printError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public static void loadTasks() throws IOException {
        Task[] loadedTasks = storage.loadTasks();
        for (Task task : loadedTasks) {
            if (taskNum < maxTaskNums) {
                tasks[taskNum] = task;
                taskNum++;
            }
        }
    }

    private static void saveTasks() throws IOException {
        storage.saveTasks(tasks, taskNum);
    }

    public static void printError(String message) {
        System.out.println(indentations);
        System.out.println(message);
        System.out.println(indentations);
    }

    public static void printHello() {
        System.out.println(indentations);
        System.out.println("Hello! I'm Litchi");
        System.out.println("What can I do for you?");
        System.out.println(indentations);
    }

    public static void printBye() {
        System.out.println(indentations);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(indentations);
    }

    public static void printTaskList() {
        System.out.println(indentations);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNum; i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(indentations);
    }

    public static void markTask(String in) throws LitchiException {
        int index = Integer.parseInt(in.substring(5)) - 1;
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }

        tasks.get(index).markAsDone();
        saveTasks();
        System.out.println(indentations);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
        System.out.println(indentations);
    }

    public static void unmarkTask(String in) throws LitchiException, IOException {
        int index = Integer.parseInt(in.substring(7)) - 1;
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }

        tasks.get(index).markAsNotDone();
        saveTasks();
        System.out.println(indentations);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index).toString());
        System.out.println(indentations);
    }

    public static void addTask(Task task) throws LitchiException, IOException {
        tasks.add(task);
        taskNum++;
        saveTasks();
        System.out.println(indentations);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskNum +
                " task" + (taskNum == 1 ? "" : "s") + " in the list.");
        System.out.println(indentations);
    }

    public static void addTodo(String in) throws LitchiException, IOException {
        String description = in.substring(4).trim();
        if (description.isEmpty()) {
            throw new LitchiException("The description of a todo cannot be empty.");
        }

        Task newTodo = new Todo(description);
        addTask(newTodo);
    }

    public static void addDeadline(String in) throws LitchiException, IOException {
        int begin = 9;
        int end = in.indexOf("/by");
        if (end == -1) {
            throw new LitchiException("The deadline is missing! " +
                    "Please use: deadline <task> /by <date>.");
        }

        String description = in.substring(begin, end).trim();
        String deadline = in.substring(end + 3).trim();
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new LitchiException("The description or the deadline cannot be empty.");
        }

        Task newDeadline = new Deadline(description, deadline);
        addTask(newDeadline);
    }

    public static void addEvent(String in) throws LitchiException, IOException {
        int begin = 6;
        int from = in.indexOf("/from");
        int to = in.indexOf("/to");
        if (from == -1 || to == -1) {
            throw new LitchiException("The event format is incorrect! " +
                    "Please use: event <event> /from <date> /to <date>.");
        }

        String description = in.substring(begin, from).trim();
        String fromTime = in.substring(from + 5, to).trim();
        String toTime = in.substring(to + 3).trim();
        if (description.isEmpty() || fromTime.isEmpty() || toTime.isEmpty()) {
            throw new LitchiException("The description or time values for event cannot be empty.");
        }

        Task newEvent = new Event(description, fromTime, toTime);
        addTask(newEvent);
    }

    public static void deleteTask(String in) throws LitchiException {
        int index = Integer.parseInt(in.substring(7)) - 1;
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }

        System.out.println(indentations);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(index).toString());
        System.out.println("Now you have " + taskNum +
                " task" + (taskNum == 1 ? "" : "s") + " in the list.");
        System.out.println(indentations);

        tasks.remove(index);
        taskNum--;
    }
}
