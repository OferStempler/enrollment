package ofer.stempler.enrollment.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ofer.stempler.enrollment.dao.SchoolDao;
import ofer.stempler.enrollment.model.Pupil;
import ofer.stempler.enrollment.model.School;

@Service
public class SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    public long createSchool(School school){
        return schoolDao.save(school).getId();
    }

    public Set<Pupil> getAllPupilsFromSchool(long schoolId){
        School school = schoolDao.findById(schoolId);
        return school.getPupils();
    }
}
