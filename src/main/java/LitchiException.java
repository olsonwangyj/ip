public class LitchiException extends Exception{
    public LitchiException(){
        super(getDefaultMessage());
    }

    public LitchiException(String message){
        super(message);
    }

    private static String getDefaultMessage() {
        return "Unknown command.\n" +
                "Welcome to Litchi!\n" +
                "Commands:\n" +
                "  todo <task>      - Add a new to-do task.\n" +
                "  deadline <task> /by <date> - Add a task with a deadline.\n" +
                "  event <event> /from <date> /to <date>  - Add an event.\n" +
                "  list            - Show all tasks.\n" +
                "  bye             - Exit the program.";
    }
}
