package no.tango.tangolib.controller.dto;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class ArtistExternalInformation {

    @Basic
    private String source;

    @Basic
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birth;

    @Temporal(TemporalType.DATE)
    private Date death;

    @ElementCollection
    private List<String> otherNames;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public List<String> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
    }
}
