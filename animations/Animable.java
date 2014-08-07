package animations;

import org.newdawn.slick.Graphics;

public interface Animable {
  void draw(float x, float y, Graphics g);

  void update(int delta);

  boolean is_stopped();
}
