package ofer.stempler.enrollment.model.map;

import lombok.Data;

@Data
public class Feature {

    private String type;
    private Geometry geometry = new Geometry();
    private Properties properties = new Properties();
}
