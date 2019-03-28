package iat265.matildac;

import processing.core.PApplet;

public class Belt extends PApplet{

	PApplet p;
	float angle;
	float x1,x2,x3,x4;
	float y1,y2,y3,y4;
	float xP, yP;
	Gear G1, G2;
	
	Belt(PApplet parent, float X1, float X2, float X3, float X4, float Y1, float Y2, float Y3, float Y4){
		p = parent; 
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
	
}

