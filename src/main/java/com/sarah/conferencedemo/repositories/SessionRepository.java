package com.sarah.conferencedemo.repositories;

import com.sarah.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

// extend JpaRepository to get the basic data access (CRUD) operations
// the reference to the class is all we need here
public interface SessionRepository extends JpaRepository<Session, Long> {
}
