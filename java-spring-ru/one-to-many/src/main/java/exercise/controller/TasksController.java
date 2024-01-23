package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
//import exercise.model.Task;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> index() {
        var tasks = taskRepository.findAll();
        var tasksDto = tasks.stream().map(task -> taskMapper.map(task)).toList();

        return tasksDto;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO show(@PathVariable Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        var taskDTO = taskMapper.map(task);
//        taskDTO.setAssigneeId(task.getAssignee().getId());
        return taskDTO;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO createDTO) {
        var task = taskMapper.map(createDTO);
        taskRepository.save(task);
        var taskDTO = taskMapper.map(task);
//        taskDTO.setAssigneeId(task.getAssignee().getId());// ??
        return taskDTO;
    }

    @PutMapping(path = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
    public TaskDTO updateTask(@PathVariable long id, @Valid @RequestBody TaskUpdateDTO updateDTO)  {
//        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
//        taskMapper.update(updateDTO, task);
//        taskRepository.save(task);
//        var taskDTO = taskMapper.map(task);

        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        task.setTitle(updateDTO.getTitle());
        task.setDescription(updateDTO.getDescription());
        task.setAssignee(userRepository.getById(updateDTO.getAssigneeId()));
        taskRepository.save(task);

//        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
//        var user = userRepository.findByEmail(task.getAssignee().getEmail()).orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
//        task.setTitle(updateDTO.getTitle());
//        task.setDescription(updateDTO.getDescription());
//        user.removeTask(task);
//        task.setAssignee(userRepository.getById(updateDTO.getAssigneeId()));
//        user.addTask(task);
//        userRepository.save(user);



        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        var user = userRepository.findByEmail(task.getAssignee().getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        user.removeTask(task);
        userRepository.save(user);
    }
    // END
}
