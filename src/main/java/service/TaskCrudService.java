package service;

import dao.TaskList;
import entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Objects;

public class TaskCrudService implements CrudService<Task, Integer> {
    private final TaskList listOfTask;
    private static final Logger logger = LoggerFactory.getLogger(TaskCrudService.class);

    public TaskCrudService(TaskList listOfTask) {
        this.listOfTask = listOfTask;
    }

    @Override
    public Task save(Task task) {
        logger.info("Get from request a  {}", task);

        if (task.getId() == null) {
            int id = listOfTask.addTask(task);
            logger.info("Added task with id {}", id);
        } else {
            listOfTask.update(task);
        }
        return task;
    }

    @Override
    public Collection<Task> fetchAll() {
        return listOfTask.getAllTasks();
    }

    @Override
    public void delete(Integer id) {
        Objects.requireNonNull(id);
        logger.info("Clear task with id {}", id);
        listOfTask.deleteTask(id);
    }

    public void sendResponse(PrintWriter out, String body) {
        logger.info("Send response with body {}", body);
        out.print(body);
        out.flush();
        out.close();
    }

    public void clear() {
        logger.info("Clear all tasks");
        listOfTask.clear();
    }
}
