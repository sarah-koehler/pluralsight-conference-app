package com.sarah.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

// Indicates that this class is connected to a database table (called sessions)
@Entity(name = "sessions")
// ignore these properties when the class is serialized to JSON
// these are properties and stub methods added by hibernate because the class has a relation
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Session {
    // fields of this model, mapping the columns of the table in the DB
    // use snake case against java convention to keep names as in DB
    // columns can be renamed when @column annotation is used
    // otherwise, no annotation is needed (because we already have @Entity)
    private String session_name;
    private String session_description;
    private Integer session_length;
    // Additional annotations needed for ID field
    // tells JPA that this is the primary key field
    @Id
    // tells JPA that this field should be auto generated when writing a new object
    // it will be generated as a sequence (postgres datatype), so postgres will take care of incrementing
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long session_id;

    // m to n relationship to Speakers
    // the session owns the relationship
    // in DB is a join table
    // this annotation says that this field is many to many relationship with a join table
    @ManyToMany
    // this specifies the join table (name) and the foreign keys
    @JoinTable(
        name = "session_speakers",
        joinColumns = @JoinColumn(name = "session_id"),
        inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speaker> speakers;



    // public constructor to allow for easy serialization and deserialization
    public Session(){
    }

    // auto generated getter and setter

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(final List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(final String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(final String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(final Integer session_length) {
        this.session_length = session_length;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(final Long session_id) {
        this.session_id = session_id;
    }
}
