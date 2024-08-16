package com.example.mergen_backend.service;

import com.example.mergen_backend.entity.Intern;
import com.example.mergen_backend.repository.InternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternService {

    @Autowired
    private InternRepository internRepository;


    public Intern saveStatus(Intern intern) {
        return internRepository.save(intern);
    }

    public List<Intern> getAllInterns() {
        return (List<Intern>) internRepository.findAll();
    }

    public Intern updateStatus(String name, Intern intern) {
        Intern newIntern = new Intern();
        newIntern.setName(intern.getName());
        newIntern.setMail(intern.getMail());
        newIntern.setTel_no(intern.getTel_no());
        newIntern.setDate(intern.getDate());
        newIntern.setDepartment(intern.getDepartment());
        newIntern.setStatus(intern.getStatus());
        return internRepository.save(newIntern);
    }

    public Intern changeStatus(String name, Intern intern) {
        Intern existingIntern = internRepository.findByName(name);
        if (existingIntern != null) {
            existingIntern.setStatus(intern.getStatus());
            return internRepository.save(existingIntern);
        } else {
            return null;
        }
    }

    public List<Intern> getInternsByStatus(String status) {
        return internRepository.findByStatus(status);
    }
}