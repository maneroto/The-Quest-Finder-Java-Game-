package estados;

import java.awt.Graphics;

import imagenes.GameCamera;

/**
 * Clase que da las pautas que deben seguir todos los estados
 * @author Emmanuel Antonio
 * @version 1.0
 */
public abstract class State {
	
	protected GameStateManager gsm;
	protected GameCamera gameCamera;
	
	public abstract void init();
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public GameCamera getCamera(){return gameCamera;}

}
