package ofer.stempler.enrollment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ofer.stempler.enrollment.model.Grade;

@Repository
public interface GradeDao extends JpaRepository<Grade, Long> {
}
