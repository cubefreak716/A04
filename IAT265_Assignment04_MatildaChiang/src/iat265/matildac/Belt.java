package iat265.matildac;

import java.util.ArrayList;
import java.util.Iterator;

import iat265.aga53.Scrubbable;
import processing.core.PApplet;
import processing.core.PVector;

public class Belt extends PApplet implements Scrubbable{

	PApplet p;

	//for calculations
	float xP, yP;
	float x1,x2,x3,x4;
	float y1,y2,y3,y4;
	
	PVector pos1, pos2 = new PVector(0,0); 
	PVector end1, end2 = new PVector(0,0); 
	
	
	Gear_New G1, G2; //gears that its pointing to
	Belt  left, right; 
	String n;  //name of part
	ArrayList<Belt> allBelts; 
	
	float sw, radius; //stroke weight and radius
	float r, g, b; //colour
	float rotS;  //speed
	
	float angle; //angle for calculation
	
	int d; //depth
	
	String [] properties; 
	
//	Belt(PApplet parent, float X1, float X2, float X3, float X4, float Y1, float Y2, float Y3, float Y4){
	Belt(PApplet parent, Gear_New g1, Gear_New g2, int depth){
		p = parent; 
		
		d = depth; 
		n = "Belt " + depth; 
		
		G1 = g1; 
		G2 = g2; 
		
		this.r = 0;
		this.g = 0; 
		this.b = 0; 
		sw = 1; 
		
		properties = new String[6];
		properties[0] = "LineWeight";
		properties[1] = "red";
		properties[2] = "green";
		properties[3] = "blue";
		properties[4] = "Name"; 
		
		if(d<5) {
//			left = new Gear_New(p,xPos+60,yPos+60, (float) (radius * 0.7), depth+1);
			left = new Belt(p, G2, G2.left, d+1); 
//			System.out.println("Belt: " +left.getName());

		}
		
	}
	
	
	public void tangency(Gear_New g, Gear_New rec) {
	if(g.radius == rec.radius) {
    	angle = atan((g.yPos-rec.yPos)/(g.xPos-rec.xPos));
    	 
    	x1 = g.xPos+g.radius*sin(angle);
    	x2 = g.xPos-g.radius*sin(angle);
    	y1 = g.yPos-g.radius*cos(angle);
    	y2 = g.yPos+g.radius*cos(angle);
    	
    	x3 = rec.xPos+rec.radius*sin(angle);
    	x4 = rec.xPos-rec.radius*sin(angle);
    	y3 = rec.yPos-rec.radius*cos(angle);
    	y4 = rec.yPos+rec.radius*cos(angle);    	
	}
	
	else{    		
		if(g.radius<rec.radius) {    	    		
    		//find intersection point
    		xP = (rec.xPos*g.radius - g.xPos*rec.radius)/(g.radius-rec.radius);
    		yP = (rec.yPos*g.radius - g.yPos*rec.radius)/(g.radius-rec.radius);
    		
    		 x1 = ((pow(g.radius,2) * (xP-g.xPos) + (g.radius*(yP-g.yPos)) * sqrt(pow((xP-g.xPos),2) + pow((yP-g.yPos),2)-pow(g.radius,2)))/(pow(xP-g.xPos,2) + pow(yP-g.yPos,2))) + g.xPos;
        	 x2 = ((pow(g.radius,2) * (xP-g.xPos) - (g.radius*(yP-g.yPos)) * sqrt(pow((xP-g.xPos),2) + pow((yP-g.yPos),2)-pow(g.radius,2)))/(pow(xP-g.xPos,2) + pow(yP-g.yPos,2))) + g.xPos;
        	 y1 = ((pow(g.radius,2) * (yP-g.yPos) - (g.radius*(xP-g.xPos)) * sqrt(pow((xP-g.xPos),2) + pow((yP-g.yPos),2)-pow(g.radius,2)))/(pow(xP-g.xPos,2) + pow(yP-g.yPos,2))) + g.yPos;
        	 y2 = ((pow(g.radius,2) * (yP-g.yPos) + (g.radius*(xP-g.xPos)) * sqrt(pow((xP-g.xPos),2) + pow((yP-g.yPos),2)-pow(g.radius,2)))/(pow(xP-g.xPos,2) + pow(yP-g.yPos,2))) + g.yPos;
        	
        	 x3 = ((pow(rec.radius,2) * (xP-rec.xPos) + (rec.radius*(yP-rec.yPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.xPos;
        	 x4 = ((pow(rec.radius,2) * (xP-rec.xPos) - (rec.radius*(yP-rec.yPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.xPos;
        	 y3 = ((pow(rec.radius,2) * (yP-rec.yPos) - (rec.radius*(xP-rec.xPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.yPos;
        	 y4 = ((pow(rec.radius,2) * (yP-rec.yPos) + (rec.radius*(xP-rec.xPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.yPos;
       	
		}
		if(g.radius > rec.radius) {
    		//find intersection point
    		xP = (g.xPos*rec.radius - rec.xPos*g.radius)/(rec.radius-g.radius);
    		yP = (g.yPos*rec.radius - rec.yPos*g.radius)/(rec.radius-g.radius);
    		
    		 x1 = ((pow(rec.radius,2) * (xP-rec.xPos) + (rec.radius*(yP-rec.yPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.xPos;
        	 x2 = ((pow(rec.radius,2) * (xP-rec.xPos) - (rec.radius*(yP-rec.yPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.xPos;
        	 y1 = ((pow(rec.radius,2) * (yP-rec.yPos) - (rec.radius*(xP-rec.xPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.yPos;
        	 y2 = ((pow(rec.radius,2) * (yP-rec.yPos) + (rec.radius*(xP-rec.xPos)) * sqrt(pow((xP-rec.xPos),2) + pow((yP-rec.yPos),2)-pow(rec.radius,2)))/(pow(xP-rec.xPos,2) + pow(yP-rec.yPos,2))) + rec.yPos;
        	
        	 x3 = ((pow(g.radius,2) * (xP-g.xPos) + (g.radius*(yP-g.yPos)) * sqrt(pow((xP-g.xPos),2) + pow((yP-g.yPos),2)-pow(g.radius,2)))/(pow(xP-g.xPos,2) + pow(yP-g.yPos,2))) + g.xPos;
        	 x4 = ((pow(g.radius,2) * (xP-g.xPos) - (g.radius*(yP-g.yPos)) * sqrt(pow((xP-g.xPos),2) + pow((yP-g.yPos),2)-pow(g.radius,2)))/(pow(xP-g.xPos,2) + pow(yP-g.yPos,2))) + g.xPos;
        	 y3 = ((pow(g.radius,2) * (yP-g.yPos) - (g.radius*(xP-g.xPos)) * sqrt(pow((xP-g.xPos),2) + pow((yP-g.yPos),2)-pow(g.radius,2)))/(pow(xP-g.xPos,2) + pow(yP-g.yPos,2))) + g.yPos;
        	 y4 = ((pow(g.radius,2) * (yP-g.yPos) + (g.radius*(xP-g.xPos)) * sqrt(pow((xP-g.xPos),2) + pow((yP-g.yPos),2)-pow(g.radius,2)))/(pow(xP-g.xPos,2) + pow(yP-g.yPos,2))) + g.yPos;
		}
	}

 	pos1 = new PVector(x1,y1); 
 	pos2 = new PVector(x2,y2);
 	end1 = new PVector(x3,y3);
 	end2 = new PVector(x4,y4);
}//end of tangency
	
	
	
	
	public void draw() {
		
		p.pushStyle(); 
		p.strokeWeight(sw);
		p.stroke(r,g,b);
		//right side	
		p.line(x1,y1,x3,y3); 
		//left side
    	p.line(x2,y2,x4,y4); 
    	p.popStyle(); 
    	
	    if (left != null) {	   
	    tangency(G1, G2);
        left.draw();
	   }
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
				c = i; 
			}
		}		
		switch(c) {
		  case 0: 
			  sw = value; 
		    break;
		  case 1: 
			  r = value;
		    break;
		  case 2: 
			  g = value; 
		    break;
		  case 3:
			  b = value; 
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
		else if(name.equals("red")) {
			return r; 
		}
		else if(name.equals("green")) {
			return g; 
		}
		else if(name.equals("blue")) {
			return b; 
		}
		else {
			return 0; 
		} 

	}
	
	public Scrubbable pick(int mouseX, int mouseY) {
	    float minX1 = min(pos1.x, end1.x)-sw/2;
	    float maxX1 = max(pos1.x, end1.x)+sw/2;
	    float minY1 = min(pos1.y, end1.y)-sw/2;
	    float maxY1 = max(pos1.y, end1.y)+sw/2;
	    float minX2 = min(pos2.x, end2.x)-sw/2;
	    float maxX2 = max(pos2.x, end2.x)+sw/2;
	    float minY2 = min(pos2.y, end2.y)-sw/2;
	    float maxY2 = max(pos2.y, end2.y)+sw/2;
 
	    if ((mouseX > minX1 && mouseX < maxX1 && mouseY > minY1 && mouseY < maxY1)||(mouseX > minX2 && mouseX < maxX2 && mouseY > minY2 && mouseY < maxY2)) {	    	
		   
	    	return this;
	    }
	    else {	    	
	    	Belt b1 = null;
	    	if(G2.left!=null&&left!=null) {
	    		b1 = (Belt)left.pick(mouseX, mouseY);
	      	}
	    	if(b1 != null) {
	    		
	    		return b1;
	    	}
	    	else {
	    		return null;
	    	}
	    }

}
//	    	p.line(minX1,minY1, maxX1, maxY1); 
//	    	println("hiya 1"); 
//	      return this;
//	    } 
//	    else  if (mouseX > minX2 && mouseX < maxX2 && mouseY > minY2 && mouseY < maxY2) {
//	    	p.line(minX1,minY1, maxX1, maxY1); 
//	    	println("hiya 2"); 
//		      return this;
//		 } 
//	    else {
//	    	println("nothing"); 
//	    	return null; 
//	    }   

	
	
//	  Tree pickMe(float mX, float mY) {
//		    float minX = min(pos.x, end.x)-bwidth/2;
//		    float maxX = max(pos.x, end.x)+bwidth/2;
//		    float minY = min(pos.y, end.y)-bwidth/2;
//		    float maxY = max(pos.y, end.y)+bwidth/2;
//
//		    if (mX > minX && mX < maxX && mY > minY && mY < maxY) {
//		      return this;
//		    } else {
//		      Tree s = null;
//
//		      if (left != null) {
//		        s = left.pickMe(mX, mY);
//		      }
//
//		      if (s == null && right != null) {
//		        s = right.pickMe(mX, mY);
//		      }
//
//		      //
//		      if (s!= null) {
//		        return s;
//		      } else {
//		        return null;
//		      }
//		    }
//		  }
	
	public Iterator createIterator() {
		return new mIterator(this); 
	}
	
	
}

