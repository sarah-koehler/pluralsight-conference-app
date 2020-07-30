package com.sarah.conferencedemo.controllers;

import com.sarah.conferencedemo.models.Session;
import com.sarah.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @PostMapping
    public Session create(@RequestBody final Session session){
        // save does not write to DB, write is only done after flushing
        return sessionRepository.saveAndFlush(session);
    }

    // there is no annotation for delete, so we need to use the general RequestMapping
    // and specify the HTTP method
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        // might need to check and delete child records
        // if there are any children, the delete would fail because of foreign key constraints
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        // TODO check payload if all attributes are passed, if not return 400 bad request
        Session existingSession = sessionRepository.getOne(id);
        // copy properties from the request to the existing object, but don't update ID!
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
