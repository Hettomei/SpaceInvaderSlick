package animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimMissileAlien implements Animable {

  private Animation      anim_missile;
  private float          width;
  private float          height;
  private float          size;

  public AnimMissileAlien(float width, float height, float size) {
    
    anim_missile = new Animation();
    SpriteSheet sp_fire = null;
    try {
      sp_fire = new SpriteSheet("resources/fireball01.png", 31, 31, Color.white);
    } catch (SlickException e1) {
      e1.printStackTrace();
    }

    int frame_speed = 30;
    for (int i = 0; i < 8; i++) {
      anim_missile.addFrame(sp_fire.getSprite(i, 0), frame_speed);
    }

    anim_missile.setPingPong(true);

    this.size = size;
    this.width = width + (size * 40);
    this.height = height + (size * 16);
  }

  public void draw(float x, float y, Graphics g) {
    anim_missile.draw(x - (size * 30), y - (size * 8), width, height);
  }

  public void update(int delta) {
  }

  @Override
  public boolean is_stopped() {
    return false;
  }
}
