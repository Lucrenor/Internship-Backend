package com.example.mergen_backend.repository;

import com.example.mergen_backend.entity.Intern;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InternRepository extends CrudRepository<Intern, Long> {
    Intern findByName(String name);
    List<Intern> findByStatus(String status);
}