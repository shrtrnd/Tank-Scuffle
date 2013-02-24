package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.lang.Math;

import javax.vecmath.Point2d;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;


public class TerrainMaker {
	private ShapeRenderer shapeRenderer;
	private Camera myCamera;
	
	public TerrainMaker(Camera camera) {
		myCamera = camera;
	}
	// this is the core function: drawHills
    // arguments: the number of hills to generate, and the horizontal step, in pixels, between two hill points
    void drawHills(int numberOfHills, int pixelStep){
    	ShapeRenderer shapeRenderer = new ShapeRenderer();
    	shapeRenderer.setProjectionMatrix(myCamera.combined);
        // setting a starting y coordinate, around the vertical center of the stage
        float hillStartY = (float) (140+ Math.random()*200);
        // defining hill width, in pixels, that is the stage width divided by the number of hills
        float hillWidth = 640/numberOfHills;
        // defining the number of slices of the hill. This number is determined by the width of the hill in pixels divided by the amount of pixels between two points
        float hillSlices= hillWidth/pixelStep;
        // drawing stuff
  
        // looping through the hills
        for (int i=0; i < numberOfHills; i++) {
            // setting a random hill height in pixels
            float randomHeight = (float) (Math.random()*100);
            // this is necessary to make all hills (execept the first one) begin where the previous hill ended
            if(i!=0){
                hillStartY-=randomHeight;
            }
            Point2d lastHillPoint = null;
            // looping through hill slices
            for (int j = 0; j<=hillSlices; j++) {
                    // defining the point of the hill
                    Point2d hillPoint =new Point2d((j*pixelStep+hillWidth*i)/640f,(hillStartY+randomHeight*Math.cos(2*Math.PI/hillSlices*j))/640f);
                    // drawing stuff
                    if(lastHillPoint != null){
                    	shapeRenderer.begin(ShapeType.Line);
                    	shapeRenderer.setColor(1, 0, 0, 1);
                    	shapeRenderer.line((float) lastHillPoint.x, (float) lastHillPoint.y, (float) hillPoint.x, (float) hillPoint.y);
                    	shapeRenderer.end();
                    }
                    lastHillPoint = hillPoint;
                    System.out.println(lastHillPoint);
            }
            // this is also necessary to make all hills (execept the first one) begin where the previous hill ended
            hillStartY = hillStartY+randomHeight;
        }
}}

