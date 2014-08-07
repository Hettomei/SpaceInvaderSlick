package controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;

import entities.ShipFire;

public class Fires {
  private ArrayList<ShipFire> fires = new ArrayList<ShipFire>();

  public void add(float x, float y) {
    fires.add(new ShipFire(x,y));
  }

  public void draw(Graphics g) {
    Iterator<ShipFire> it = fires.iterator();

    while (it.hasNext()) {
      it.next().draw(g);
    }
  }

  public void update(Invaders invaders, int delta) {
    Iterator<ShipFire> it = fires.iterator();
    ArrayList<ShipFire> fires_to_remove = new ArrayList<ShipFire>();
    while (it.hasNext()) {
      ShipFire f = it.next();
      f.update(invaders, delta);
      if (f.is_marked_for_destroy()) {
        fires_to_remove.add(f);
      }
    }
    fires.removeAll(fires_to_remove);
  }
}
