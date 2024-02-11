package nl.vissersuwald.sheepadmin.models.numbering;

import javax.persistence.Id;
import java.util.List;

public class NumberSheep {
    @Id
    private Long birthId;
    private List<NewNumber> newNumbers;

    public Long getBirthId() {
        return birthId;
    }

    public void setBirthId(Long birthId) {
        this.birthId = birthId;
    }

    public List<NewNumber> getNewNumbers() {
        return newNumbers;
    }

    public void setNewNumbers(List<NewNumber> newNumbers) {
        this.newNumbers = newNumbers;
    }
}