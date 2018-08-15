package coleccionables;

import java.awt.Graphics;
import java.awt.Rectangle;

import caracteristicasPrincipales.ID;
import controlDeObjetos.Handler;
import imagenes.Assets;
import otrosRecursos.Hud;
/**
 * Coleccionable chocolate, aumenta la vida
 * @author Emmanuel Antonio
 * @version 1.0
 *
 */
public class Chocolat extends Coleccionable{
	/**
	 * Constructor del objeto Chocolat
	 * @param x posiscion x
	 * @param y	posiscion y
	 * @param id	Id del objeto
	 * @param handler	handler que se ocupa del objeto
	 */
	public Chocolat(double x, double y, ID id, Handler handler) {
		super(x, y, id, handler);
		// TODO Auto-generated constructor stub
		width = 28;
		height = 20;
		bounds= new Rectangle(0, 0, width, height);
		valor = 20;
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
		g.drawImage(Assets.chocolat, (int) (x - handler.getCamera().getXOffset()),
				(int) (y - handler.getCamera().getYOffset()), width, height, null);
	}
	/**
	 * Genera un rectangulo a partir de los limites del objeto
	 */
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return (new Rectangle((int) x + bounds.x, (int) y + bounds.y, bounds.width, bounds.height));
	}
	/**
	 * Aumenta el valor de la vida
	 */
	@Override
	public void sumarValor() {
		// TODO Auto-generated method stub
		Hud.setVida(Hud.getVida() + valor);
		handler.removeObject(this);
	}

}
