package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private Texture ballTexture;
	private Sprite ballSprite;
	
	private Texture tankTexture;
	private Sprite tankSprite;
	
	BallManager manager = new BallManager();
	
	private float xOffset = 0;
	private float xChange = 0.01f;
	private float xMaxChange = .5f;
	private float xOriginal;
	public float xVelocity = 0;
	
	
	@Override
	public void create() {		
		MyGestureListener mylistener = new MyGestureListener();
		mylistener.myGame = this;
		Gdx.input.setInputProcessor(new GestureDetector(mylistener));
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 1025, 550);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		xOriginal = -sprite.getWidth()/2;
		sprite.setPosition(xOriginal,-sprite.getHeight()/2 );
		

		tankTexture = new Texture(Gdx.files.internal("data/Tank.png"));
		tankTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion tankRegion = new TextureRegion(tankTexture, 0, 0, 1025, 550);
		
		tankSprite = new Sprite(tankRegion);
		tankSprite.setSize(0.25f, 0.25f * sprite.getHeight() / sprite.getWidth());
		tankSprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		xOriginal = -sprite.getWidth()*3/4;
		
	}
	
	public void shoot(float velocityX, float velocityY)
	{
		manager.CreateBall(tankSprite.getX(), tankSprite.getY(), velocityX/80000f, velocityY/80000);
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		
		sprite.setPosition(sprite.getX() + xVelocity, sprite.getY());
		manager.updateBalls();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//sprite.draw(batch);
		manager.draw(batch);
		tankSprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
