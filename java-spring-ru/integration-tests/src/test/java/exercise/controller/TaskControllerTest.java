package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    // BEGIN
    @Test
    public void testUpdate() throws Exception {
        Task task = generateTask();
        taskRepository.save(task);

        var date = new HashMap<>();
        date.put("title", "updatedTitle");

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(date));
        mockMvc.perform(request).andExpect(status().isOk());

        task = taskRepository.findById(task.getId()).get();
        assertThat(task.getTitle().equals(date.get("title")));

    }

    @Test
    public void testDelete() throws Exception {
        Task task = generateTask();
        taskRepository.save(task);

        var request = delete("/tasks/" + task.getId());
        mockMvc.perform(request);

        var requestGet = get("/tasks/" + task.getId());
        mockMvc.perform(requestGet).andExpect(status().isNotFound());
    }

    @Test
    public void testIndexbyId() throws Exception {
        Task task = generateTask();
        taskRepository.save(task);

        var request = get("/tasks/" + task.getId());
        mockMvc.perform(request).andExpect(status().isOk());

        var bedRequset = get("/tasks/" + 999);
        mockMvc.perform(bedRequset).andExpect(status().isNotFound());
    }

    @Test
    public void testCreate() throws Exception {
        var date = new HashMap<>();
        date.put("title", "createNew");

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(date));
        mockMvc.perform(request).andExpect(status().isCreated());

        assertThat(taskRepository.findByTitle("createNew").get().getTitle().equals("createNew"));
    }
    // END
}
