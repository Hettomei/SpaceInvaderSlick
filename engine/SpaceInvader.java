package engine;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import controller.GameController;

import tools.Window;


public class SpaceInvader extends BasicGame {

  // private AnimSpace anim_space;

  private Input          i;
  private GameContainer  container;
  private GameController gc;

  public SpaceInvader() {
    super("Space Invader Tim");
  }

  @Override
  public void init(GameContainer container) throws SlickException {
    this.container = container;
    gc = new GameController();
  }

  @Override
  public void render(GameContainer container, Graphics g) throws SlickException {
    g.drawString("Haut / Bas / Espace", 100, 10);
    gc.draw(g);
  }

  @Override
  public void update(GameContainer container, int delta) throws SlickException {

    i = container.getInput();

    if (i.isKeyDown(Input.KEY_UP)) {
      gc.key_up(delta);
    } else if (i.isKeyDown(Input.KEY_DOWN)) {
      gc.key_down(delta);
    }

    if (i.isKeyDown(Input.KEY_RIGHT)) {
      gc.key_right(delta);
    } else if (i.isKeyDown(Input.KEY_LEFT)) {
      gc.key_left(delta);
    }

    if (i.isKeyDown(Input.KEY_F)) {
      gc.key_space();
    }

    gc.update(delta);
  }

  public void keyPressed(int key, char c) {
    if (key == Input.KEY_ESCAPE) {
      container.exit();
    }
    if (key == Input.KEY_SPACE) {
      gc.key_space();
    }
  }

  public static void main(String[] args) throws SlickException {
    AppGameContainer app = new AppGameContainer(new SpaceInvader());

    app.setDisplayMode(Window.WIDTH, Window.HEIGHT, false);
    app.start();
  }

  /**
   * @see org.newdawn.slick.BasicGame#mouseClicked(int, int, int, int)
   */
  public void mouseClicked(int button, int x, int y, int clickCount) {
    System.out.println("shipPoly.addPoint(" + x + ", " + y + ");");
  }
}
