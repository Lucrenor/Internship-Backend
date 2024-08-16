package com.example.mergen_backend.repository;

import com.example.mergen_backend.entity.Authorization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends CrudRepository<Authorization, Long> {
    Authorization findByUsername(String username);
}