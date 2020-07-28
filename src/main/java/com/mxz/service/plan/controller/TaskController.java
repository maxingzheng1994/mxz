package com.mxz.service.plan.controller;

import com.mxz.service.plan.dao.TaskRepository;
import com.mxz.service.plan.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/27 13:52
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("")
    public List<Task> getList() {
        List<Task> all = taskRepository.findAll();
        return all;
    }
    @GetMapping("/{id}")
    public Task save(@RequestBody Task task) {
        return  taskRepository.save(task);
    }
}
