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
	Gear_New G1, G2;
	String n; 
	ArrayList<Belt> allBelts; 
	
	float sw, radius;
	float r, g, b; 
	float rotS; 
	
	String [] properties; 
	
//	Belt(PApplet parent, float X1, float X2, float X3, float X4, float Y1, float Y2, float Y3, float Y4){
	Belt(PApplet parent, Gear_New g1, Gear_New g2){
		p = parent; 
		
		G1 = g1; 
		G2 = g2; 
		
		properties = new String[6];
		properties[0] = "LineWeight";
		properties[1] = "Width";
		properties[2] = "red";
		properties[3] = "green";
		properties[4] = "blue";
		properties[5] = "Name"; 
		
		
		
		tangency(G1, G2); 
//		x1 = X1; 
//		x2 = X2;
//		x3 = X3; 
//		x4 = X4; 
//		y1 = Y1; 
//		y2 = Y2; 
//		y3 = Y3;
//		y4 = Y4; 
	}
	
	
	public void tangency(Gear_New gA, Gear_New gB) {
		if(gA.radius == gB.radius) {
	    	angle = atan((gA.yPos-gB.yPos)/(gA.xPos-gB.xPos));
	    	 
	    	x1 = gA.xPos+gA.radius*sin(angle);
	    	x2 = gA.xPos-gA.radius*sin(angle);
	    	y1 = gA.yPos-gA.radius*cos(angle);
	    	y2 = gA.yPos+gA.radius*cos(angle);
	    	
	    	x3 = gB.xPos+gB.radius*sin(angle);
	    	x4 = gB.xPos-gB.radius*sin(angle);
	    	y3 = gB.yPos-gB.radius*cos(angle);
	    	y4 = gB.yPos+gB.radius*cos(angle);
	    	
//	    	Belt b1 = new Belt(p, x1,x2,x3,x4,y1,y2,y3,y4); 
//	    	drawMe(); 
	    	
		}
		
		else{    		
    		if(gA.radius<gB.radius) {    	    		
	    		//find intersection point
	    		xP = (gB.xPos*gA.radius - gA.xPos*gB.radius)/(gA.radius-gB.radius);
	    		yP = (gB.yPos*gA.radius - gA.yPos*gB.radius)/(gA.radius-gB.radius);
	    		
	    		 x1 = ((pow(gA.radius,2) * (xP-gA.xPos) + (gA.radius*(yP-gA.yPos)) * sqrt(pow((xP-gA.xPos),2) + pow((yP-gA.yPos),2)-pow(gA.radius,2)))/(pow(xP-gA.xPos,2) + pow(yP-gA.yPos,2))) + gA.xPos;
	        	 x2 = ((pow(gA.radius,2) * (xP-gA.xPos) - (gA.radius*(yP-gA.yPos)) * sqrt(pow((xP-gA.xPos),2) + pow((yP-gA.yPos),2)-pow(gA.radius,2)))/(pow(xP-gA.xPos,2) + pow(yP-gA.yPos,2))) + gA.xPos;
	        	 y1 = ((pow(gA.radius,2) * (yP-gA.yPos) - (gA.radius*(xP-gA.xPos)) * sqrt(pow((xP-gA.xPos),2) + pow((yP-gA.yPos),2)-pow(gA.radius,2)))/(pow(xP-gA.xPos,2) + pow(yP-gA.yPos,2))) + gA.yPos;
	        	 y2 = ((pow(gA.radius,2) * (yP-gA.yPos) + (gA.radius*(xP-gA.xPos)) * sqrt(pow((xP-gA.xPos),2) + pow((yP-gA.yPos),2)-pow(gA.radius,2)))/(pow(xP-gA.xPos,2) + pow(yP-gA.yPos,2))) + gA.yPos;
	        	
	        	 x3 = ((pow(gB.radius,2) * (xP-gB.xPos) + (gB.radius*(yP-gB.yPos)) * sqrt(pow((xP-gB.xPos),2) + pow((yP-gB.yPos),2)-pow(gB.radius,2)))/(pow(xP-gB.xPos,2) + pow(yP-gB.yPos,2))) + gB.xPos;
	        	 x4 = ((pow(gB.radius,2) * (xP-gB.xPos) - (gB.radius*(yP-gB.yPos)) * sqrt(pow((xP-gB.xPos),2) + pow((yP-gB.yPos),2)-pow(gB.radius,2)))/(pow(xP-gB.xPos,2) + pow(yP-gB.yPos,2))) + gB.xPos;
	        	 y3 = ((pow(gB.radius,2) * (yP-gB.yPos) - (gB.radius*(xP-gB.xPos)) * sqrt(pow((xP-gB.xPos),2) + pow((yP-gB.yPos),2)-pow(gB.radius,2)))/(pow(xP-gB.xPos,2) + pow(yP-gB.yPos,2))) + gB.yPos;
	        	 y4 = ((pow(gB.radius,2) * (yP-gB.yPos) + (gB.radius*(xP-gB.xPos)) * sqrt(pow((xP-gB.xPos),2) + pow((yP-gB.yPos),2)-pow(gB.radius,2)))/(pow(xP-gB.xPos,2) + pow(yP-gB.yPos,2))) + gB.yPos;
	        	
//	        	Belt b1 = new Belt(p, xF1,xF2,xF3,xF4,yF1,yF2,yF3,yF4); 
//		    	drawMe();         	
    		}
    		if(gA.radius > gB.radius) {
	    		//find intersection point
	    		xP = (gA.xPos*gB.radius - gB.xPos*gA.radius)/(gB.radius-gA.radius);
	    		yP = (gA.yPos*gB.radius - gB.yPos*gA.radius)/(gB.radius-gA.radius);
	    		
	    		 x1 = ((pow(gB.radius,2) * (xP-gB.xPos) + (gB.radius*(yP-gB.yPos)) * sqrt(pow((xP-gB.xPos),2) + pow((yP-gB.yPos),2)-pow(gB.radius,2)))/(pow(xP-gB.xPos,2) + pow(yP-gB.yPos,2))) + gB.xPos;
	        	 x2 = ((pow(gB.radius,2) * (xP-gB.xPos) - (gB.radius*(yP-gB.yPos)) * sqrt(pow((xP-gB.xPos),2) + pow((yP-gB.yPos),2)-pow(gB.radius,2)))/(pow(xP-gB.xPos,2) + pow(yP-gB.yPos,2))) + gB.xPos;
	        	 y1 = ((pow(gB.radius,2) * (yP-gB.yPos) - (gB.radius*(xP-gB.xPos)) * sqrt(pow((xP-gB.xPos),2) + pow((yP-gB.yPos),2)-pow(gB.radius,2)))/(pow(xP-gB.xPos,2) + pow(yP-gB.yPos,2))) + gB.yPos;
	        	 y2 = ((pow(gB.radius,2) * (yP-gB.yPos) + (gB.radius*(xP-gB.xPos)) * sqrt(pow((xP-gB.xPos),2) + pow((yP-gB.yPos),2)-pow(gB.radius,2)))/(pow(xP-gB.xPos,2) + pow(yP-gB.yPos,2))) + gB.yPos;
	        	
	        	 x3 = ((pow(gA.radius,2) * (xP-gA.xPos) + (radius*(yP-gA.yPos)) * sqrt(pow((xP-gA.xPos),2) + pow((yP-gA.yPos),2)-pow(gA.radius,2)))/(pow(xP-gA.xPos,2) + pow(yP-gA.yPos,2))) + gA.xPos;
	        	 x4 = ((pow(gA.radius,2) * (xP-gA.xPos) - (radius*(yP-gA.yPos)) * sqrt(pow((xP-gA.xPos),2) + pow((yP-gA.yPos),2)-pow(gA.radius,2)))/(pow(xP-gA.xPos,2) + pow(yP-gA.yPos,2))) + gA.xPos;
	        	 y3 = ((pow(gA.radius,2) * (yP-gA.yPos) - (radius*(xP-gA.xPos)) * sqrt(pow((xP-gA.xPos),2) + pow((yP-gA.yPos),2)-pow(gA.radius,2)))/(pow(xP-gA.xPos,2) + pow(yP-gA.yPos,2))) + gA.yPos;
	        	 y4 = ((pow(gA.radius,2) * (yP-gA.yPos) + (radius*(xP-gA.xPos)) * sqrt(pow((xP-gA.xPos),2) + pow((yP-gA.yPos),2)-pow(gA.radius,2)))/(pow(xP-gA.xPos,2) + pow(yP-gA.yPos,2))) + gA.yPos;
	        	        	
	
//	        	Belt b1 = new Belt(p, xF1,xF2,xF3,xF4,yF1,yF2,yF3,yF4); 
//		    	drawMe();    	
    		}
    	}
	}//end of tangency
	
	
	
	
	public void draw() {
		tangency(G1, G2); 
		p.pushStyle(); 
		p.strokeWeight(2);
		p.line(x1,y1,x3,y3);
    	p.line(x2,y2,x4,y4); 
    	p.popStyle(); 
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

