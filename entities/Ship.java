package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import tools.Window;

import controller.ShipFires;

import animations.AnimShip;


public class Ship {

  private AnimShip  anim_ship;
  private Rectangle shipRec;
  private float     speed;
  private ShipFires fires;
  private float size;
  private Polygon shipPoly;

  public Ship(ShipFires fires) {

    this.speed = 0.5f;
    this.fires = fires;
    this.size = 0.4f;

    Transform t = Transform.createScaleTransform(size, size);
    this.shipPoly = AnimShip.getPolygon();
    this.shipPoly = (Polygon) shipPoly.transform(t);

    this.shipRec = new Rectangle(0, 0, 170, 343);
    Shape p = shipRec.transform(t);
    this.shipRec = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
    this.shipPoly.setCenterX(shipRec.getCenterX());
    this.shipPoly.setCenterY(shipRec.getCenterY());

    this.anim_ship = new AnimShip();
  }

  public void draw(Graphics g) {
    anim_ship.draw(shipRec, size, g);
  }

  public void update(int delta) {
    anim_ship.update(delta);
  }

  private float getY() {
    return shipRec.getY();
  }

  private float getX() {
    return shipRec.getX();
  }

  private void setY(float f) {
    shipRec.setY(f);
    shipPoly.setCenterY(shipRec.getCenterY());
  }

  private void setX(float f) {
    shipRec.setX(f);
    shipPoly.setCenterX(shipRec.getCenterX());
  }

  public void up(int delta) {
    anim_ship.up();
    setY(getY() - (delta * speed));
    if (getY() < 0)
      setY(0);
  }

  public void down(int delta) {
    anim_ship.down();
    setY(getY() + (delta * speed));
    if (getY() > Window.HEIGHT - getHeight())
      setY(Window.HEIGHT - getHeight());
  }

  public void left(int delta) {
    setX(getX() - (delta * speed));
    if (getX() < 0)
      setX(0);
  }

  public void right(int delta) {
    setX(getX() + (delta * speed));
    if (getX() > Window.WIDTH - getWidth())
      setX(Window.WIDTH - getWidth());
  }

  private float getHeight() {
    return shipRec.getHeight();
  }

  private float getWidth() {
    return shipRec.getWidth();
  }

  public void fire() {
    fires.add(getWidth() + getX() - 5, getCenterY());
  }

  float getCenterY() {
    return shipRec.getCenterY();
  }

  float getCenterX() {
    return shipRec.getCenterX();
  }

  public Shape shape() {
    return shipPoly;
  }
}
