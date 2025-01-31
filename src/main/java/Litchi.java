import java.util.Scanner;

public class Litchi {
    private final static int maxTaskNums = 100;
    private static String[] tasks = new String[maxTaskNums];
    private static int taskNum = 0;

    public static void main(String[] args) {
        String indentations = "_____________________________________________";
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
                System.out.println(indentations);
                for (int i = 0; i < taskNum; i++) {
                    int j = i + 1;
                    System.out.println(j + ". " + tasks[i]);
                }
                System.out.println(indentations);
            } else {
                tasks[taskNum] = in;
                taskNum++;
                System.out.println(indentations);
                System.out.println("added: " + in);
                System.out.println(indentations);
            }
        }
    }
}
