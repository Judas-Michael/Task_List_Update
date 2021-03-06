package com.clara.taskdb.controller;

import com.clara.taskdb.model.Task;
import com.clara.taskdb.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLParagraphElement;

import java.util.List;

@RestController

public class TaskAPIController {

    private final TaskRepository tasks;

    @Autowired

    public TaskAPIController(TaskRepository tasks){
        this.tasks = tasks;

        tasks.save(new Task("task 1", true, false));
        tasks.save(new Task("task 2", false, true));
        tasks.save(new Task("task 3", true, false));
        tasks.save(new Task("task 4", false, false));
    }

    @PostMapping(value="/add") //adds task
    public ResponseEntity addTask(@RequestBody Task task) {
        System.out.println("new task" + task);
        try{
            tasks.save(task);
        } catch (Exception e) {
            return new ResponseEntity<>("Task object is invalid", HttpStatus.BAD_REQUEST); //catches bad requests to add items
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("/tasks") //lists all tasks in order of urgency
    public ResponseEntity<List<Task>> queryTasks() {
        return new ResponseEntity<>(tasks.findAllByOrderByUrgentDesc(), HttpStatus.OK);
    }

    @PatchMapping(value="/completed") //looks at completed tasks
    public ResponseEntity markTaskAsCompleted(@RequestBody Task task){

        int tasksUpdated = tasks.setTaskCompleted(task.isCompleted(), task.getId());
        if (tasksUpdated == 0) { //if that task doesn't exist nothing is returned
            return new ResponseEntity(HttpStatus.NOT_FOUND); //this happens if the task doesn't exist
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value="/delete") //deleted tasks in list
    public ResponseEntity deleteTask(@RequestBody Task task) {
        tasks.delete(task);
        return new ResponseEntity(HttpStatus.OK);
    }

}

