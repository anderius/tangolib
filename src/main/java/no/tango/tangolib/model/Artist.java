package no.tango.tangolib.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Artist extends BaseEntity {

    @Basic
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birth;

    @Temporal(TemporalType.DATE)
    private Date death;

    @ElementCollection
    private List<String> otherNames;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PerformanceContribution> performanceContributions;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkContribution> workContributions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PerformanceContribution> getPerformanceContributions() {
        return performanceContributions;
    }

    public void setPerformanceContributions(List<PerformanceContribution> performanceContributions) {
        this.performanceContributions = performanceContributions;
    }

    public List<WorkContribution> getWorkContributions() {
        return workContributions;
    }

    public void setWorkContributions(List<WorkContribution> workContributions) {
        this.workContributions = workContributions;
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
