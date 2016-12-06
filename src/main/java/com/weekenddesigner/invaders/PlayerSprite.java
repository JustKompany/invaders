package com.weekenddesigner.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.sun.org.apache.regexp.internal.RE;

public class PlayerSprite implements Actor, PositionedSprite {
  private static final int a = 1;
  private static final int maxV = 3;
  private static final int MIN_Y = 10;

  private final int minX;
  private final int maxX;

  private final Texture texture;
  private final BulletManager bulletManager;

  private final int y;
  private final int bulletY;
  private int x;
  private int v;

  private static final int RECHARGE_MS = 500;
  private long lastFired = 0;

  public PlayerSprite(int minX, int maxX, BulletManager bulletManager, int x) {
    this.minX = minX;
    this.maxX = maxX;
    this.bulletManager = bulletManager;

    this.texture = new Texture(Gdx.files.internal("sprites/player-sprite.png"));

    this.y = MIN_Y + texture.getHeight() / 2;
    this.bulletY = this.y + texture.getHeight() / 2 + 15;
    this.x = x;
    this.v = 0;
  }

  @Override
  public void process(CycleInput cycleInput) {
    if(cycleInput.isLeftArrow() && Math.abs(v) < maxV) {
      v -= a;
    } else if(cycleInput.isRightArrow() && Math.abs(v) < maxV) {
      v += a;
    }

    if(!cycleInput.isLeftArrow() && !cycleInput.isRightArrow() && Math.abs(v) != 0) {
      v += v > 0 ? -1 : 1;
    }

    if((v < 0 && minX < x) || (v > 0 && x < maxX)) {
      x += v;
    }

    if(cycleInput.isSpaceBar()) {
      long timeStamp = System.currentTimeMillis();
      if(timeStamp - lastFired > RECHARGE_MS) {
        bulletManager.createBullet(x, bulletY);
        lastFired = timeStamp;
      }
    }
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
