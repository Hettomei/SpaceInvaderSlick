package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.newdawn.slick.Graphics;

import tools.Window;


import entities.Invader;
import entities.Ship;
import entities.ShipFire;

public class Invaders {

  private ArrayList<Invader> invaders;
  private int                last_shoot;
  private Random             r = new Random();
  private InvaderFires       invader_fires;
  private GameController gc;

  public Invaders(int niveau, InvaderFires invaderFires, GameController gc) {
    invaders = new ArrayList<Invader>();

    if (niveau == 1) {
      for (int i = 10; i < Window.HEIGHT - 50; i += 50) {
        for (int j = Window.WIDTH - 500; j < Window.WIDTH - 50; j += 70) {
          invaders.add(new Invader(j, i, this));
        }
      }
    }
    this.invader_fires = invaderFires;
    last_shoot = 4600;
    this.gc = gc;
  }

  public void draw(Graphics g) {
    Iterator<Invader> it = invaders.iterator();

    while (it.hasNext()) {
      Invader invader = it.next();
      invader.draw(g);
    }
  }

  public void update(Ship ship, int delta) {
    Iterator<Invader> it = invaders.iterator();
    ArrayList<Invader> invaders_to_remove = new ArrayList<Invader>();

    while (it.hasNext()) {
      Invader invader = it.next();
      if (invader.is_alive()) {
        invader.update(delta);
      } else {
        invaders_to_remove.add(invader);
      }
    }
    invaders.removeAll(invaders_to_remove);

    last_shoot += delta;
    int taille = invaders.size();
    if (last_shoot > 900 && taille > 0) {
      invaders.get(r.nextInt(invaders.size())).fire();
      last_shoot = 0;
    }
  }

  public void check_collisions(ShipFire shipFire) {
    Iterator<Invader> it = invaders.iterator();

    while (shipFire.not_destroyed() && it.hasNext()) {
      Invader invader = it.next();
      if (invader.is_alive() && shipFire.collide_with(invader)) {
        shipFire.change_state_to_explosion(invader);
        invader.explode();
        gc.update_point(invader.getPoint());
      }
    }
  }

  public void fire(Invader invader) {
    invader_fires.add(invader.getX(), invader.center_y());
  }
}
