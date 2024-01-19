package Rest_api.task_1.controller;

import java.util.List;
import java.util.Optional;

import Rest_api.task_1.Task;
import Rest_api.task_1.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class TaskController {

    private final TaskRepository Repo;

    @Autowired
    public TaskController(TaskRepository Repo) {
        this.Repo = Repo;
    }

    @PostMapping(value = "/add_task")
    public String addUser(@RequestBody Task task){
        Repo.save(task);
        return "Task created successfully";
    }

    @GetMapping("/get_task")
    public List<Task> Get_Task(){
        return Repo.findAll();
    }


    @GetMapping("/get_task/{id}")
    public Optional<Task> Get_Task_ByID(@PathVariable("id") String id){
        return Repo.findById(id);
    }

    @GetMapping("/find_ByName/{name}")
    public List<Task> find_ByName(@PathVariable("name") String name){
        return Repo.getTaskByName(name);
    }

    @GetMapping("/find_ByAssignee/{assignee}")
    public List<Task> find_ByAssignee(@PathVariable("assignee") String assignee){
        return Repo.getTaskByAssignee(assignee);
    }

    @DeleteMapping("/del_task/{id}")
    public ResponseEntity<String> del_task(@PathVariable("id") String id){
        Optional<Task> result = Repo.findById(id);
        if (result.isPresent()) {
            Repo.deleteById(id);
            return ResponseEntity.ok("Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with ID: " + id);
        }
    }
}
