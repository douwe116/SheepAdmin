package nl.vissersuwald.sheepadmin.models.farming;

import jakarta.persistence.*;

@Entity
@Table(name="activeparameters", schema="farming")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "parameterName", unique = true, nullable = false)
    protected String parameterName;
    @Column(name = "parameterValue")
    protected String parameterValue;
    @Column(name = "parameterValueInt")
    protected Long parameterValueInt;

    public Parameter(String parameterName, String parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

    public Parameter(String parameterName, Long parameterValueInt) {
        this.parameterName = parameterName;
        this.parameterValueInt = parameterValueInt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public Long getParameterValueInt() {
        return parameterValueInt;
    }

    public void setParameterValueInt(Long parameterValueInt) {
        this.parameterValueInt = parameterValueInt;
    }
}
