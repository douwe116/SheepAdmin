package nl.vissersuwald.sheepadmin.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fosterreference", schema = "farming")
public class FosterReference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fostermotherid")
    protected Long fosterMotherId;
    @Column(name = "motherid")
    protected Long motherId;
    @Column(name = "gender")
    protected String gender;
    @Column(name = "yearofbirth")
    protected Long yearOfBirth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFosterMotherId() {
        return fosterMotherId;
    }

    public void setFosterMotherId(Long fosterMotherId) {
        this.fosterMotherId = fosterMotherId;
    }

    public Long getMotherId() {
        return motherId;
    }

    public void setMotherId(Long motherId) {
        this.motherId = motherId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Long yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
