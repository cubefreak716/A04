package iat265.matildac;

import processing.core.*;
import controlP5.*;
//import interfacelib.*;

public class mainRun extends PApplet{
	
	Gear rrr; 
	Gear selected; 
	ControlP5 cp5;

	Slider sizeSlider, strSlider;
	Slider rSlider,gSlider, bSlider;
	Slider xSlider, ySlider;
	Button clearGear;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("iat265.matildac.mainRun");
	}
	
    public void settings(){
    	size(800,800);
    }

    public void setup(){
    	cp5 = new ControlP5(this);    	
    	rrr = new Gear(this, 340, 200, 40, 1);
    	initUI();
    }

    public void draw(){
    	background(255);
    	
    	//draw gears
    	rrr.drawMe(); 
    	
    	//draw side bar
    	pushStyle();
    	strokeWeight(2);
    	rect(0,0,200, height);
    	popStyle();   	
    	
    }//end of draw
    

    
    void styleCaptionLabel(Label l) {
    	  PFont font = createFont("arial", 16);
    	  l.setLineHeight(20).setFont(font)
    	    .setColor(0)
    	    .align(ControlP5.LEFT_OUTSIDE, CENTER);
    	  ;
	}
    
    Slider createSlider(String key, String label, float x, float y, float min, float max) {
    	  Slider s = cp5.addSlider(key).setPosition(x, y)
    	    .setRange(min, max)
    	    .setLabel(label)
    	    .setSize(100, 20);
    	  styleCaptionLabel(s.getCaptionLabel());
    	  return s;
    }
    
    Button createButton(String key, String label, float x, float y){
    	  Button b = cp5.addButton(key)
    	  .setLabel(label)
    	  .setSize(75, 50)
    	  .setPosition(x, y);
//    	  styleButton(b);
    	  return b;
    }
    
    public void initUI() {
    	 sizeSlider = createSlider("radius", "Radius", 80, 50, 10, 100);
    	 rSlider = createSlider("red", "Red", 80, 100, 0, 255); 
    	 gSlider = createSlider("green", "Green", 80, 120, 0, 255);
    	 bSlider = createSlider("blue", "Blue", 80, 140, 0, 255);
    	 xSlider = createSlider("xposition", "X Pos", 80,170,200, width);
    	 ySlider = createSlider("yposition", "Y Pos", 80,190,0, height);
    	 strSlider = createSlider("strokeS", "Weight", 80,30,1, 5); 
    	 clearGear = createButton("save","Save", 20,700);
    }
    
    public void strokeS(float sW) {
    	if(selected != null) {
    		selected.sw = sW; 
    	}
    }
    
    public void radius(float ra) {
    	if(selected != null) {
    		selected.radius = ra;
    	}
    }
    
    public void red(float r) {
	  if (selected != null) {
	    selected.cou = color(r, green(selected.cou), blue(selected.cou));
	  }
    }
    public void green(float g) {
	  if (selected != null) {
	    selected.cou = color(red(selected.cou), g, blue(selected.cou));
	  }
    }
    public void blue(float b) {
	  if (selected != null) {
	    selected.cou = color(red(selected.cou), green(selected.cou), b);
	  }
    }
    
    public void xposition(float x) {
    	if(selected!= null) {
    		selected.xPos = x;
    	}
    }
    public void yposition(float y) {
    	if(selected!=null) {
    		selected.yPos = y;
    	}
    }
    
    public void save() {
    	save("image.jpg");
    }
    
    public void mousePressed() {
    	if(mouseX>200) {
    		selected = rrr.pickMe(mouseX, mouseY);
    		if(selected != null) {
    			strSlider.setValue(selected.sw);
    			sizeSlider.setValue(selected.radius);
    			rSlider.setValue(red(selected.cou));
    			gSlider.setValue(green(selected.cou));
    			bSlider.setValue(blue(selected.cou));
    			xSlider.setValue(selected.xPos);
    			ySlider.setValue(selected.yPos);
    		}
    	}
    }

}
