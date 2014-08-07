package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import tools.Window;

import controller.Invaders;

import animations.AnimExplosion;
import animations.AnimMissileShip;
import animations.Animable;

public class ShipFire{

  enum States {
    DESTROY, EXPLOSION, MISSILE
  }

  private Animable[] anims;
  private Shape      fire;

  private float      speed;
  private States     state;

  public ShipFire(float x, float y) {
    float size = 1f;// 0.7f;
    float width = 40 * size;
    float height = 20 * size;
    speed = 0.5f;
    state = States.MISSILE;

    fire = new Rectangle(x, y, width, height);
    anims = new Animable[3];
    anims[States.MISSILE.ordinal()] = new AnimMissileShip(width, height, size);
  }

  public void change_state_to_explosion(Invader invader) {
    // need to align center of explosion and center of invader
    state = States.EXPLOSION;
    int size_explosion = 100;
    anims[States.EXPLOSION.ordinal()] = new AnimExplosion(size_explosion,
        size_explosion);
    fire = new Rectangle(getX(), getY(), size_explosion, size_explosion);
    setCenterX(invader.center_x());
    setCenterY(invader.center_y());
  }

  public boolean collide_with(Invader inv) {
    return fire.intersects(inv.shape());
  }

  public void draw(Graphics g) {
    anims[state.ordinal()].draw(getX(), getY(), g);
  }

  private float getX() {
    return fire.getX();
  }

  private float getY() {
    return fire.getY();
  }

  public boolean is_marked_for_destroy() {
    return (getX() > Window.WIDTH + 400) || (state == States.DESTROY);
  }

  private void setCenterX(float center_x) {
    fire.setCenterX(center_x);
  }

  private void setCenterY(float center_y) {
    fire.setCenterY(center_y);
  }

  private void setX(float f) {
    fire.setX(f);
  }

  public void update(Invaders invaders ,int delta) {
    if (state == States.MISSILE) {
      setX(getX() + delta * speed);
      anims[States.MISSILE.ordinal()].update(delta);
      invaders.check_collisions(this);
    } else if (state == States.EXPLOSION) {
      if (anims[States.EXPLOSION.ordinal()].is_stopped()) {
        state = States.DESTROY;
      }
    }
  }

  public boolean not_destroyed() {
    return state != States.EXPLOSION;
  }
}
