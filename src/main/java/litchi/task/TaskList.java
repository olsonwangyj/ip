package litchi.task;

import litchi.exception.LitchiException;

import java.util.ArrayList;
import java.util.List;


public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskNum;

    public TaskList() {
        tasks = new ArrayList<>();
        taskNum = 0;
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        this.taskNum = tasks.size();
    }

    public int getTaskCount() {
        return taskNum;
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public Task getTask(int index) throws LitchiException {
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }
        return tasks.get(index);
    }

    public void markTaskAsDone(int index) throws LitchiException {
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }
        tasks.get(index).markAsDone();
    }

    public void markTaskAsNotDone(int index) throws LitchiException {
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }
        tasks.get(index).markAsNotDone();
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskNum++;
    }

    public void deleteTask(int index) throws LitchiException {
        if (index < 0 || index >= taskNum) {
            throw new LitchiException("Task number is out of range.");
        }
        tasks.remove(index);
        taskNum--;
    }
}
