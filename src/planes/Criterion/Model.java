package planes.Criterion;

import planes.Plane;

public class Model extends Criteria {

	private String name;
	
	public Model(String name){
		this.name = name;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		if(name.equals("") || userPlane.getModel().toString().equals(""))
			return -1;
		else {
			if(name.contains(userPlane.getModel().toString()))
				return 0;
			else
				return Ponderation.MODEL * 100;
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
