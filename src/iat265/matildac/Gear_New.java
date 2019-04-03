package iat265.matildac;

import java.util.ArrayList;
import java.util.Iterator;
import iat265.aga53.Scrubbable;
import processing.core.PApplet;
import processing.core.PVector;

public class Gear_New extends PApplet implements Scrubbable {

	PApplet p; //PApplet
	float xPos, yPos; //position of gear
	PVector pos, end; // 
	
	ArrayList<Gear_New> allGear;
	
	float rotS; //rotation speed
	
	float radius; //width
	Gear_New left, right; //next gears
	Belt B; 	//belt
	float r, g, b; 
	float sw; //stroke weight
	
	//for tangency function
	float angle; 
	float x1,x2,x3,x4; // x coordinates of belts
	float y1,y2,y3,y4; // y coordinates of belts
	float xP, yP; 
	
	String [] properties; 
	int numProp = 9;  
	
	String n ; 
	
	public Gear_New(PApplet parent, float x, float y, float r, int depth){
		
		n = "Gear" + depth; // name of gear
		properties = new String[numProp]; 
		properties[0] = "LineWeight";
		properties[1] = "Width";
		properties[2] = "red";
		properties[3] = "green";
		properties[4] = "blue";
		properties[5] = "XPos";
		properties[6] = "YPos"; 
		properties[7] = "Name"; 
		properties[8] = "Rotation"; 
		
		p = parent;
		xPos = x;
		yPos = y;
		radius = r;
		this.r = 0;
		this.g = 0; 
		this.b = 0; 
		sw = 1; 
		rotS = 1; 
		
		//allGear.add(this); 
				
		if(depth<4) {
			//println(depth);
			if(depth==3) {
				left = new Gear_New(p,xPos-40,yPos-180, radius, depth+1);
			}
			else if(depth==1) {
				left = new Gear_New(p,xPos+60,yPos+200, (float) (radius * 0.7), depth+1);
			}
			else if(depth == 2) {
				left = new Gear_New(p,xPos+120,yPos+50, (float) (radius * 0.5), depth+1);	
				right = new Gear_New(p,xPos-80,yPos+120, (float) (radius * 0.5), 5);				
			}
			else if(depth == 0) { //first gear
				left = new Gear_New(p,xPos,yPos, 50, depth+1); 
				
			}
			
		}
		
		
	}
	
	public String getName() {
		return(n + "  " );		
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
			  xPos = value;
			  break;
		  case 6:
			  yPos = value; 
			  break;
		  case 7:
			  n = str(value); 
			  break;
		  case 8:
			  rotS = value; 
			  break;
		  default:             
		    println("None");   
		    break;
		}	
	}
	
	public float getParameter(String name) {
//		properties[0] = "LineWeight";
//		properties[1] = "Width";
//		properties[2] = "red";
//		properties[3] = "green";
//		properties[4] = "blue";
//		properties[5] = "XPos";
//		properties[6] = "YPos"; 
//		properties[7] = "Name"; 
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
		else if(name.equals("XPos")) {
			return xPos; 
		}
		else if(name.equals("YPos")) {
			return yPos;
		}
		else if(name.equals("Rotation")) {
			return rotS; 
		}
		else {
			return 0; 
		}
		
	}
	
	public void draw() {
		p.pushMatrix();
		p.pushStyle();
			p.translate(xPos, yPos);
			p.rotate(rotS); 
			p.stroke(r,g,b); 		
			p.strokeWeight(sw); 
			p.ellipse(0, 0, 2*radius, 2*radius);
			p.line(0, 0, radius, 0);
			p.line(0, 0, 0, radius);
			p.line(0, 0, -radius, 0);
			p.line(0, 0, 0, -radius);
			p.ellipse(0,0,radius,radius);
			rotS+= 0.1; 
		p.popStyle();
		p.popMatrix();

		
	    if (left != null) {	    	
	       	tangency(left); 	    	
	        left.draw();	        
	    }
	    if (right!=null) {	    		
	    	tangency(right); 	    	
	        right.draw();
	    }
	}
	
	public Scrubbable pick (int mouseX, int mouseY) {
		if(mouseX<xPos+radius &&mouseX>xPos-radius && mouseY<yPos+radius && mouseY>yPos-radius) {
			println(" selected ");
			return this; 
		}
		else {
			Gear_New g =  null; 
			if(left!= null) {
				g = (Gear_New) left.pick(mouseX, mouseY);
			}
			if(g==null && right!= null) {
				g = (Gear_New) right.pick(mouseX, mouseY);
			}
			if(g!=null) {
				return g;
			}
			else {
				return null; 
			}
		}
		//return null; 
	}
	

	public Iterator createIterator() {
//		return null; 
		return new mIterator(allGear); 
	}
	
