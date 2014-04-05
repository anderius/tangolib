package no.tango.tangolib.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WorkContribution extends BaseEntity {

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private WorkContributionType contributionType;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Work work;

    public WorkContribution() {
    }

    public WorkContribution(Artist artist, WorkContributionType contributionType, Work work) {
        this.contributionType = contributionType;
        this.artist = artist;
        this.work = work;
    }

    public WorkContribution(Artist artist, WorkContributionType contributionType, Work work, Date date) {
        this.date = date;
        this.contributionType = contributionType;
        this.artist = artist;
        this.work = work;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public WorkContributionType getContributionType() {
        return contributionType;
    }

    public void setContributionType(WorkContributionType contributionType) {
        this.contributionType = contributionType;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }
}
