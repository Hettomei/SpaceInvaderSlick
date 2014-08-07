package animations;

import java.io.IOException;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import tools.Window;


public class AnimSpace {

  private ParticleSystem smoke;
  private Vector2f v = new Vector2f();

  public AnimSpace(){
    try {
      smoke = ParticleIO.loadConfiguredSystem("resources/space.xml");
    } catch (IOException e) {
      e.printStackTrace();
    }
    v.x = Window.WIDTH -100;
    v.y = Window.HEIGHT/2;

    smoke.setPosition(v.x, v.y);
  }

  public void draw() {
    smoke.setPosition(v.x, v.y);
    smoke.render();
  }

  public void update(int delta) {
    smoke.update(delta);
  }
}
