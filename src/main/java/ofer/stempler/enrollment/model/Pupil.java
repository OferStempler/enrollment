package ofer.stempler.enrollment.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "PUPIL")
public class Pupil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SCHOOL_ID")
    private School school;

    @OneToMany(mappedBy = "pupil", cascade = {CascadeType.MERGE})
    private Set<Grade> gradeList = new HashSet<>();


    @JsonBackReference
    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="PUPIL_FRIENDS",
            joinColumns={@JoinColumn(name="PUPIL_ID")},
            inverseJoinColumns={@JoinColumn(name="PUPIL_FRIEND_ID")})
    private Set<Pupil> friendsSet = new HashSet<>();

    @ManyToMany(mappedBy="friendsSet")
    private Set<Pupil> friends = new HashSet<>();

    @Column
    private double lat;
    @Column
    private double lon;

    /**
     * Calculates the GPE from all of the pupils courses.
     * @return the average GPA.
     */
    public double calculateGPE() {
        Integer sum = getGradeList().stream().map(grade -> grade.getGrade())
                .reduce(0, Integer::sum);
        return sum / getGradeList().size();
    }

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

    public Set<Pupil> getFriendsSet() {
        return friendsSet;
    }

    public void setFriendsSet(Set<Pupil> friendsList) {
        this.friendsSet = friendsList;
    }

    public Pupil(java.lang.Long id, School school) {
        this.id = id;
        this.school = school;
    }

    public Pupil() {
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Set<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(Set<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "id=" + id +
                ", school=" + school +
                ", gradeList=" + gradeList +
                ", friendsSet=" + friendsSet +
                ", friends=" + friends +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
