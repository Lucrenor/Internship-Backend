package com.example.mergen_backend.repository;

import com.example.mergen_backend.entity.Email;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends CrudRepository<Email, Long> {
    boolean existsByRecipientAndStatus(String recipient, String status);
    List<Email> findByRecipient(String recipient);
}