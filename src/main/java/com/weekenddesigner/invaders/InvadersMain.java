package com.weekenddesigner.invaders;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class InvadersMain {
  public static void main(String[] args) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Invaders";
    config.height = 460;
    config.width = 480;
    new LwjglApplication(new InvadersApplication(config.width, config.height), config);
  }
}
