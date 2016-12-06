package com.weekenddesigner.invaders;

public class CycleInput {
  private final boolean leftArrow;
  private final boolean rightArrow;
  private final boolean spaceBar;

  public CycleInput(boolean leftArrow, boolean rightArrow, boolean spaceBar) {
    this.leftArrow = leftArrow;
    this.rightArrow = rightArrow;
    this.spaceBar = spaceBar;
  }

  public boolean isLeftArrow() {
    return leftArrow;
  }

  public boolean isRightArrow() {
    return rightArrow;
  }

  public boolean isSpaceBar() {
    return spaceBar;
  }

  @Override
  public String toString() {
    return "CycleInput{" +
            "leftArrow=" + leftArrow +
            ", rightArrow=" + rightArrow +
            ", spaceBar=" + spaceBar +
            '}';
  }
}
