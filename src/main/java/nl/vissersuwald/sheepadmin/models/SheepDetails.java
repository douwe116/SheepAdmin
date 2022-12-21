package nl.vissersuwald.sheepadmin.models;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="sheep", schema="farming")
public class SheepDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "dateofbirth")
    private Date dateOfBirth;
    @Column(name = "fatherid")
    private Long fatherId;
    @ManyToOne
    @JoinColumn(name = "motherid", referencedColumnName = "id")
    private Sheep mother;
    @ManyToOne
    @JoinColumn(name = "fostermotherid", referencedColumnName = "id")
    private Sheep fosterMother;
    @Column(name = "yearofbirth")
    private Long yearOfBirth;
    @Column(name = "dateofdeath")
    private Date dateOfDeath;
    @Column(name = "reasonofdeath")
    private String reasonOfDeath;
    @Column(name = "colour")
    private String colour;
    @Column(name = "oldname")
    private String oldName;
    @Column(name = "boughtfrom")
    private String boughtFrom;
    @Column(name = "gender")
    private String gender;
    @OneToMany
    @JoinColumn(name = "motherid", referencedColumnName = "id")
    @OrderBy(value = "yearofbirth desc")
    private List<BirthDetails> birthDetailsList;

    public SheepDetails() {
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

    public Sheep getMother() {
        return mother;
    }

    public void setMother(Sheep mother) {
        this.mother = mother;
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

    public List<BirthDetails> getBirthDetailsList() {
        return birthDetailsList;
    }

    public void setBirthDetailsList(List<BirthDetails> birthDetailsList) {
        this.birthDetailsList = birthDetailsList;
    }

    public Sheep getFosterMother() {
        return fosterMother;
    }

    public void setFosterMother(Sheep fosterMother) {
        this.fosterMother = fosterMother;
    }
}
