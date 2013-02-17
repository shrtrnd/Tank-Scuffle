package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class BallManager {
	public ArrayList<SpriteWrapper> balls = new ArrayList<SpriteWrapper>();
	
	
	public void CreateBall(float x, float y, float xVelocity, float yVelocity){
		

		Texture ballTexture =  new Texture(Gdx.files.internal("data/Ball.png"));
		ballTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion ballRegion = new TextureRegion(ballTexture, 0, 0, 1025, 550);
		
		Sprite ballSprite = new Sprite(ballRegion);
		ballSprite.setSize(0.25f, 0.25f * ballSprite.getHeight() / ballSprite.getWidth());
		ballSprite.setOrigin(ballSprite.getWidth()/2, ballSprite.getHeight()/2);
		ballSprite.setPosition(x,y );
		
		SpriteWrapper ball = new SpriteWrapper(ballSprite, ballTexture);
		ball.xVelocity=xVelocity;
		ball.yVelocity=yVelocity;
		balls.add(ball);
		
	}
	

	public void updateBalls()
	{
		for (SpriteWrapper ball : balls) {
			ball.Update();
		}
	}
	
	public void draw(SpriteBatch spriteBatch)
	{

		for (SpriteWrapper ball : balls) {
			ball.mySprite.draw(spriteBatch);
		}
		
	}
	
}
