package spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TaskList;
import entity.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.TaskCrudService;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ContextConfiguration {

    @Bean
    public TaskList taskListDao() {
        return new TaskList(taskMap());
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public TaskCrudService taskCrudService() {
        return new TaskCrudService(taskListDao());
    }

    @Bean
    public Map<Integer, Task> taskMap() {
        return new HashMap<>();
    }
}
