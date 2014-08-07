package animations;

import java.io.IOException;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class AnimMeteorite {

  private Animation anim_meteorite;
  private ParticleSystem smoke;

  public AnimMeteorite() {
    anim_meteorite = new Animation();
    SpriteSheet sp_fire = null;
    try {
      sp_fire = new SpriteSheet("resources/asteroid_bullet.png", 96, 80);
    } catch (SlickException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    int frame_speed = 10;
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 21; j++) {
        anim_meteorite.addFrame(sp_fire.getSprite(j, i), frame_speed);
      }
    }
    for (int i = 0; i < 17; i++) {
      anim_meteorite.addFrame(sp_fire.getSprite(i, 6), frame_speed);
    }

    try {
      smoke = ParticleIO.loadConfiguredSystem("resources/system.xml");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public void draw(float x, float y, int sizeX, int sizeY) {
    anim_meteorite.draw(x, y, sizeX, sizeY);
    smoke.setPosition(x+5, y+15);
    smoke.render();
  }

  public void update(int delta) {
    smoke.update(delta);
  }

}
