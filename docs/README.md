# Litchi User Guide

Litchi is a task management application optimized for use via a Command Line Interface (CLI). Litchi can help you manage your tasks efficiently.

## Features

### Adding a Todo Task: `todo`
Adds a simple task without a specific time or date.

**Format:**
```
todo TASK_DESCRIPTION
```
**Examples:**
```
todo Buy groceries
todo Submit assignment
```

### Adding a Deadline Task: `deadline`
Adds a task with a specific deadline.

**Format:**
```
deadline TASK_DESCRIPTION /by DEADLINE_DATE
```
**Examples:**
```
deadline Submit report /by Dec 5
```

### Adding an Event Task: `event`
Adds a task that occurs at a specific time.

**Format:**
```
event TASK_DESCRIPTION /from EVENT_TIME /to EVENT_TIME
```
**Examples:**
```
event Team meeting /from Monday 2pm /to 3pm
```

### Listing All Tasks: `list`
Displays all tasks currently stored in the task list.

**Format:**
```
list
```

### Marking a Task as Done: `mark`
Marks a task as completed.

**Format:**
```
mark INDEX
```
**Example:**
```
mark 2  # Marks the 2nd task as done
```

### Unmarking a Task: `unmark`
Marks a completed task as not done.

**Format:**
```
unmark INDEX
```
**Example:**
```
unmark 3  # Marks the 3rd task as not done
```

### Finding Tasks: `find`
Searches for tasks containing the specified keyword.

**Format:**
```
find KEYWORD
```
**Example:**
```
find report  # Finds all tasks containing the word "report"
```

### Deleting a Task: `delete`
Removes a task from the list.

**Format:**
```
delete INDEX
```
**Example:**
```
delete 1  # Deletes the 1st task
```

### Exiting the Program: `bye`
Closes the application.

**Format:**
```
bye
```

## Data Storage
- Tasks are saved automatically to a text file at `./data/litchi.txt` after any modification.
- The application will attempt to load existing tasks from this file when started.

## Error Handling
- The application provides error messages for invalid commands or inputs.
- e.g. If you enter an invalid index, the application will notify you.

## Command Summary
| Action | Format & Example                                                                                             |
|-------------|--------------------------------------------------------------------------------------------------------------|
| Add a todo task | `todo TASK_DESCRIPTION` e.g., `todo Finish report`                                                           |
| Add a deadline | `deadline TASK_DESCRIPTION /by DEADLINE_DATE` e.g., `deadline Submit report /by Dec 5`                       |
| Add an event | `event TASK_DESCRIPTION /from EVENT_TIME /to EVENT_TIME` e.g., `event Team meeting /from Monday 2pm /to 3pm` |
| List tasks | `list`                                                                                                       |
| Mark as done | `mark INDEX` e.g., `mark 1`                                                                                  |
| Mark as not done | `unmark INDEX` e.g., `unmark 2`                                                                              |
| Find tasks | `find KEYWORD` e.g., `find report`                                                                           |
| Delete task | `delete INDEX` e.g., `delete 2`                                                                              |
| Exit application | `bye`                                                                                                        |

Enjoy using Litchi to manage your tasks efficiently!