package no.tango.tangolib.model;

import javax.persistence.*;

@Entity
public class SoundFile extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String path;

    /**
     * seconds
     */
    @Basic
    @Column(nullable = false)
    private Long length;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Performance performance;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Artist getLeader() {
        PerformanceContribution contribution =  performance.getPerformanceContribution(PerformanceContributionType.LEADER);
        if (contribution != null) {
            return contribution.getArtist();
        } else {
            return null;
        }
    }

    public String getTitle() {
        return performance.getWork().getTitle();
    }
}
