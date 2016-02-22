package planes.Criterion;

import planes.Plane;

public class Engine_nb extends Criteria {
	
	private int nb;
	
	public Engine_nb(int nb){
		this.nb = nb;
	}

	@Override
	public int evaluate(Plane userPlane) {
		Engine_nb userEngine_nb = (Engine_nb) userPlane.getEngine_nbCrit();
		if(nb==0 || userEngine_nb.getEngine_nb()==0)
			return -1;
		else
			return Ponderation.ENGINE_NB * (Math.abs(userEngine_nb.getEngine_nb()-nb)*100)/userEngine_nb.getEngine_nb();
	}
	
	public int getEngine_nb(){
		return this.nb;
	}

	@Override
	public String toString() {
		if(nb>0) return Integer.toString(nb);
		else return "-";
	}
	
	
}
