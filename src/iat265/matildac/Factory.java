package iat265.matildac;

import iat265.aga53.MechanismFactory;
import iat265.aga53.Scrubbable;
import processing.core.PApplet;


public class Factory implements MechanismFactory{
	
	//no constructor allowed here	
	public Scrubbable getMechanism(PApplet p) {
		return new Gear_New(p,340, 200, 40, 1); 
	}

}
