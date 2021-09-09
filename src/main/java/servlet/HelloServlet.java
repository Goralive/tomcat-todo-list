package servlet;

import entity.Task;
import service.TodoListImp;
import utils.JsonMapper;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/todo"}
)
public class HelloServlet extends HttpServlet {
    private final TodoListImp todo = new TodoListImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String response = todo.parseGetRequest();
            todo.sendResponse(resp.getWriter(), response);
            resp.setStatus(SC_OK);
        } catch (IOException e) {
            resp.setStatus(SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Task requestTask = JsonMapper.parse(req.getReader().lines().collect(Collectors.joining()), Task.class);
            String response = todo.parsePostRequest(requestTask);
            todo.sendResponse(resp.getWriter(), response);
            resp.setStatus(SC_OK);
        } catch (IOException e) {
            resp.setStatus(SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String query = req.getQueryString();
        if (query != null) {
            try {
                Integer id = Integer.valueOf(query.substring(query.lastIndexOf("=") + 1));
                todo.parseDeleteRequest(id);
            } catch (NumberFormatException e) {
                resp.setStatus(SC_BAD_REQUEST);
            }
        } else {
            todo.parseDeleteRequest();
        }
        resp.setStatus(SC_OK);
    }
}
