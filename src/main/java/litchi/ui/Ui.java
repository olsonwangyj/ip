package litchi.ui;

import litchi.task.Task;
import litchi.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private final static String indentations = "_____________________________________________";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showWelcome() {
        System.out.println(indentations);
        System.out.println("Hello! I'm Litchi");
        System.out.println("What can I do for you?");
        System.out.println(indentations);
    }

    public void showGoodbye() {
        System.out.println(indentations);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(indentations);
    }

    public void showError(String message) {
        System.out.println(indentations);
        System.out.println(message);
        System.out.println(indentations);
    }

    public void showTaskList(TaskList taskList) {
        System.out.println(indentations);
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < taskList.getTaskCount(); i++) {
            try {
                System.out.println((i + 1) + "." + taskList.getTask(i).toString());
            } catch (Exception e) {
                System.out.println("Error displaying task " + (i + 1) + ": " + e.getMessage());
            }
        }

        System.out.println(indentations);
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.println(indentations);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(indentations);
    }

    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println(indentations);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(indentations);
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(indentations);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskCount +
                " task" + (taskCount == 1 ? "" : "s") + " in the list.");
        System.out.println(indentations);
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(indentations);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskCount +
                " task" + (taskCount == 1 ? "" : "s") + " in the list.");
        System.out.println(indentations);
    }
}
