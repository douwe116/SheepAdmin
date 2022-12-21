package nl.vissersuwald.sheepadmin.models;

import org.hibernate.CustomEntityDirtinessStrategy;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="sheep", schema="farming")
public class Sheep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    protected String name;
    @Column(name = "dateofbirth")
    protected Date dateOfBirth;
    @Column(name = "fatherid")
    protected Long fatherId;
    @Column(name = "motherid", nullable = false)
    protected Long motherId;
    @Column(name = "fostermotherid")
    protected Long fosterMotherId;
    @Column(name = "yearofbirth", nullable = false)
    protected Long yearOfBirth;
    @Column(name = "dateofdeath")
    protected Date dateOfDeath;
    @Column(name = "reasonofdeath")
    protected String reasonOfDeath;
    @Column(name = "colour")
    protected String colour;
    @Column(name = "oldname")
    protected String oldName;
    @Column(name = "boughtfrom")
    protected String boughtFrom;
    @Column(name = "comments")
    protected String comments;
    @Column(name = "gender")
    protected String gender;

    public Sheep() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public Long getMotherId() {
        return motherId;
    }

    public void setMotherId(Long motherId) {
        this.motherId = motherId;
    }

    public Long getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Long yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getReasonOfDeath() {
        return reasonOfDeath;
    }

    public void setReasonOfDeath(String reasonOfDeath) {
        this.reasonOfDeath = reasonOfDeath;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getBoughtFrom() {
        return boughtFrom;
    }

    public void setBoughtFrom(String boughtFrom) {
        this.boughtFrom = boughtFrom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getFosterMotherId() {
        return fosterMotherId;
    }

    public void setFosterMotherId(Long fosterMotherId) {
        this.fosterMotherId = fosterMotherId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

