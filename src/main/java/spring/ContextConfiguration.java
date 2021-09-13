package spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TaskList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.TaskCrudService;

@Configuration
public class ContextConfiguration {

    @Bean
    public TaskList taskListDao() {
        return new TaskList();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public TaskCrudService taskCrudService() {
        return new TaskCrudService(taskListDao());
    }
}
