package coleccionables;

import java.awt.Graphics;
import java.awt.Rectangle;

import caracteristicasPrincipales.ID;
import controlDeObjetos.Handler;
import imagenes.Assets;
import otrosRecursos.Hud;
/**
 * Coleccionable tipo key es la llave del nivel
 * @author Emmanuel Antonio
 *@version 1.0
 */
public class Key extends Coleccionable{
	/**
	* Constructor clase 
	* @param x posiscion x
	* @param y posiscion y
	* @param id id del objeto
	* @param handler handler del objeto
	*/
	public Key(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
		// TODO Auto-generated constructor stub
		width = 28;
		height = 11;
		bounds= new Rectangle(0, 0, width, height);
		valor = 1;
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
		g.drawImage(Assets.key, (int) (x - handler.getCamera().getXOffset()),
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
	 * Aumenta el valor de getKeys
	 */
	@Override
	public void sumarValor() {
		// TODO Auto-generated method stub
		Hud.setKeys(Hud.getKeys() + valor);
		handler.removeObject(this);
	}

}
