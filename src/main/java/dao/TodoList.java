package dao;

import entity.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TodoList {
    private final Map<Integer, Task> taskMap;
    private int counter = 1;

    public TodoList() {
        this.taskMap = new HashMap<>();
    }

    public int addTask(Task task) {
        task.setId(counter);
        taskMap.put(task.getId(), task);
        counter++;
        return task.getId();
    }

    public void deleteTask(Integer id) {
        this.taskMap.remove(id);
    }

    public void deleteTask() {
        this.taskMap.clear();
    }

    public Collection<Task> getAllTasks() {
        return taskMap.values();
    }
}
