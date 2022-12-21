package nl.vissersuwald.sheepadmin.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@IdClass(BirthDetailsKey.class)
@Table(name="births", schema="farming")
public class BirthDetails {
    @Column(name = "id")
    private Long id;
    @Id
    @Column(name ="yearofbirth")
    private Long yearOfBirth;
    @Id
    @Column(name ="motherid")
    private Long motherId;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns(
    {
            @JoinColumn(updatable = false, insertable = false, name = "motherid", referencedColumnName = "motherid"),
            @JoinColumn(updatable = false, insertable = false, name = "yearofbirth", referencedColumnName = "yearofbirth")
    }
    )
    private List<Sheep> childList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns(
            {
                    @JoinColumn(updatable = false, insertable = false, name = "fostermotherid", referencedColumnName = "motherid"),
                    @JoinColumn(updatable = false, insertable = false, name = "yearofbirth", referencedColumnName = "yearofbirth")
            }
    )
    private List<Sheep> fosterChildList;

    public BirthDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Long yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
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

    public Long getMotherId() {
        return motherId;
    }

    public void setMotherId(Long motherId) {
        this.motherId = motherId;
    }

    public List<Sheep> getChildList() {
        return childList;
    }

    public void setChildList(List<Sheep> childList) {
        this.childList = childList;
    }

    public List<Sheep> getFosterChildList() {
        return fosterChildList;
    }

    public void setFosterChildList(List<Sheep> fosterChildList) {
        this.fosterChildList = fosterChildList;
    }
}
