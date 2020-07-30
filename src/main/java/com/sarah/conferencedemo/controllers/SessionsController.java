package com.sarah.conferencedemo.controllers;

import com.sarah.conferencedemo.models.Session;
import com.sarah.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// annotation to tell spring mvc that this is a controller
// this will respond to payloads as JSOn REST endpoints
@RestController
// what the mapping URLs will look like
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    // when the controller is instantiated, spring can autowire this and add an instance of the repository
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list(){
        // findAll is implemented by spring jpa magic
        return sessionRepository.findAll();
    }

    @GetMapping
    // addition to class request mapping
    // adds additional part to defined path on class
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }
}
