package planes.Criterion;

import planes.Plane;

public class Model extends Criteria {

	private String name;
	
	public Model(String name){
		this.name = name;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		if(userPlane.getModel().equals(""))
			return -1;
		if(name.toLowerCase().contains(userPlane.getModel().toLowerCase()))
			return 0;
		else
			return -1;
	}

	@Override
	public String toString() {
		return name;
	}
}
