package ofer.stempler.enrollment.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ofer.stempler.enrollment.dao.PupilDao;
import ofer.stempler.enrollment.dao.SchoolDao;
import ofer.stempler.enrollment.model.map.Feature;
import ofer.stempler.enrollment.model.Pupil;
import ofer.stempler.enrollment.model.School;

@Service
public class MapService {

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private PupilDao pupilDao;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String FEATURE = "Feature";
    private static final String POINT = "Point";
    private static final String CIRCLE = "Circle";

    /**
     * Creates an htmpl page with all the students and school coordinates.
     * @return the html.
     */
    public String getMap(){
        String features = null;
        try {

             features = createFeatureArray();
        } catch (Exception e){
            System.out.println("Error while parsing json. " + e);
        }
        String mapHtml = loadFile("/templates/mapTemplate2");
        return mapHtml.replace("XXX", features);
    }

    /**
     * Maps the pupils and school object into a Feature object that can be used in the html.
     * The Feateus list is converted to string json in the end.
     * @return json string
     * @throws JsonProcessingException
     */
    private String createFeatureArray() throws JsonProcessingException {
        List<Feature> features = new ArrayList<>();

        List<School> schools = schoolDao.findAll();
        List<Pupil> pupils = pupilDao.findAll();

        for (School school: schools) {
            Feature feature = new Feature();
            feature.setType(FEATURE);
            feature.getGeometry().setType(POINT);
            feature.getGeometry().setCoordinates(new double[]{school.getLat(), school.getLon()});
            feature.getProperties().setTitle("school " + school.getId());

            features.add(feature);
        }

        for (Pupil pupil: pupils) {
            Feature feature = new Feature();
            feature.setType(FEATURE);
            feature.getGeometry().setType(POINT);
            feature.getGeometry().setCoordinates(new double[]{pupil.getLat(), pupil.getLon()});
            feature.getProperties().setTitle("pupil " + pupil.getId());

            features.add(feature);
        }

        return objectMapper.writeValueAsString(features);
    }


    /**
     * Loads the temmplate from resources folder.
     * @param filename
     * @return
     */
    public static String loadFile(String filename) {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(MapService.class.getResourceAsStream(filename)))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Error loading challenge template '%s'.", filename), e);
        }
    }
}
