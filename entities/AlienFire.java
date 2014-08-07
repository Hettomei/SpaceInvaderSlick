package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import animations.AnimExplosion;
import animations.AnimMissileAlien;
import animations.Animable;

public class AlienFire extends Fire{

  enum States {
    DESTROY, EXPLOSION, MISSILE
  }

  private Animable[] anims;
  private Shape      fire;

  private float      speed;
  private States     state;

  AlienFire(float x, float y) {
    float size = 1f;// 0.7f;
    float width = 40 * size;
    float height = 20 * size;
    speed = 0.1f;
    state = States.MISSILE;

    fire = new Rectangle(x, y, width, height);
    anims = new Animable[3];
    anims[States.MISSILE.ordinal()] = new AnimMissileAlien(width, height, size);
  }

  private void check_collisions(Ship ship) {
    if (state != States.EXPLOSION) {
      if (/*ship.is_alive() && */ collide_with(ship)) {
        //ship.explode();
        change_state_to_explosion(ship);
      }
    }
  }

  private void change_state_to_explosion(Ship ship) {
    // need to align center of explosion and center of invader
    state = States.EXPLOSION;
    int size_explosion = 100;
    anims[States.EXPLOSION.ordinal()] = new AnimExplosion(size_explosion,
        size_explosion);
    fire = new Rectangle(getX(), getY(), size_explosion, size_explosion);
    setCenterX(ship.getCenterX());
    setCenterY(ship.getCenterY());
  }

  public boolean collide_with(Ship ship) {
    return fire.intersects(ship.shape());
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
    return (getX() < -300) || (state == States.DESTROY);
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

  public void update(Ship ship ,int delta) {
    if (state == States.MISSILE) {
      setX(getX() - delta * speed);
      anims[States.MISSILE.ordinal()].update(delta);
      check_collisions(ship);
    } else if (state == States.EXPLOSION) {
      if (anims[States.EXPLOSION.ordinal()].is_stopped()) {
        state = States.DESTROY;
      }
    }
  }
}
