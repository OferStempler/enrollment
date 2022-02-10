package ofer.stempler.enrollment.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ofer.stempler.enrollment.dao.GradeDao;
import ofer.stempler.enrollment.dao.PupilDao;
import ofer.stempler.enrollment.model.Grade;
import ofer.stempler.enrollment.model.Pupil;

@Service
public class PupilsService {

    @Autowired
    private PupilDao pupilDao;

    @Autowired
    private GradeDao gradeDao;


    public Pupil createPupil(Pupil pupil){

        for (Grade grade: pupil.getGradeList()
             ) {
            grade.setPupil(pupil);
            gradeDao.save(grade);
        }
        return pupilDao.save(pupil);

    }

    public List<Pupil> getAll(){
        return pupilDao.findAll();
    }

    public Set<Pupil> findAllFriends(long id){
        return pupilDao.findById(id).getFriendsSet();
    }


    /**
     * Sets friendship between two pupils by their id.
     * @param idA pupil a ID.
     * @param idB pupil B ID.
     */
    public void setFriendship(long idA, long idB){
       Pupil pupilA =  pupilDao.findById(idA);
       Pupil pupilB=  pupilDao.findById(idB);

        pupilA.getFriendsSet().add(pupilB);
        pupilB.getFriendsSet().add(pupilA);

        pupilDao.save(pupilA);
        pupilDao.save(pupilB);

    }
}
