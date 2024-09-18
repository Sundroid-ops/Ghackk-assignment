package com.example.assignment.Repository;

import com.example.assignment.Entity.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepo extends JpaRepository<Client, UUID> {
    List<Client> findByClientNameContainingIgnoreCase(String clientName, Pageable page);
}
