package iat265.matildac;
import java.util.ArrayList;
import java.util.Iterator;

public class mIterator implements Iterator<Gear_New>{
	
	ArrayList<Gear_New> gears; 
	int pos = 0; 
	
	public mIterator(ArrayList<Gear_New> gears) {
		this.gears = gears; 
	}
	
	public boolean hasNext() {
		if(pos>gears.size() ||  gears.get(pos) ==  null) {
			return false; 
		}
		else {
			return true; 
		}	
		
	}
	
	public Gear_New next() {
		Gear_New g = gears.get(pos); 
		pos = pos +1; 
		return g; 
	}
	
//	public boolean hasNext() {
//		return false; 
//	}
//	
//	public Gear_New next() {
//		return null;
//				
//	}
//	
//	public void remove() {
//		
//	}

}
