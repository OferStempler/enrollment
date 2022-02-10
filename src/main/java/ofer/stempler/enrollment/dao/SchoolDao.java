package ofer.stempler.enrollment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ofer.stempler.enrollment.model.School;
@Repository
public interface SchoolDao extends JpaRepository<School, Long> {
    School findById(long id);
}
