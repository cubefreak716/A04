package iat265.matildac;

import processing.core.*;

import java.util.ArrayList;
import java.util.Iterator;

import controlP5.*;

public class mainRun extends PApplet{
	
	//Mechanism parts
	Gear_New all; 
	Belt allB; 
	Gear_New selected; 
	Belt selected2; 	
	
	//P5 stuff
	ControlP5 cp5;	
	Slider sizeSlider, strSlider;
	Slider rSlider,gSlider, bSlider;
	Slider xSlider, ySlider;
	Button clearGear;
	
	//For textfield
	String input; 
	
	Boolean onScreen = false; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("iat265.matildac.mainRun");
	}
	
    public void settings(){
    	size(800,800);
    }

    public void setup(){
    	//Setup cp5
    	cp5 = new ControlP5(this);    	
    	//declare mechanism
    	all = new Gear_New(this, 340, 200, 40, 1);
    	allB = new Belt(this, all, all.left, 1); 
    	//Initialize cp5 ui
    	initUI();  
    	
    	//Testing methods for parts
    	all.setParameter("red", 255);
//    	println("here:  "+ all.getParameter("Width"));
//    	println("length: " + all.getProperties().length) ;
//    	for(int o= 0; o <all.getProperties().length; o++) {
//    		println("Property " + (o+1) + "  " + all.getProperties()[o]); 
//    	}
    	
    	//Iterator testing
    	Iterator it = all.createIterator(); 
    	while(it.hasNext()) {
    		Gear_New current = (Gear_New)it.next(); 
    		println("oh boy   "+ current.getName()); 
    	}
    	Iterator it2 = allB.createIterator();
    	while(it2.hasNext()) {
    		Belt curr = (Belt)it2.next();
    		println("bletss  " + curr.getName());
    	}
    	
    }

    public void draw(){
    	background(255);
    	
    	//draw gears
    	all.draw(); 
    	allB.draw();
    	
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
     	 cp5.addTextfield("textInput_1").setPosition(20, 570).setSize(100, 40).setAutoClear(false);
     	 cp5.addBang("Submit").setPosition(20, 620).setSize(100, 30);  
    }
    
    public void Submit() {
    	input = cp5.get(Textfield.class,"textInput_1").getText();
    	selected.setName(input);
    	println("Changed name to: "+ input); 
    	input = null; 
    }
    
    public void strokeS(float sW) {
    	if(selected != null) {
    		selected.sw = sW; 
    	}
    	if(selected2 != null) {
    		selected2.sw = sW; 
    	}
    }
    
    public void radius(float ra) {
    	if(selected != null) {
    		selected.radius = ra;
    	}
    }
    
    public void red(float r) {
	  if (selected != null) {
	    selected.r = r; 
	  }
	  if (selected2 != null) {
		    selected2.r = r; 
	  }
    }
    public void green(float g) {
	  if (selected != null) {
	    selected.g = g; 
	  }
	  if (selected2 != null) {
		    selected2.g = g; 
	  }
    }
    public void blue(float b) {
	  if (selected != null) {
	    selected.b = b; 
	  }
	  if (selected2 != null) {
		    selected2.b = b; 
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
    		selected = (Gear_New) all.pick(mouseX, mouseY);
    		selected2 = (Belt)allB.pick(mouseX, mouseY); 
    		if(selected != null) {
    			strSlider.setValue(selected.sw);
    			sizeSlider.setValue(selected.radius);
    			rSlider.setValue(selected.r);
    			gSlider.setValue(selected.g);
    			bSlider.setValue(selected.b);
    			xSlider.setValue(selected.xPos);
    			ySlider.setValue(selected.yPos);
    			println("Current Gear Name: "+selected.getName()); 
    			
    		}
    		if(selected2 != null) {
    			strSlider.setValue(selected2.sw);
    			rSlider.setValue(selected2.r);
    			gSlider.setValue(selected2.g);
    			bSlider.setValue(selected2.b);
    			println("Current Belt name: " + selected2.getName());
    		}
    	}
    }

}
