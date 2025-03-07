package litchi.task;

import litchi.exception.LitchiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks and provides operations to modify them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskNum;

    /**
     * Constructor of TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        taskNum = 0;
    }

    /**
     * Constructor of TaskList.
     * @param tasks The list of tasks would be loaded.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        this.taskNum = tasks.size();
    }

    /**
     * Get the number of the tasks in the tasklist.
     * @return The number of the tasks in the tasklist.
     */
    public int getTaskCount() {
        return taskNum;
    }

    /**
     * Get the tasklist.
     * @return The tasklist.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Get the task of specific index.
     * @param index The specific number of tasks.
     * @return Task with the specific index.
     * @throws LitchiException If the index is out of bounds.
     */
    public Task getTask(int index) throws LitchiException {
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark as done.
     * @throws LitchiException If the index is out of bounds.
     */
    public void markTaskAsDone(int index) throws LitchiException {
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to mark as not done.
     * @throws LitchiException If the index is out of bounds.
     */
    public void markTaskAsNotDone(int index) throws LitchiException {
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }
        tasks.get(index).markAsNotDone();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskNum++;
    }

    /**
     * Remove the task with specific index in the tasklist.
     * @param index The spcific index of the task will be removed.
     * @throws LitchiException If the index is out of bounds.
     */
    public void deleteTask(int index) throws LitchiException {
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }
        tasks.remove(index);
        taskNum--;
    }

    /**
     * Find all the tasks with the description.
     * @param command The specific description looked for.
     * @return The TaskList with the description.
     * @throws LitchiException If the index is out of bounds.
     */
    public TaskList findTask(String command) throws LitchiException {
        TaskList findOutcome = new TaskList();
        for (Task task : tasks) {
            String cur = task.getDescription();
            if (cur.contains(command)) {
                findOutcome.addTask(task);
            }
        }

        return findOutcome;
    }
}
