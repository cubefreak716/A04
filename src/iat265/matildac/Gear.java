package iat265.matildac;

import processing.core.PApplet;
import processing.core.PVector;

public class Gear extends PApplet{

	PApplet p;
	float xPos, yPos;
	PVector pos, end; 	
	
	float radius; 
	Gear left, right;
	Belt b; 
	int cou; 
	float sw; //stroke weight
	
	//for tangency function
	float angle;
	float x1,x2,x3,x4;
	float y1,y2,y3,y4;
	float xP, yP;
	
	Gear(PApplet parent, float x, float y, float r, int depth){
		p = parent;
		xPos = x;
		yPos = y;
		radius = r;
		cou = color(125);	
		sw = 1; 

		if(depth<4) {
			//println(depth);
			if(depth==3) {
				left = new Gear(p,xPos-40,yPos-180, radius, depth+1);
			}
			else if(depth==1) {
				left = new Gear(p,xPos+60,yPos+200, (float) (radius * 0.7), depth+1);
			}
			else if(depth == 2) {
				left = new Gear(p,xPos+120,yPos+50, (float) (radius * 0.5), depth+1);	
				right = new Gear(p,xPos-80,yPos+120, (float) (radius * 0.5), 5);
				
			}
			else if(depth == 0) { //first gear
				left = new Gear(p,xPos,yPos, 50, depth+1);
				
			}
			
		}
		
	}
	
	
	void drawMe() {
		p.pushMatrix();
		p.pushStyle();
			p.translate(xPos, yPos);
			p.stroke(cou); 		
			p.strokeWeight(sw); 
			p.ellipse(0, 0, 2*radius, 2*radius);
			p.line(0, 0, radius, 0);
			p.line(0, 0, 0, radius);
			p.line(0, 0, -radius, 0);
			p.line(0, 0, 0, -radius);
			p.ellipse(0,0,radius,radius);
		p.popStyle();
		p.popMatrix();

		
	    if (left != null) {	    	
	       	tangency(left); 	    	
	        left.drawMe();	        
	    }
	    if (right!=null) {	    		
	    	tangency(right); 	    	
	        right.drawMe();
	    }

	}//end of draw me
	
	Gear pickMe(float MX, float MY) {
		if(MX<xPos+radius && MX>xPos-radius && MY<yPos+radius && MY>yPos-radius) {
			println(" selected ");
			return this; 
		}
		else {
			Gear g =  null; 
			if(left!= null) {
				g = left.pickMe(MX, MY);
			}
			if(g==null && right!= null) {
				g = right.pickMe(MX, MY);
			}
			if(g!=null) {
				return g;
			}
			else {
				return null; 
			}
		}
	}
	
	public void tangency(Gear rec) {
		if(radius == rec.radius) {
	    	angle = atan((yPos-rec.yPos)/(xPos-rec.xPos));
	    	
//	    	println(angle); 
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
