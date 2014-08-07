package animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimExplosion implements Animable {

  private Animation explode;
  private float HEIGHT;
  private float WIDTH;

  public AnimExplosion(float width, float height) {
    SpriteSheet explosion = null;
    try {
      explosion = new SpriteSheet("resources/explosion.png", 256, 256);
    } catch (SlickException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    explode = new Animation();

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        explode.addFrame(explosion.getSprite(j, i), 20);
      }
    }
    explode.setLooping(false);
    WIDTH = width;
    HEIGHT = height;
  }

  @Override
  public void draw(float x, float y, Graphics g) {
    explode.draw(x, y, WIDTH, HEIGHT);
  }

  @Override
  public boolean is_stopped() {
    return explode.isStopped();
  }

  @Override
  public void update(int delta) {
  }
}
