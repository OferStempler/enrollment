package ofer.stempler.enrollment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import ofer.stempler.enrollment.dao.PupilDao;
import ofer.stempler.enrollment.dao.SchoolDao;
import ofer.stempler.enrollment.model.School;

@DataJpaTest
@Sql("/data.sql")
@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {


    @Autowired
    private PupilDao pupilDao;
    @Autowired
    private SchoolDao schoolDao;


    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    public void testCreateBestSchoolsMap() {
        TreeMap<Double, School> bestSchoolMap = enrollmentService.createBestSchoolsMap(schoolDao.findAll(), pupilDao.findById(1));

        assertEquals(3, bestSchoolMap.size());
    }

    @Test
    public void testFindBestSchool(){
        TreeMap<Double, School> bestSchoolMap = new TreeMap<>();
        bestSchoolMap.put(3.0, schoolDao.findAll().get(0));
        bestSchoolMap.put(2.0, schoolDao.findAll().get(1));
        bestSchoolMap.put(1.0, schoolDao.findAll().get(2));

        School school = enrollmentService.findBestSchool(new TreeMap<>(bestSchoolMap), 99.0);
        assertEquals(1, school.getId());

        School school2 = enrollmentService.findBestSchool(new TreeMap<>(bestSchoolMap), 85.0);
        assertEquals(3, school2.getId());
    }
}
