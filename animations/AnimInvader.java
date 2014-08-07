package animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimInvader {

  private Animation anim_invader;
  private float          width;
  private float          height;
  private float          size;

  public AnimInvader(float width, float height, float size) {
    anim_invader = new Animation();

    SpriteSheet sp_invad = null;
    try {
      sp_invad = new SpriteSheet("resources/invader01.png", 177, 123, new Color(0,255,0),34);
    } catch (SlickException e1) {
      e1.printStackTrace();
    }

    int frame_speed = 500;
    for (int i = 0; i < 2; i++) {
      anim_invader.addFrame(sp_invad.getSprite(i, 0), frame_speed);
    }

    this.size = size;
    this.width = width + (size * 7.5f);
    this.height = height + (size * 3.5f);
  }

  public void draw(float x, float y) {
    anim_invader.draw(x-(size*3.8f), y-(size*1.8f), width, height);
  }

  public void update() {
  }

}
