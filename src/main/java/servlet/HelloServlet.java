package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Task;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.TaskCrudService;
import spring.ContextConfiguration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
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
    private ConfigurableApplicationContext context;
    private ObjectMapper mapper;
    private TaskCrudService todoService;

    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        mapper = context.getBean(ObjectMapper.class);
        todoService = context.getBean(TaskCrudService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Collection<Task> tasks = todoService.fetchAll();
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
            Task result = todoService.save(requestTask);
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
                todoService.delete(id);
                resp.setStatus(SC_OK);
            } catch (Exception e) {
                resp.setStatus(SC_BAD_REQUEST);
            }
        } else {
            todoService.clear();
        }
    }
}
