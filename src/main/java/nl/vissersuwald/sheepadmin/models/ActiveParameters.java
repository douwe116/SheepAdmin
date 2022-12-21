package nl.vissersuwald.sheepadmin.models;

import javax.persistence.*;

@Entity
@Table(name="activeparameters", schema="farming")
public class ActiveParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "paraname")
    protected String paraName;
    @Column(name = "paravalue")
    protected String paraValue;
    @Column(name = "paravalueint")
    protected Long paraValuesInt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
    }

    public Long getParaValuesInt() {
        return paraValuesInt;
    }

    public void setParaValuesInt(Long paraValuesInt) {
        this.paraValuesInt = paraValuesInt;
    }
}
