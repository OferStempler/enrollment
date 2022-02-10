package ofer.stempler.enrollment.controler;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ofer.stempler.enrollment.model.Pupil;
import ofer.stempler.enrollment.model.School;
import ofer.stempler.enrollment.services.EnrollmentService;
import ofer.stempler.enrollment.services.MapService;
import ofer.stempler.enrollment.services.PupilsService;
import ofer.stempler.enrollment.services.SchoolService;

@RestController
public class EnrollmentControler {

    @Autowired
    private PupilsService pupilsService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private MapService mapService;

    @PostMapping( value = "/pupil", consumes = "application/json")
    public long createPupil(@RequestBody Pupil pupil){
      return pupilsService.createPupil(pupil).getId();
    }

    @PostMapping( value = "/school", consumes = "application/json")
    public long createSchool(@RequestBody School school){
       return schoolService.createSchool(school);
    }

    @PostMapping("/setFriendShip/{firstPupilId}/{secondPupilId}")
    public void setFriendship(@PathVariable long firstPupilId, @PathVariable long secondPupilId){
        pupilsService.setFriendship(firstPupilId, secondPupilId);
    }

    @PostMapping("/enroll/{pupilId}")
    public void enrollPupil(@PathVariable long pupilId){
        enrollmentService.enrollPupil(pupilId);
    }


    @GetMapping( value = "/getAllPupilsFromSchool/{id}")
    public Set<Pupil> getFromSchool(@PathVariable long id){
        return schoolService.getAllPupilsFromSchool(id);
    }

    @GetMapping("/getAllPupils")
    public List<Pupil> getAllPupils(){
        return pupilsService.getAll();
    }

    @GetMapping("/getAllFriends/{id}")
    public Set<Pupil> getAllFriends(@PathVariable long id){
        return pupilsService.findAllFriends(id);
    }

    @GetMapping("/getMap")
    public String getMap(){
        return mapService.getMap();
    }

}
