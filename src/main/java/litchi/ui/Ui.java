package litchi.ui;

import litchi.task.Task;
import litchi.task.TaskList;

import java.util.Scanner;

/**
 * The user interface, print messages.
 */
public class Ui {
    private final Scanner scanner;
    private final static String indentations = "_____________________________________________";

    /**
     * The constructor of Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Read the user's command.
     * @return The word of user input.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Print the welcome message.
     */
    public void showWelcome() {
        System.out.println(indentations);
        System.out.println("Hello! I'm Litchi");
        System.out.println("What can I do for you?");
        System.out.println(indentations);
    }

    /**
     * Print bye message.
     */
    public void showGoodbye() {
        System.out.println(indentations);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(indentations);
    }

    /**
     * Print error message.
     * @param message Message will be printed.
     */
    public void showError(String message) {
        System.out.println(indentations);
        System.out.println(message);
        System.out.println(indentations);
    }

    /**
     * Print the tasklist
     * @param taskList The tasklist will be printed.
     */
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

    /**
     * Print message after the task is marked.
     * @param task The task be marked.
     */
    public void showTaskMarkedAsDone(Task task) {
        System.out.println(indentations);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(indentations);
    }

    /**
     * Print message after the task is unmark.
     * @param task The task be unmarked.
     */
    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println(indentations);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(indentations);
    }

    /**
     * Print message after the task be added.
     * @param task The task be added.
     * @param taskCount The number of tasks currently.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(indentations);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskCount +
                " task" + (taskCount == 1 ? "" : "s") + " in the list.");
        System.out.println(indentations);
    }

    /**
     * Print message after the task be deleted.
     * @param task The task be deleted.
     * @param taskCount The number of tasks currently.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println(indentations);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskCount +
                " task" + (taskCount == 1 ? "" : "s") + " in the list.");
        System.out.println(indentations);
    }

    /**
     * Print message of the tasks be found.
     * @param taskList The task be found.
     */
    public void showFindTask(TaskList taskList) {
        System.out.println(indentations);

        if (taskList.getTaskCount() == 0) {
            System.out.println("No tasks found.");
            System.out.println(indentations);
            return;
        }

        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < taskList.getTaskCount(); i++) {
            try {
                System.out.println((i + 1) + "." + taskList.getTask(i).toString());
            } catch (Exception e) {
                System.out.println("Error displaying task " + (i + 1) + ": " + e.getMessage());
            }
        }

        System.out.println(indentations);
    }
}
