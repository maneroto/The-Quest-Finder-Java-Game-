package coleccionables;

import java.awt.Graphics;
import java.awt.Rectangle;

import caracteristicasPrincipales.ID;
import controlDeObjetos.Handler;
import imagenes.Assets;
import otrosRecursos.Hud;
/**
 * 
 * 
 * @author Emmanuel Antonio
 * @version 1.0
 *
 */
public class Coin extends Coleccionable{
	/**
	 * Constructor clase Coin
	 * @param x posiscion x
	 * @param y posiscion y
	 * @param id id del objeto
	 * @param handler handler del objeto
	 */
	public Coin(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
		// TODO Auto-generated constructor stub
		width = 10;
		height = 10;
		bounds= new Rectangle(0, 0, width, height);
		valor = 10;
	}
	/**
	* Actualiza el estado del objeto
	*/
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	/**
	* Pinta el objeto
	*/
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.coin, (int) (x - handler.getCamera().getXOffset()),
				(int) (y - handler.getCamera().getYOffset()), width, height, null);
	}
	/**
	* Genera un rectangulo a partir de los limites del objeto
	*/
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return (new Rectangle((int) x + bounds.x, (int) y + bounds.y, bounds.width, bounds.height));
	}
	/**
	 * Aumenta el valor de Score
	 */
	@Override
	public void sumarValor() {
		// TODO Auto-generated method stub
		Hud.setScore(Hud.getScore() + valor);
		handler.removeObject(this);
	}

}
