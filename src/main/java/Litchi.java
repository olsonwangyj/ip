import java.util.Scanner;

public class Litchi {
    private final static int maxTaskNums = 100;
    private static Task[] tasks = new Task[maxTaskNums];
    private static int taskNum = 0;
    private final static String indentations = "_____________________________________________";

    public static void main(String[] args) {
        System.out.println(indentations);
        System.out.println("Hello! I'm Litchi");
        System.out.println("What can I do for you?");
        System.out.println(indentations);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String in = scanner.nextLine().trim();

            if (in.equals("bye")) {
                System.out.println(indentations);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(indentations);
                break;
            }

            if (in.equals("list")) {
                printTaskList();
            } else if (in.startsWith("mark ")) {
                markTask(in);
            } else if (in.startsWith("unmark ")) {
                unmarkTask(in);
            } else if (in.startsWith("todo ")) {
                addTodo(in);
            } else if (in.startsWith("deadline ")) {
                addDeadline(in);
            }
        }
    }

    public static void printTaskList() {
        System.out.println(indentations);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNum; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
        System.out.println(indentations);
    }

    public static void addTodo(String in) {
        Task newTodo = new Todo(in.substring(5));
        tasks[taskNum] = newTodo;
        taskNum++;
        System.out.println(indentations);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTodo.toString());
        if (taskNum == 1) {
            System.out.println("Now you have " + taskNum + " task in the list.");
        } else {
            System.out.println("Now you have " + taskNum + " tasks in the list.");
        }
        System.out.println(indentations);
    }

    public static void markTask(String in) {
        int index = Integer.parseInt(in.substring(5)) - 1;
        if (index >= 0 && index < taskNum) {
            tasks[index].markAsDone();
            System.out.println(indentations);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[index].toString());
            System.out.println(indentations);
        } else {
            System.out.println(indentations);
            System.out.println("Out of range!");
            System.out.println(indentations);
        }
    }

    public static void unmarkTask(String in) {
        int index = Integer.parseInt(in.substring(7)) - 1;
        if (index >= 0 && index < taskNum) {
            tasks[index].markAsNotDone();
            System.out.println(indentations);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[index].toString());
            System.out.println(indentations);
        } else {
            System.out.println(indentations);
            System.out.println("Out of range!");
            System.out.println(indentations);
        }
    }

    public static void addDeadline(String in) {
        
    }
}
