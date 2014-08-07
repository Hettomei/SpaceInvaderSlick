package controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;

import entities.InvaderFire;
import entities.Ship;

public class InvaderFires {

  public ArrayList<InvaderFire> invader_fires = new ArrayList<InvaderFire>();

  public void draw(Graphics g){
    Iterator<InvaderFire> it = invader_fires.iterator();

    while (it.hasNext()) {
      it.next().draw(g);
    }
  }

  public void update(Ship ship, int delta) {
    Iterator<InvaderFire> it = invader_fires.iterator();
    ArrayList<InvaderFire> fires_to_remove = new ArrayList<InvaderFire>();
    while (it.hasNext()) {
      InvaderFire f = it.next();
      f.update(ship, delta);
      if (f.is_marked_for_destroy()) {
        fires_to_remove.add(f);
      }
    }
    invader_fires.removeAll(fires_to_remove);
  }

  public void add(float x, float y) {
    invader_fires.add(new InvaderFire(x,y));
  }
}
