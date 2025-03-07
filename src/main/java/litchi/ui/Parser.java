package litchi.ui;

import litchi.exception.LitchiException;
import litchi.task.Deadline;
import litchi.task.Event;
import litchi.task.Todo;

public class Parser {
    public static int parseIndex(String command, int prefixLength) throws NumberFormatException {
        return Integer.parseInt(command.substring(prefixLength).trim()) - 1;
    }

    public static Todo parseTodoCommand(String command) throws LitchiException {
        String description = command.substring(4).trim();
        if (description.isEmpty()) {
            throw new LitchiException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    public static Deadline parseDeadlineCommand(String command) throws LitchiException {
        int begin = 9;
        int end = command.indexOf("/by");
        if (end == -1) {
            throw new LitchiException("The deadline is missing! " +
                    "Please use: deadline <task> /by <date>.");
        }

        String description = command.substring(begin, end).trim();
        String deadline = command.substring(end + 3).trim();
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new LitchiException("The description or the deadline cannot be empty.");
        }

        return new Deadline(description, deadline);
    }

    public static Event parseEventCommand(String command) throws LitchiException {
        int begin = 6;
        int from = command.indexOf("/from");
        int to = command.indexOf("/to");
        if (from == -1 || to == -1) {
            throw new LitchiException("The event format is incorrect! " +
                    "Please use: event <event> /from <date> /to <date>.");
        }

        String description = command.substring(begin, from).trim();
        String fromTime = command.substring(from + 5, to).trim();
        String toTime = command.substring(to + 3).trim();
        if (description.isEmpty() || fromTime.isEmpty() || toTime.isEmpty()) {
            throw new LitchiException("The description or time values for event cannot be empty.");
        }

        return new Event(description, fromTime, toTime);
    }
}