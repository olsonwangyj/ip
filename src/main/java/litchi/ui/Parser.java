package litchi.ui;

import litchi.exception.LitchiException;
import litchi.task.Deadline;
import litchi.task.Event;
import litchi.task.Todo;

/**
 * Parse the user's command
 */
public class Parser {
    /**
     * Get the index of the input after the keyword.
     * @param command The user's input command.
     * @param prefixLength The length of the keyword
     * @return The index of the input after the keyword.
     * @throws NumberFormatException If the index is a legal number.
     */
    public static int parseIndex(String command, int prefixLength) throws NumberFormatException {
        return Integer.parseInt(command.substring(prefixLength).trim()) - 1;
    }

    /**
     * Parse the todo command.
     * @param command The user input.
     * @return The Todo from the user's input
     * @throws LitchiException If the index is out of bounds.
     */
    public static Todo parseTodoCommand(String command) throws LitchiException {
        String description = command.substring(4).trim();
        if (description.isEmpty()) {
            throw new LitchiException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    /**
     * Parse the deadline command.
     * @param command The user input.
     * @return The Deadline from the user's input
     * @throws LitchiException If the index is out of bounds.
     */
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

    /**
     * Parse the event command.
     * @param command The user input.
     * @return The Event from the user's input
     * @throws LitchiException If the index is out of bounds.
     */
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

    /**
     * Parse the find command.
     * @param command The user input.
     * @return The String needs to be found.
     * @throws LitchiException If the index is out of bounds.
     */
    public static String parseFindCommand(String command) throws LitchiException {
        int begin = 5;
        return command.substring(begin).trim();
    }
}