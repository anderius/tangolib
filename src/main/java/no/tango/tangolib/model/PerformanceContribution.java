package no.tango.tangolib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class PerformanceContribution extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Performance performance;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Artist artist;

    @Enumerated(EnumType.STRING)
    private PerformanceContributionType type;

    public PerformanceContribution() {
    }

    public PerformanceContribution(Artist artist, PerformanceContributionType type, Performance performance) {
        this.performance = performance;
        this.artist = artist;
        this.type = type;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public PerformanceContributionType getType() {
        return type;
    }

    public void setType(PerformanceContributionType type) {
        this.type = type;
    }
}
