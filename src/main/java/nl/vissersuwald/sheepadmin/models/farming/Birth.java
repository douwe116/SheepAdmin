package nl.vissersuwald.sheepadmin.models.farming;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="births", schema="farming")
public class Birth {
    @EmbeddedId
    private BirthId id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "motherId", referencedColumnName = "id", insertable = false, updatable = false)
    private Sheep mother;
    @Column(name = "dateofbirth")
    private Date dateOfBirth;
    @Column(name = "ewes")
    private Long ewes;
    @Column(name = "rams")
    private Long rams;
    @Column(name = "fosteredewes")
    private Long fosteredEwes;
    @Column(name = "fosteredrams")
    private Long fosteredRams;
    @Column(name = "eweinfantmortality")
    private Long eweInfantMortality;
    @Column(name = "raminfantmortality")
    private Long ramInfantMortality;
    @Column(name = "fosterewes")
    private Long fosterEwes;
    @Column(name = "fosterrams")
    private Long fosterRams;
    @Column(name = "infantmortality")
    private Long infantMortality;
    @Column(name = "status")
    private String status;

    public Birth() {}

    public Birth(Sheep mother, int yearOfBirth) {
        this.mother = mother;
        this.id = new BirthId(mother.getId(), yearOfBirth);
    }

    public BirthId getBirthId() {
        return id;
    }

    public void setBirthId(BirthId birthId) {
        this.id = birthId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getEwes() {
        return ewes;
    }

    public void setEwes(Long ewes) {
        this.ewes = ewes;
    }

    public Long getRams() {
        return rams;
    }

    public void setRams(Long rams) {
        this.rams = rams;
    }

    public Long getFosteredEwes() {
        return fosteredEwes;
    }

    public void setFosteredEwes(Long fosteredEwes) {
        this.fosteredEwes = fosteredEwes;
    }

    public Long getFosteredRams() {
        return fosteredRams;
    }

    public void setFosteredRams(Long fosteredRams) {
        this.fosteredRams = fosteredRams;
    }

    public Long getEweInfantMortality() {
        return eweInfantMortality;
    }

    public void setEweInfantMortality(Long eweInfantMortality) {
        this.eweInfantMortality = eweInfantMortality;
    }

    public Long getRamInfantMortality() {
        return ramInfantMortality;
    }

    public void setRamInfantMortality(Long ramInfantMortality) {
        this.ramInfantMortality = ramInfantMortality;
    }

    public Long getFosterEwes() {
        return fosterEwes;
    }

    public void setFosterEwes(Long fosterEwes) {
        this.fosterEwes = fosterEwes;
    }

    public Long getFosterRams() {
        return fosterRams;
    }

    public void setFosterRams(Long fosterRams) {
        this.fosterRams = fosterRams;
    }

    public Long getInfantMortality() {
        return infantMortality;
    }

    public void setInfantMortality(Long infantMortality) {
        this.infantMortality = infantMortality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
