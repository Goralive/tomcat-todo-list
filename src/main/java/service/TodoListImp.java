package service;

import dao.TodoList;
import entity.Task;
import utils.JsonMapper;

import java.io.IOException;
import java.io.PrintWriter;

public class TodoListImp implements TodoListService {
    private final TodoList listOfTask = new TodoList();

    @Override
    public String parsePostRequest(Task task) throws IOException {
        logger.info("Get from request a  {}", task);

        if (task.getId() == null || task.getId() == 0) {
            int id = listOfTask.addTask(task);
            logger.info("Added task with id {}", id);
        }
        return JsonMapper.parseToString(task);
    }

    @Override
    public String parseGetRequest() throws IOException {
        return JsonMapper.parseToString(listOfTask.getAllTasks());
    }

    @Override
    public void parseDeleteRequest(Integer id) {
        logger.info("Clear task with id {}", id);
        listOfTask.deleteTask(id);
    }

    public void sendResponse(PrintWriter out, String body) {
        logger.info("Send response with body {}", body);
        out.print(body);
        out.flush();
        out.close();
    }

    public void parseDeleteRequest() {
        logger.info("Clear all tasks");
        listOfTask.deleteTask();
    }
}
