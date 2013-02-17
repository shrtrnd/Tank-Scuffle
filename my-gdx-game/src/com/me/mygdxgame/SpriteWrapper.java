package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteWrapper {
	public Sprite mySprite;
	private Texture myTexture;
	public float xVelocity = 0;
	public float yVelocity = 0;
	
	public SpriteWrapper(Sprite sprite, Texture texture) {
		mySprite=sprite;
		myTexture=texture;
	}
	
	
	public void Update()
	{
		mySprite.setX(mySprite.getX() + xVelocity);
		mySprite.setY(mySprite.getY() + yVelocity);		
	}
}
