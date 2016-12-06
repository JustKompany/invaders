package com.weekenddesigner.invaders;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InvadersApplication implements ApplicationListener {

  private PlayerSprite playerSprite;

  private final int width;
  private final int height;

  private SpriteBatch spriteBatch;
  private OrthographicCamera camera;

  public InvadersApplication(int width, int height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public void create() {
    spriteBatch = new SpriteBatch();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, width, height);

    playerSprite = new PlayerSprite(0, width, width/2);
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0,0,0,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    camera.update();

    spriteBatch.setProjectionMatrix(camera.combined);
    spriteBatch.begin();
    spriteBatch.draw(playerSprite.getTexture(), playerSprite.getX(), playerSprite.getY());
    spriteBatch.end();

    CycleInput cycleInput = new CycleInput(Gdx.input.isKeyPressed(Input.Keys.LEFT),
            Gdx.input.isKeyPressed(Input.Keys.RIGHT),
            Gdx.input.isKeyPressed(Input.Keys.SPACE));

    playerSprite.process(cycleInput);
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

  @Override
  public void dispose() {
    spriteBatch.dispose();
  }
}
