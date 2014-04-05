package no.tango.tangolib.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Work extends BaseEntity {

    @Basic
    private String title;

    @ManyToOne
    private Artist composer;

    @OneToMany(mappedBy = "work", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Performance> performances;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getComposer() {
        return composer;
    }

    public void setComposer(Artist composer) {
        this.composer = composer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }
}
