package nl.vissersuwald.sheepadmin.dto;

import java.util.Date;

public class SheepListDto {
    private Long id;
    private String name;
    private Date dateOfBirth;
    private Long fatherId;
    private Long motherId;
    private Long fosterMotherId;
    private Integer yearOfBirth;
    private Date dateOfDeath;
    private String reasonOfDeath;
    private String colour;
    private String oldName;
    private String boughtFrom;
    private String gender;
    private Long lambCountThisYear;
    private Long lambCountPreviousYears;

    public SheepListDto(Long id, String name, Date dateOfBirth, Long fatherId, Long motherId, Long fosterMotherId, Integer yearOfBirth, Date dateOfDeath, String reasonOfDeath, String colour, String oldName, String boughtFrom, String gender, Long lambCountThisYear, Long lambCountPreviousYears) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.fosterMotherId = fosterMotherId;
        this.yearOfBirth = yearOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.reasonOfDeath = reasonOfDeath;
        this.colour = colour;
        this.oldName = oldName;
        this.boughtFrom = boughtFrom;
        this.gender = gender;
        this.lambCountThisYear = lambCountThisYear;
        this.lambCountPreviousYears = lambCountPreviousYears;
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

    public Long getFosterMotherId() {
        return fosterMotherId;
    }

    public void setFosterMotherId(Long fosterMotherId) {
        this.fosterMotherId = fosterMotherId;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
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

    public Long getLambCountThisYear() {
        return lambCountThisYear;
    }

    public void setLambCountThisYear(Long lambCountThisYear) {
        this.lambCountThisYear = lambCountThisYear;
    }

    public Long getLambCountPreviousYears() {
        return lambCountPreviousYears;
    }

    public void setLambCountPreviousYears(Long lambCountPreviousYears) {
        this.lambCountPreviousYears = lambCountPreviousYears;
    }
}
