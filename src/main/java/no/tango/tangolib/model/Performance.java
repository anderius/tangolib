package no.tango.tangolib.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Performance extends BaseEntity {

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Work work;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "performance", cascade = CascadeType.ALL)
    private List<PerformanceContribution> contributions;

    public List<PerformanceContribution> getContributions() {
        if (contributions == null) {
            contributions = new ArrayList<PerformanceContribution>();
        }
        return contributions;
    }

    public void setContributions(List<PerformanceContribution> contributions) {
        this.contributions = contributions;
    }

    public PerformanceContribution getPerformanceContribution(PerformanceContributionType type) {
        for (PerformanceContribution performanceContribution : getContributions()) {
            if (type == performanceContribution.getType()) {
                return performanceContribution;
            }
        }
        // ingen funnet, helt ok:
        return null;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }
}
