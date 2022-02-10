package ofer.stempler.enrollment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ofer.stempler.enrollment.model.Pupil;
@Repository
public interface PupilDao extends JpaRepository<Pupil, java.lang.Long> {

    Pupil findById(long id);
}
