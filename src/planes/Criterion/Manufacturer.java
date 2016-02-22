package planes.Criterion;

import planes.Plane;

public class Manufacturer extends Criteria {

	private String name;

	public Manufacturer(String n){
		name = n;
	}
	
	@Override
	public int evaluate(Plane userPlane) {
		if(userPlane.getManufacturer().equals("")){
			System.out.println("PASNORMAL");
			return -1;
		}else{
			if(name.toLowerCase().contains(userPlane.getManufacturer().toLowerCase())){
				return 0;
			}
			else
				return 100;
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
