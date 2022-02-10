package ofer.stempler.enrollment.model.map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Geometry {

    private String type;
    private double[] coordinates;

}
