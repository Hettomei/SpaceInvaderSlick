package animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;

public class AnimShip {

  private Animation[] anim_ship;
  private enum Moves {
    CENTER_TO_UP, DOWN_TO_UP, CENTER, CENTER_TO_DOWN, UP_TO_DOWN, UP_TO_CENTER, DOWN_TO_CENTER
  };

  private enum States {
    UP, DOWN, FIX
  }

  private States current_state   = States.FIX;
  private States previous_state  = current_state;
  private int    time_same_state = 0;

  private Moves  current_move    = Moves.CENTER;

  public AnimShip() {

    //float width, float height, float size
    SpriteSheet sp_ship = null;

    try {
      sp_ship = new SpriteSheet("resources/ship.gif", 170, 343);
    } catch (SlickException e) {
      e.printStackTrace();
    }

    anim_ship = new Animation[Moves.values().length];
    for (int i = 0; i < anim_ship.length; i++) {
      anim_ship[i] = new Animation();
      anim_ship[i].setLooping(false);
    }

    int frame_speed = 50;
    for (int i = 5; i < 8; i++) {
      anim_ship[Moves.CENTER_TO_UP.ordinal()].addFrame(sp_ship.getSprite(i, 0),
          frame_speed);
    }
    for (int i = 1; i < 8; i++) {
      anim_ship[Moves.DOWN_TO_UP.ordinal()].addFrame(sp_ship.getSprite(i, 0),
          frame_speed);
    }
    anim_ship[Moves.CENTER.ordinal()].addFrame(sp_ship.getSprite(4, 0),
        frame_speed);
    for (int i = 3; i > 0; i--) {
      anim_ship[Moves.CENTER_TO_DOWN.ordinal()].addFrame(
          sp_ship.getSprite(i, 0), frame_speed);
    }
    for (int i = 7; i > 0; i--) {
      anim_ship[Moves.UP_TO_DOWN.ordinal()].addFrame(sp_ship.getSprite(i, 0),
          frame_speed);
    }
    for (int i = 7; i > 3; i--) {
      anim_ship[Moves.UP_TO_CENTER.ordinal()].addFrame(sp_ship.getSprite(i, 0),
          frame_speed);
    }
    for (int i = 1; i < 5; i++) {
      anim_ship[Moves.DOWN_TO_CENTER.ordinal()].addFrame(
          sp_ship.getSprite(i, 0), frame_speed);
    }
  }

  public void draw(Rectangle ship, float size, Graphics g) {
    anim_ship[current_move.ordinal()].draw(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
  }

  private void change_move_to(Moves m) {
    current_move = m;
    need_restart();
  }

  private void need_restart() {
    if (anim_ship[current_move.ordinal()].isStopped()) {
      anim_ship[current_move.ordinal()].restart();
    }
  }

  public void up() {
    // need to select the good animation
    if (current_state == States.FIX) {
      change_move_to(Moves.CENTER_TO_UP);
    } else if (current_state == States.DOWN) {
      change_move_to(Moves.DOWN_TO_UP);
    }
    current_state = States.UP;
    time_same_state = 0;
  }

  public void down() {
    // need to select the good animation
    if (current_state == States.FIX) {
      change_move_to(Moves.CENTER_TO_DOWN);
    } else if (current_state == States.UP) {
      change_move_to(Moves.UP_TO_DOWN);
    }
    current_state = States.DOWN;
    time_same_state = 0;
  }

  public void fix() {
    // need to select the good animation
    if (current_state == States.UP) {
      change_move_to(Moves.UP_TO_CENTER);
    } else if (current_state == States.DOWN) {
      change_move_to(Moves.DOWN_TO_CENTER);
    }
    current_state = States.FIX;
    time_same_state = 0;
  }

  public void update(int delta) {
    // method to anim the fly to go straight(? good english ?)
    if (current_state != States.FIX) {
      if (previous_state == current_state) {
        time_same_state += delta;
        if (time_same_state > 100 && previous_state != States.FIX) {
          fix();
        }
      }
    }
    previous_state = current_state;
  }

  public static Polygon getPolygon(){
    Polygon shipPoly = new Polygon();

    shipPoly.addPoint(4, 121);
    shipPoly.addPoint(35, 120);
    shipPoly.addPoint(38, 113);
    shipPoly.addPoint(28, 89);
    shipPoly.addPoint(28, 72);
    shipPoly.addPoint(66, 20);
    shipPoly.addPoint(104, 20);
    shipPoly.addPoint(80, 69);
    shipPoly.addPoint(81, 97);
    shipPoly.addPoint(77, 114);
    shipPoly.addPoint(106, 134);
    shipPoly.addPoint(118, 121);
    shipPoly.addPoint(130, 118);
    shipPoly.addPoint(162, 141);
    shipPoly.addPoint(168, 144);
    shipPoly.addPoint(170, 191);
    shipPoly.addPoint(161, 201);
    shipPoly.addPoint(127, 220);
    shipPoly.addPoint(106, 207);
    shipPoly.addPoint(77, 236);
    shipPoly.addPoint(85, 254);
    shipPoly.addPoint(81, 267);
    shipPoly.addPoint(104, 318);
    shipPoly.addPoint(74, 318);
    shipPoly.addPoint(38, 277);
    shipPoly.addPoint(31, 268);
    shipPoly.addPoint(32, 239);
    shipPoly.addPoint(44, 232);
    shipPoly.addPoint(36, 220);
    shipPoly.addPoint(5, 220);

    return shipPoly;
  }
}
