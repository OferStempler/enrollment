package ofer.stempler.enrollment.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "SCHOOL")
public class School {

    @Id
    @Column(name = "SCHOOL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private double minimumGpa;
    @Column
    private int maxNumberOfPupils;

    @Column
    private double lat;
    @Column
    private double lon;

    @JsonBackReference
    @OneToMany(mappedBy = "school", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Pupil> pupils;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public School() {
    }

    public long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public double getMinimumGpa() {
        return minimumGpa;
    }

    public void setMinimumGpa(double minimumGpa) {
        this.minimumGpa = minimumGpa;
    }

    public int getMaxNumberOfPupils() {
        return maxNumberOfPupils;
    }

    public void setMaxNumberOfPupils(int maxNumberOfPupils) {
        this.maxNumberOfPupils = maxNumberOfPupils;
    }

    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupilsList) {
        this.pupils = pupilsList;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", minimumGpa=" + minimumGpa +
                ", maxNumberOfPupils=" + maxNumberOfPupils +
                ", pupilsList=" + pupils +
                '}';
    }
}
