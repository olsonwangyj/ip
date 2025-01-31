import java.util.Scanner;

public class Litchi {
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

            System.out.println(indentations);
            System.out.println(in);
            System.out.println(indentations);
        }
    }
}
