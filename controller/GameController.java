package controller;

import org.newdawn.slick.Graphics;
import entities.Ship;

public class GameController {

  private Ship     ship;
  private Invaders invaders;
  private ShipFires shipFires;
  private InvaderFires invaderFires;

  private long score;

  public GameController() {

    invaderFires = new InvaderFires();
    shipFires = new ShipFires();
    ship = new Ship(shipFires);
    invaders = new Invaders(1, invaderFires, this); //1 --> niveau 1
    score = 0;
  }

  public void draw(Graphics g) {
    ship.draw(g);
    invaders.draw(g);
    shipFires.draw(g);
    invaderFires.draw(g);
    g.drawString("Score : " + score, 300, 10);
  }

  public void key_up(int delta) {
    ship.up(delta);
  }

  public void key_down(int delta) {
    ship.down(delta);
  }

  public void key_right(int delta) {
    ship.right(delta);
  }

  public void key_left(int delta) {
    ship.left(delta);
  }

  public void update(int delta) {
    ship.update(delta);
    invaders.update(ship, delta);
    shipFires.update(invaders, delta);
    invaderFires.update(ship, delta);
  }

  public void key_space() {
    ship.fire();
  }

  public void update_point(int i) {
    this.score  += i;
  }
}
