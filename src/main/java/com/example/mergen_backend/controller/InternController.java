package com.example.mergen_backend.controller;

import com.example.mergen_backend.entity.Intern;
import com.example.mergen_backend.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intern")
public class InternController {

    @Autowired
    private InternService internService;


    @PostMapping
    public Intern postStatus(@RequestBody Intern intern) {
        return internService.saveStatus(intern);
    }

    @GetMapping
    public List<Intern> getAllInterns() {
        return internService.getAllInterns();
    }

    @PutMapping("/update/{name}")
    public Intern updateStatus(@PathVariable String name, @RequestBody Intern intern) {
        return internService.updateStatus(name, intern);
    }

    @PutMapping("/change/{name}")
    public Intern changeStatus(@PathVariable String name, @RequestBody Intern intern) {
        return internService.changeStatus(name, intern);
    }

}