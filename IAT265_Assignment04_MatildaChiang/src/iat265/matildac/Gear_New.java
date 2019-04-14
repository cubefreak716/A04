package iat265.matildac;

import java.util.ArrayList;
import java.util.Iterator;
import iat265.aga53.Scrubbable;
import processing.core.PApplet;
import processing.core.PVector;

public class Gear_New extends PApplet implements Scrubbable {

	PApplet p; //PApplet
	float xPos, yPos; //position of gear
//	PVector pos, end; // 
	
	float rotS; //rotation speed
	
	float radius; //width
	Gear_New left, right; //next gears
	Belt bLEFT, bRIGHT; //next Belts
	float r, g, b; 
	float sw; //stroke weight
	int d; //depth
	
	float changeDir = -10; 
	
	//for tangency function
	float angle; 
//	float x1,x2,x3,x4; // x coordinates of belts
//	float y1,y2,y3,y4; // y coordinates of belts
//	float xP, yP; 
	
	String [] properties; 
	int numProp = 9;  //number of properties available to edit
	
	String n ;  //name of part
	
	public Gear_New(PApplet parent, float x, float y, float r, int depth){
		d = depth; 
		n = "Gear" + (depth); // name of gear
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
		
				
		if(depth<5) {
			println(depth);
			if(depth==3) {
				left = new Gear_New(p,xPos-40,yPos-180, radius, depth+1);
//				bLEFT = new Belt(p, left, this, depth); 
			}
			else if(depth==1) {
				left = new Gear_New(p,xPos+60,yPos+200, (float) (radius * 0.7), depth+1);
//				bLEFT = new Belt(p, left, this, depth); 
			}
			else if(depth == 2) {
				left = new Gear_New(p,xPos+120,yPos+50, (float) (radius * 0.5), depth+1);	
//				bLEFT = new Belt(p, left, this, depth); 
//				right = new Gear_New(p,xPos-80,yPos+120, (float) (radius * 0.5), 5);
//				RIGHT = new Belt(p, right, this, depth); 
			}
			else if(depth == 0) { //first gear
				left = new Gear_New(p,xPos,yPos, 50, depth+1); 
//				bLEFT = new Belt(p, left, this, depth); 				
			}
			else if(depth== 4) {
				left = new Gear_New(p,xPos-80,yPos+210, (float) (radius * 0.5), depth+1);
//				bLEFT = new Belt(p, left, this, depth);
			}			
		}

		
		
//		if(depth<5) {
//			left = new Gear_New(p,(xPos+60)+(-changeDir),(yPos+40), (float) (radius * 0.7), depth+1);
////			bLEFT = new Belt(p, left, this, depth); 
////			println("Belt: " +bLEFT.getName());
//		}
		
		
		
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
//				println("Found it"); 
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
			rotS+= 0.01; 
		p.popStyle();
		p.popMatrix();

		
	    if (left != null) {	    	 
	        left.draw();
		    if(bLEFT!=null) {
		    	bLEFT.tangency(this, left);
		    	bLEFT.draw(); 
		    }
	    }
	    if (right!=null) {	    		 
	        right.draw();
		    if(bRIGHT!=null) {
		    	bRIGHT.tangency(this, right);
		    	bRIGHT.draw();
		    }
	    }
	}
	
	public Scrubbable pick (int mouseX, int mouseY) {
		if(mouseX<xPos+radius && mouseX>xPos-radius && mouseY<yPos+radius && mouseY>yPos-radius) {
//			println(" selected " + this.getName());
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
	}
	

	public Iterator createIterator() {
		return new mIterator(this); 
	}
		
}// End of class
