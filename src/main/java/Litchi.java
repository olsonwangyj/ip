import java.util.Scanner;

public class Litchi {
    private final static int maxTaskNums = 100;
    private static final Task[] tasks = new Task[maxTaskNums];
    private static int taskNum = 0;
    private final static String indentations = "_____________________________________________";

    public static void main(String[] args) {

        printHello();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String in = scanner.nextLine().trim();

            if (in.equals("bye")) {
                printBye();
                break;
            } else if (in.equals("list")) {
                printTaskList();
            } else if (in.startsWith("mark ")) {
                markTask(in);
            } else if (in.startsWith("unmark ")) {
                unmarkTask(in);
            } else if (in.startsWith("todo ")) {
                addTodo(in);
            } else if (in.startsWith("deadline ")) {
                addDeadline(in);
            } else if (in.startsWith("event ")) {
                addEvent(in);
            } else {
                printDefault();
            }
        }
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

    public static void printDefault() {
        System.out.println(indentations);
        System.out.println("Welcome to Litchi!");
        System.out.println("Commands:");
        System.out.println("  todo <task>      - Add a new to-do task.");
        System.out.println("  deadline <task> /by <date> - Add a task with a deadline.");
        System.out.println("  event <event> /from <date> /to <date>  - Add an event.");
        System.out.println("  list            - Show all tasks.");
        System.out.println("  bye             - Exit the program.");
        System.out.println(indentations);
    }


    public static void printTaskList() {
        System.out.println(indentations);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNum; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
        System.out.println(indentations);
    }

    public static void printOutOfRange() {
        System.out.println(indentations);
        System.out.println("Out of range!");
        System.out.println(indentations);
    }

    public static void markTask(String in) {
        int index = Integer.parseInt(in.substring(5)) - 1;
        if (index < 0 || index >= taskNum) {
            printOutOfRange();
            return;
        }

        tasks[index].markAsDone();
        System.out.println(indentations);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index].toString());
        System.out.println(indentations);
    }

    public static void unmarkTask(String in) {
        int index = Integer.parseInt(in.substring(7)) - 1;
        if (index < 0 || index >= taskNum) {
            printOutOfRange();
            return;
        }

        tasks[index].markAsNotDone();
        System.out.println(indentations);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[index].toString());
        System.out.println(indentations);
    }

    public static void addTask(Task task) {
        tasks[taskNum] = task;
        taskNum++;
        System.out.println(indentations);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskNum + " task" + (taskNum == 1 ? "" : "s") + " in the list.");
        System.out.println(indentations);
    }

    public static void addTodo(String in) {
        String description = in.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println(indentations);
            System.out.println("The description of a todo cannot be empty!");
            System.out.println(indentations);
            return;
        }
        Task newTodo = new Todo(description);
        addTask(newTodo);
    }

    public static void addDeadline(String in) {
        int begin = 9;
        int end = in.indexOf("/by");
        if (end == -1) {
            System.out.println(indentations);
            System.out.println("The deadline is missing!");
            System.out.println(indentations);
            return;
        }

        String description = in.substring(begin, end).trim();
        String deadline = in.substring(end + 3).trim();
        if (description.isEmpty() || deadline.isEmpty()) {
            System.out.println(indentations);
            System.out.println("The description or the deadline cannot be empty!");
            System.out.println(indentations);
            return;
        }

        Task newDeadline = new Deadline(description, deadline);
        addTask(newDeadline);
    }

    public static void addEvent(String in) {
        int begin = 6;
        int from = in.indexOf("/from");
        int to = in.indexOf("/to");
        if (from == -1 || to == -1) {
            System.out.println(indentations);
            System.out.println("The event duration is missing!");
            System.out.println(indentations);
            return;
        }

        String description = in.substring(begin, from).trim();
        String fromTime = in.substring(from + 5, to).trim();
        String toTime = in.substring(to + 3).trim();
        if (description.isEmpty() || fromTime.isEmpty() || toTime.isEmpty()) {
            System.out.println(indentations);
            System.out.println("The description or time values for event cannot be empty!");
            System.out.println(indentations);
            return;
        }

        Task newEvent = new Event(description, fromTime, toTime);
        addTask(newEvent);
    }
}
