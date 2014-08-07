package entities;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import tools.Window;
import controller.Invaders;

import animations.AnimInvader;

public class Invader {

  private AnimInvader anim_invader;

  private float       size;
  private boolean     alive;
  private float       speed;
  private Shape       invader;
  private Invaders    invaders;

  public Invader(float x, float y, Invaders invaders) {

    size = 1;
    float height = 20 * size;
    float width = 30 * size;
    speed = 0.1f;

    anim_invader = new AnimInvader(width, height, size);
    alive = true;
    invader = new Rectangle(x, y, width, height);
    this.invaders = invaders;
  }

  public void draw(Graphics g) {
    anim_invader.draw(getX(), getY());
  }

  private float getY() {
    return invader.getY();
  }

  public float getX() {
    return invader.getX();
  }

  public void update(int delta) {
    if (getY() > Window.HEIGHT - getHeight()) {
      setY(Window.HEIGHT - getHeight());
      speed = -speed;
    } else if (getY() < 0) {
      setY(0);
      speed = -speed;
    }
    if (getX() > Window.WIDTH - getWidth()) {
      setX(Window.WIDTH - getWidth());
      speed = -speed;
    } else if (getX() < 0) {
      setX(0);
      speed = -speed;
    }
    Random r = new Random();
    r.nextInt(2);
    setY(getY() + delta * speed);
    // setX(getY() + delta * speed);
  }

  private float getWidth() {
    return invader.getWidth();
  }

  private float getHeight() {
    return invader.getHeight();
  }

  private void setY(float f) {
    invader.setY(f);
  }

  private void setX(float f) {
    invader.setX(f);
  }

  public Shape shape() {
    return invader;
  }

  public void explode() {
    alive = false;
  }

  public float center_x() {
    return invader.getCenterX();
  }

  public float center_y() {
    return invader.getCenterY();
  }

  public boolean is_alive() {
    return alive;
  }

  public void fire() {
    invaders.fire(this);
  }

  public int getPoint() {
    return 10;
  }
}
