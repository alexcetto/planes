package planes.Criterion;

import planes.Plane;

import java.util.Arrays;
import java.util.List;

public class Type extends Criteria {

    private static List<String> types = Arrays.asList("Glider", "Balloon", "Blimp/Dirigible",
            "Fixed wing single engine", "Fixed wing multi engine", "Rotorcraft",
            "Weight-shift-control", "Powered Parachute", "Gyroplane");

    private String type;

    public Type(int type){
        if(type<0 || type>=types.size())
            this.type = "-";
        else
            this.type = types.get(type);
    }

    public Type(String type){
        if(type!=null && types.contains(type))
            this.type = type;
        else
            this.type = "-";
    }

    @Override
    public int evaluate(Plane userPlane) {
    	if(type.equals("-") || userPlane.getType().equals("-"))
			return -1;
		else {
			if(type.equals(userPlane.getType()))
				return 0;
			else
				return Ponderation.TYPE * 100;
		}
    }
	
	public int getPoids(){
		return Ponderation.TYPE;
	}

    @Override
    public String toString() {
        return type;
    }
}