//	Gear_New pickMe(float MX, float MY) {
//		if(MX < xPos+radius && MX > xPos-radius && MY < yPos+radius && MY > yPos-radius) {
//			println("selected");
//			return this; 
//		}
//		else {
//			Gear_New g =  null; 
//			if(left!= null) {
//				g = left.pickMe(MX, MY);
//			}
//			if(g==null && right!= null) {
//				g = right.pickMe(MX, MY);
//			}
//			if(g!=null) {
//				return g;
//			}
//			else {
//				return null; 
//			}
//		}
//	}
	
	public void tangency(Gear_New rec) {
		if(radius == rec.radius) {
	    	angle = atan((yPos-rec.yPos)/(xPos-rec.xPos));
	    	 
	    	x1 = xPos+radius*sin(angle);
	    	x2 = xPos-radius*sin(angle);
	    	y1 = yPos-radius*cos(angle);
	    	y2 = yPos+radius*cos(angle);
	    	
	    	x3 = rec.xPos+rec.radius*sin(angle);
	    	x4 = rec.xPos-rec.radius*sin(angle);
	    	y3 = rec.yPos-rec.radius*cos(angle);
	    	y4 = rec.yPos+rec.radius*cos(angle);
	    	
	    	Belt b1 = new Belt(p, x1,x2,x3,x4,y1,y2,y3,y4); 
	    	b1.drawMe(); 
	    	
		}
		
		else{    		
    		if(radius<rec.radius) {    	    		
	    		//find intersection point
	    		xP = (rec.xPos*radius - xPos*rec.radius)/(radius-rec.radius);
	    		yP = (rec.yPos*radius - yPos*rec.radius)/(radius-rec.radius);
	    		
	    		float xF1 = ((pow(radius,2) * (xP-xPos) + (radius*(yP-yPos)) * sqrt(pow((xP-xPos),2) + pow((yP-yPos),2)-pow(radius,2)))/(pow(xP-xPos,2) + pow(yP-yPos,2))) + xPos;
	        	float xF2 = ((pow(radius,2) * (xP-xPos) - (radius*(yP-yPos)) * sqrt(pow((xP-xPos),2) + pow((yP-yPos),2)-pow(radius,2)))/(pow(xP-xPos,2) + pow(yP-yPos,2))) + xPos;
	        	float yF1 = ((pow(radius,2) * (yP-yPos) - (radius*(xP-xPos)) * sqrt(pow((xP-xPos),2) + pow((yP-yPos),2)-pow(radius,2)))/(pow(xP-xPos,2) + pow(yP-yPos,2))) + yPos;
	        	float yF2 = ((pow(radius,2) * (yP-yPos) + (radius*(xP-xPos)) * sqrt(pow((xP-xPos),2) + pow((yP-yPos),2)-pow(radius,2)))/(pow(xP-xPos,2) + pow(yP-yPos,2))) + yPos;
	        	
	        	float xF3 = ((pow(rec.radius,2) * (xP-rec.xPos) + (rec.radius*(yP-rec.yPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.xPos;
	        	float xF4 = ((pow(rec.radius,2) * (xP-rec.xPos) - (rec.radius*(yP-rec.yPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.xPos;
	        	float yF3 = ((pow(rec.radius,2) * (yP-rec.yPos) - (rec.radius*(xP-rec.xPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.yPos;
	        	float yF4 = ((pow(rec.radius,2) * (yP-rec.yPos) + (rec.radius*(xP-rec.xPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.yPos;
	        	
	        	Belt b1 = new Belt(p, xF1,xF2,xF3,xF4,yF1,yF2,yF3,yF4); 
		    	b1.drawMe();         	
    		}
    		if(radius > rec.radius) {
	    		//find intersection point
	    		xP = (xPos*rec.radius - rec.xPos*radius)/(rec.radius-radius);
	    		yP = (yPos*rec.radius - rec.yPos*radius)/(rec.radius-radius);
	    		
	    		float xF1 = ((pow(rec.radius,2) * (xP-rec.xPos) + (rec.radius*(yP-rec.yPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.xPos;
	        	float xF2 = ((pow(rec.radius,2) * (xP-rec.xPos) - (rec.radius*(yP-rec.yPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.xPos;
	        	float yF1 = ((pow(rec.radius,2) * (yP-rec.yPos) - (rec.radius*(xP-rec.xPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.yPos;
	        	float yF2 = ((pow(rec.radius,2) * (yP-rec.yPos) + (rec.radius*(xP-rec.xPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.yPos;
	        	
	        	float xF3 = ((pow(radius,2) * (xP-xPos) + (radius*(yP-yPos)) * sqrt(pow((xP-xPos),2) + pow((yP-yPos),2)-pow(radius,2)))/(pow(xP-xPos,2) + pow(yP-yPos,2))) + xPos;
	        	float xF4 = ((pow(radius,2) * (xP-xPos) - (radius*(yP-yPos)) * sqrt(pow((xP-xPos),2) + pow((yP-yPos),2)-pow(radius,2)))/(pow(xP-xPos,2) + pow(yP-yPos,2))) + xPos;
	        	float yF3 = ((pow(radius,2) * (yP-yPos) - (radius*(xP-xPos)) * sqrt(pow((xP-xPos),2) + pow((yP-yPos),2)-pow(radius,2)))/(pow(xP-xPos,2) + pow(yP-yPos,2))) + yPos;
	        	float yF4 = ((pow(radius,2) * (yP-yPos) + (radius*(xP-xPos)) * sqrt(pow((xP-xPos),2) + pow((yP-yPos),2)-pow(radius,2)))/(pow(xP-xPos,2) + pow(yP-yPos,2))) + yPos;
	        	        	
	
	        	Belt b1 = new Belt(p, xF1,xF2,xF3,xF4,yF1,yF2,yF3,yF4); 
		    	b1.drawMe();    	
    		}
    	}
	}//end of tangency


	
}
