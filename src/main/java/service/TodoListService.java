package service;

import entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TodoListService<T> {
    Logger logger = LoggerFactory.getLogger(TodoListService.class);

    String parsePostRequest(Task request) throws IOException;

    String parseGetRequest() throws IOException;

    void parseDeleteRequest(Integer id) throws IOException;
}
