package ofer.stempler.enrollment.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql("/data.sql")
public class DaoTest {

    @Autowired
    private PupilDao pupilDao;

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private GradeDao gradeDao;

    @Test
    public void testPupilDao(){
        assertEquals(5, pupilDao.findAll().size());
    }

    @Test
    public void testSchoolDao(){
        assertEquals(3, schoolDao.findAll().size());
    }

    @Test
    public void testGradeDao(){
        assertEquals(5, gradeDao.findAll().size());
    }
}
