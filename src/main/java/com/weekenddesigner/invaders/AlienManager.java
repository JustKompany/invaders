package com.weekenddesigner.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class AlienManager implements Actor {
  private static final int X_SPACE = 20;

  private final Texture alien1Texture;
  private final AlienPosition alienPosition;
  private final List<Alien> aliens;

  private static final int MOVE_PAUSE_MS = 25;
  private static final int MOVE_DISTANCE = 2;

  private final int maxXPosition;
  private final int blockWidth;

  private long lastMoved;

  public AlienManager(int x, int y, int width) {
    this.alienPosition = new AlienPosition(x, y);
    alien1Texture = new Texture(Gdx.files.internal("sprites/alien1.png"));
    aliens = new ArrayList();

    int textureWidth = alien1Texture.getWidth();
    for(int i=0; i<5; ++i) {
      Alien alien = new Alien(alien1Texture, X_SPACE + i*(textureWidth + X_SPACE), 0, alienPosition);
      aliens.add(alien);
    }
    blockWidth = 5*(textureWidth + X_SPACE) - X_SPACE / 2;
    maxXPosition = width - blockWidth;

    lastMoved = 0;
  }

  @Override
  public void process(CycleInput cycleInput) {
    long timeStamp = System.currentTimeMillis();
    if(timeStamp - lastMoved > MOVE_PAUSE_MS) {
      if(alienPosition.x < maxXPosition + MOVE_DISTANCE) {
        alienPosition.x += MOVE_DISTANCE;
      }
      lastMoved = timeStamp;
    }
  }

  public List<Alien> getAliens() {
    return aliens;
  }

  public static class AlienPosition {
    int x;
    int y;

    public AlienPosition(int x, int y) {
      this.x = x;
      this.y = y;
    }

    int getX() {
      return x;
    }

    int getY() {
      return y;
    }
  }

  public static class Alien implements PositionedSprite {
    private final Texture texture;

    private final int offsetX;
    private final int offsetY;

    private final AlienPosition alienPosition;

    private boolean alive;

    public Alien(Texture texture, int offsetX, int offsetY, AlienPosition alienPosition) {
      this.texture = texture;
      this.offsetX = offsetX;
      this.offsetY = offsetY;
      this.alienPosition = alienPosition;
      this.alive = true;
    }

    @Override
    public int getX() {
      return alienPosition.getX() + offsetX;
    }

    @Override
    public int getY() {
      return alienPosition.getY() + offsetY;
    }

    @Override
    public Texture getTexture() {
      return texture;
    }
  }
}
