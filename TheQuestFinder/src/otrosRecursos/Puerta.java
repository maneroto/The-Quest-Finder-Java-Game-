package otrosRecursos;

import java.awt.Graphics;
import java.awt.Rectangle;

import caracteristicasPrincipales.ID;
import coleccionables.Coleccionable;
import controlDeObjetos.Handler;
import estados.GameStateManager;
import imagenes.Assets;
	/**
	 * Objeto que comparte muchas características de los coleccionables
	 * y que es el final del nivel y se consigue
	 * abrir despues de conseguir la llave
	 * @author Emmanuel Antonio
	 *
	 */
public class Puerta extends Coleccionable{
	
	private GameStateManager gsm;
	/**
	 * Constructor de clase Puerta
	 * @param x posiscion x
	 * @param y posiscion y
	 * @param id id del objeto
	 * @param handler handler del objeto
	 * @param gsm game state manager del objeto
	 */
	public Puerta(double x, double y, ID id, Handler handler, GameStateManager gsm) {
		super(x, y, id, handler);
		// TODO Auto-generated constructor stub
		width = 32;
		height = 32;
		bounds= new Rectangle(0, 0, width, height);
		this.gsm = gsm;
	}
	/**
	 * Actualiza los valores del objeto
	 */
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Pinta el objeto
	 */
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.door, (int) (x - handler.getCamera().getXOffset()),
				(int) (y - handler.getCamera().getYOffset()), width, height, null);
	}
	/**
	 * Genera un rectangulo a partir del tamaño del objeto
	 */
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return (new Rectangle((int) x + bounds.x, (int) y + bounds.y, bounds.width, bounds.height));
	}

	@Override
	/**
	 * Aumenta el valor del estado del juego
	 */
	public void sumarValor() {
		// TODO Auto-generated method stub
		if (Hud.getKeys() > 0)
		{
			Hud.setKeys(Hud.getKeys() - 1);
			gsm.setSate(gsm.getState() + 1);
			return;
		}
	}

}
