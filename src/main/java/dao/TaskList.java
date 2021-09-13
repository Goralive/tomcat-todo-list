package dao;

import entity.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskList {

    private final Map<Integer, Task> taskMap;
    private int counter = 1;

    public TaskList(Map<Integer, Task> taskMap) {
        this.taskMap = taskMap;
    }

    public int addTask(Task task) {
        task.setId(counter++);
        taskMap.put(task.getId(), task);
        return task.getId();
    }

    public void deleteTask(Integer id) {
        this.taskMap.remove(id);
    }

    public void clear() {
        this.taskMap.clear();
    }

    public Collection<Task> getAllTasks() {
        return taskMap.values();
    }

    public void update(Task task) {
        if (taskMap.containsKey(task.getId())) {
            taskMap.put(task.getId(), task);
        }
    }
}
