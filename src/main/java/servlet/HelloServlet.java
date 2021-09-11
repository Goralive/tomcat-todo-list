package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Task;
import service.TaskCrudService;

import java.io.IOException;
import java.util.Collection;

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
    private  final ObjectMapper mapper = new ObjectMapper();
    private final TaskCrudService todo = new TaskCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Collection<Task> tasks = todo.fetchAll();
            mapper.writeValue(resp.getOutputStream(), tasks);
            resp.setStatus(SC_OK);
        } catch (IOException e) {
            resp.setStatus(SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Task requestTask = mapper.readValue(req.getInputStream(), Task.class);
            Task result = todo.save(requestTask);
            mapper.writeValue(resp.getOutputStream(), result);
            resp.setStatus(SC_OK);
        } catch (IOException e) {
            resp.setStatus(SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                todo.delete(id);
                resp.setStatus(SC_OK);
            } catch (Exception e) {
                resp.setStatus(SC_BAD_REQUEST);
            }
        } else {
            todo.clear();
        }
    }
}
