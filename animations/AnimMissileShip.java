package animations;

import java.io.IOException;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class AnimMissileShip implements Animable {

  private Animation      anim_missile;
  private ParticleSystem smoke;
  private float          width;
  private float          height;
  private float          size;

  public AnimMissileShip(float width, float height, float size) {
    
    anim_missile = new Animation();
    SpriteSheet sp_fire = null;
    try {
      sp_fire = new SpriteSheet("resources/blue_fire_ball.png", 81, 46,
          new Color(0, 0, 0), 1);
    } catch (SlickException e1) {
      e1.printStackTrace();
    }

    int frame_speed = 30;
    for (int i = 0; i < 4; i++) {
      anim_missile.addFrame(sp_fire.getSprite(i, 0), frame_speed);
    }

    anim_missile.setPingPong(true);

    try {
      smoke = ParticleIO.loadConfiguredSystem("resources/smoke.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.size = size;
    this.width = width + (size * 40);
    this.height = height + (size * 16);
  }

  public void draw(float x, float y, Graphics g) {
    anim_missile.draw(x - (size * 30), y - (size * 8), width, height);
    smoke.setPosition(x + 10, y + (size * 10));
    smoke.render();
  }

  public void update(int delta) {
    smoke.update(delta);
  }

  @Override
  public boolean is_stopped() {
    return false;
  }
}
