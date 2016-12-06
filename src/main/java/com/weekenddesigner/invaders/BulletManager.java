package com.weekenddesigner.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashSet;
import java.util.Set;

public class BulletManager implements Actor {
  private final Set<Bullet> bullets;
  private final Texture bulletTexture;
  private final int height;

  public BulletManager(int height) {
    this.height = height;
    this.bullets = new HashSet<>();
    this.bulletTexture = new Texture(Gdx.files.internal("sprites/bullet.png"));
  }

  public void createBullet(int x, int y) {
    Bullet bullet = new Bullet(x, y, bulletTexture);
    bullets.add(bullet);
  }

  @Override
  public void process(CycleInput cycleInput) {
    Set<Bullet> taggedForRemove = new HashSet<>();
    for(Bullet bullet : bullets) {
      bullet.process();
      if(bullet.getY() > height) {
        taggedForRemove.add(bullet);
      }
    }
    bullets.removeAll(taggedForRemove);
  }

  public Set<Bullet> getBulletSprites() {
    return bullets;
  }

  public static class Bullet implements PositionedSprite {

    private static final int DY = 3;
    private final int x;
    private int y;

    private final Texture texture;

    public Bullet(int x, int y, Texture texture) {
      this.x = x;
      this.y = y;
      this.texture = texture;
    }

    public void process() {
      y = y + DY;
    }

    @Override
    public int getX() {
      return x;
    }

    @Override
    public int getY() {
      return y;
    }

    @Override
    public Texture getTexture() {
      return texture;
    }
  }
}
