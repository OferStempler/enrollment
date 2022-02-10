package ofer.stempler.enrollment.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ofer.stempler.enrollment.dao.PupilDao;
import ofer.stempler.enrollment.dao.SchoolDao;
import ofer.stempler.enrollment.model.Pupil;
import ofer.stempler.enrollment.model.School;
import ofer.stempler.enrollment.utils.HaversineUtil;

@Service
public class EnrollmentService {

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private PupilDao pupilDao;


    /**
     * Enrolls a pupil to best school according to the required criteria.
     * @param pupilId pupilId
     */
    public void enrollPupil(long pupilId){
        Pupil pupil = pupilDao.findById(pupilId);
        List<School> schools = schoolDao.findAll();

        TreeMap<Double, School> bestSchoolMap = createBestSchoolsMap(schools, pupil);
        School selectedSchool = findBestSchool(bestSchoolMap, pupil.calculateGPE());

        //TODO - handle null schools
        pupil.setSchool(selectedSchool);
        pupilDao.save(pupil);
        System.out.println("Enrolling pupil to school: " + selectedSchool);
    }

    /**
     * Calculates the required formula according to number of firends in the school,
     * divided by the distance as obtained from the Haversine formula.
     * @param schools List of all schools
     * @param pupil pupil to be enrolled.
     * @return A treeMap with the calculated formula as key, and school as value.
     */
    protected TreeMap<Double, School> createBestSchoolsMap(List<School> schools, Pupil pupil) {

        TreeMap<Double, School> bestSchoolMap = new TreeMap<>();
        //for each school:
        for (School school:schools) {
            Set<Pupil> schoolPupils = school.getPupils();

            //find the number of friends in the school
            Set<Pupil> commonFriends = new HashSet<>(pupil.getFriendsSet());
            commonFriends.retainAll(schoolPupils);

            //Obtain the distance
            double km = HaversineUtil.haversine(pupil.getLat(), pupil.getLon(), school.getLat(), school.getLon());

            //get the required number
            double sweetNumber = commonFriends.size() / km;

            //place all schools and their number on a treeMap
            bestSchoolMap.put(sweetNumber, school);
        }
        return bestSchoolMap;
    }

    /**
     * Finds the school with the best calculated required formula, and that meets the GPA and max pupils criteria.
     * @param schoolMap with all the schools and their magic number.
     * @param gpa of the pupil.
     * @return The best school that meets the criteria, or null if none was found.
     */
    protected School findBestSchool(TreeMap<Double, School> schoolMap, double gpa) {
        School school =null;
        if (schoolMap.size() > 0) {
             school = schoolMap.lastEntry().getValue();

            if (school.getPupils().size() <= school.getMaxNumberOfPupils() &&
                    gpa > school.getMinimumGpa()
            ) {
                return school;
            } else {
                schoolMap.pollLastEntry();
                school = findBestSchool(schoolMap, gpa);
            }
        }
        return school;
    }

}
