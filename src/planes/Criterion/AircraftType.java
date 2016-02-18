package planes.Criterion;

import planes.Plane;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alexandrecetto on 16/02/2016.
 */
public class AircraftType extends Criteria {

    private static List<String> types = Arrays.asList("Glider", "Balloon", "Blimp/Dirigible", "Fixed Wing single engine", "Fixed wing multi engine", "Rotorcraft",
            "Weight-Shift-Control", "Powered Parachute", "Gyroplane");

    String aircraftType;

    public AircraftType(int type){
        if(type<0 || type>=types.size() || type==9)
            this.aircraftType = types.get(0);
        else
            this.aircraftType = types.get(type);
    }

    public AircraftType(String type){
        if(type!=null && types.contains(type))
            this.aircraftType = type;
        else
            this.aircraftType = types.get(0);
    }

    @Override
    public int evaluate(Plane userPlane) {
        if(aircraftType.equals(userPlane.getAicraftType().toString()))
            return 0;
        else
            return Ponderation.ENGINE * 100;
    }
}
