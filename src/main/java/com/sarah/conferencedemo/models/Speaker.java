package com.sarah.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity( name = "speakers")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long speaker_id;

    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;

    // define the Many to Many relationship to session from this side
    // The relation is owned by the Session, so we don't need to define the join table again
    // instead we just say that it is part of a Relationship
    // and how the field is called on the other side of the relation
    @ManyToMany(mappedBy = "speakers")
    // don't serialize this in JSON response
    // required here to avoid cyclical serialization of sessions and speakers
    @JsonIgnore
    private List<Session> sessions;

    // binary data field for the photo (as byte array)
    // this annotation says that this is a "Large OBject"
    @Lob
    // Hibernate is the JPA implementation we are using
    // this annotation helps it to manage large data, otherwise we might get exceptions
    @Type(type="org.hibernate.type.BinaryType")
     private byte[] speaker_photo;

    public Speaker(){

    }

    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }

    public void setSpeaker_photo(final byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(final List<Session> sessions) {
        this.sessions = sessions;
    }

    public Long getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(final Long speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(final String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(final String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(final String company) {
        this.company = company;
    }

    public String getSpeaker_bio() {
        return speaker_bio;
    }

    public void setSpeaker_bio(final String speaker_bio) {
        this.speaker_bio = speaker_bio;
    }
}
