package iat265.matildac;
import java.util.ArrayList;
import iat265.aga53.Scrubbable;
import java.util.Iterator;

public class mIterator implements Iterator{
	
	ArrayList<Gear_New> mech = new ArrayList<Gear_New>(); 
	ArrayList<Belt> mechB = new ArrayList<Belt>(); 
	Gear_New gears; 
	Belt belts;
	int pos = 0; 
	Scrubbable type; 
	
	public mIterator(Scrubbable part) {
		type = part; 
		if(part instanceof Gear_New) {
			gears = (Gear_New) part; 
			while(gears.d <5 ) {
				System.out.println(gears.d);
				mech.add(this.gears); 
				if(this.gears.left!= null) {
					this.gears = this.gears.left; 
				}
				else {
					break;
				}
			}
		}
		if(part instanceof Belt) {
			belts = (Belt)part; 
			while(belts.d < 5) {
				System.out.println(belts.d);
				mechB.add(belts); 
				if(belts.left != null) {
					belts = belts.left; 
				}
			}
		}

	}
	
	public boolean hasNext() {
		if(type instanceof Gear_New) {
			if(pos >= mech.size()) {
				return false;
			}
			else {
				return true; 
			}
		}
		else if(type instanceof Belt) {
			if(pos>=mechB.size()) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false; 
		}
	}
	
	public Scrubbable next() {
		if(type instanceof Gear_New) {
			Scrubbable g = mech.get(pos); 
			pos = pos +1;
			return g; 
		}
		else if(type instanceof Belt) {
			Scrubbable bb = mechB.get(pos);
			pos = pos +1;
			return bb; 
		}
		else {
			return null; 
		}

		
	}
	
	public void remove() {
		
	}

}
