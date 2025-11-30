package nl.vissersuwald.sheepadmin.models.farming;

import javax.persistence.Column;
import java.io.Serializable;

public class BirthId implements Serializable {

    @Column(name = "motherId")
    private Long motherId;

    @Column(name = "yearOfBirth")
    private int yearOfBirth;

    public BirthId() {}

    public BirthId(Long motherId, int yearOfBirth) {
        this.motherId = motherId;
        this.yearOfBirth = yearOfBirth;
    }

    public Long getMotherId() {
        return motherId;
    }

    public void setMotherId(Long motherId) {
        this.motherId = motherId;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
