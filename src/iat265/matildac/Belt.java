package iat265.matildac;

import java.util.ArrayList;
import java.util.Iterator;

import iat265.aga53.Scrubbable;
import processing.core.PApplet;

public class Belt extends PApplet implements Scrubbable{

	PApplet p;
	float angle;
	float x1,x2,x3,x4;
	float y1,y2,y3,y4;
	float xP, yP;
	Gear G1, G2;
	String n; 
	ArrayList<Belt> allBelts; 
	
	float sw, radius;
	float r, g, b; 
	float rotS; 
	
	String [] properties; 
	
	Belt(PApplet parent, float X1, float X2, float X3, float X4, float Y1, float Y2, float Y3, float Y4){
//	Belt(PApplete Gear_New g1, Gear_New g2){
		p = parent; 
		
		properties = new String[6];
		properties[0] = "LineWeight";
		properties[1] = "Width";
		properties[2] = "red";
		properties[3] = "green";
		properties[4] = "blue";
		properties[5] = "Name"; 
		
		x1 = X1; 
		x2 = X2;
		x3 = X3; 
		x4 = X4; 
		y1 = Y1; 
		y2 = Y2; 
		y3 = Y3;
		y4 = Y4; 
	}
	
	public void drawMe() {
		p.line(x1,y1,x3,y3);
    	p.line(x2,y2,x4,y4); 
	}
	
	public String getName() {
		return n; 
	}
	
	public void setName(String name) {
		n = name; 
	}
	
	public String[] getProperties() {
		return properties; 
	}
	
	public void setParameter(String name, float value) {
		int c = -1; 
		for(int i = 0; i<properties.length; i++) {
			if(name.equals(properties[i])) {
				println("Found it"); 
				c = i; 
			}
		}		
		switch(c) {
		  case 0: 
			  sw = value; 
		    break;
		  case 1: 
			  radius = value; 
		    break;
		  case 2: 
			  r = value;
		    break;
		  case 3: 
			  g = value; 
		    break;
		  case 4:
			  b = value; 
			 break;
		  case 5:
			  n = str(value);  ///shouldn't be here
			  break;
		  case 6:
			  rotS = value; 
			  break;
		  default:             
		    println("None");   
		    break;
		}	
	}
	
	public float getParameter(String name) {
		if(name.equals("LineWeight")){
			return sw;
		}
		else if(name.equals("Width")) {
			return radius;
		}
		else if(name.equals("red")) {
			return r; 
		}
		else if(name.equals("green")) {
			return g; 
		}
		else if(name.equals("blue")) {
			return b; 
		}
		else if(name.equals("Rotation")) {
			return rotS; 
		}
		else {
			return 0; 
		} 

	}
	
	public Scrubbable pick(int mouseX, int mouseY) {
		return null; 
	}
	
	public Iterator createIterator() {
		return null; 
	}
	
	
}

