package coleccionables;

import java.awt.Graphics;
import java.awt.Rectangle;

import caracteristicasPrincipales.GameObject;
import caracteristicasPrincipales.ID;
import controlDeObjetos.Handler;
/**
 * Planilla de los objetos tipo Coleccionable
 * @author Emmanuel Antonio
 * @version 1.0
 *
 */
public abstract class Coleccionable extends GameObject{

	protected int valor;
	protected Handler handler;
	protected Rectangle bounds;
	/**
	* Constructor clase Coleccionable
	* @param x posiscion x
	* @param y posiscion y
	* @param id id del objeto
	* @param handler handler del objeto
	*/

	public Coleccionable(double x, double y,  ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		// TODO Auto-generated constructor stub
	}
	
	/**
	* Actualiza el estado del objeto
	*/
	public abstract void tick();
	/**
	* Pinta el objeto
	*/
	public abstract void render(Graphics g);
	/**
	* Genera un rectangulo a partir de los limites del objeto
	*/
	public abstract Rectangle getBounds();
	/**
	 * Aumenta el valor determinado dependiendo de la subclase
	 */
	public abstract void sumarValor();
	/**
	 * Genera un rectangulo a partir de los limtes del objeto pero desplazado
	 * @param xOffset desplazamiento en x
	 * @param yOffset desplazamiento en y
	 * @return Regresa un rectangulo generado
	 */
	public Rectangle getBounds(double xOffset, double yOffset)
	{
		return (new Rectangle((int)(x + bounds.x + xOffset), 
				(int) (y + bounds.y + yOffset), bounds.width, bounds.height));
	}
	/**
	 * to String del objeto
	 */
	public String toString()
	{
		return 
				"Estadisticas del coleccionable tipo: " + id + "\n" +
				"Posicion X: " + x + "\n" +
				"Posicion Y: " + y + "\n" +
				"Ancho: " + width + "\n" +
				"Alto: " + height + "\n" +
				"Archivo de imagen: " + imagen + "\n" +
				"Valor del coleccionable: " + valor
				;
	}
	/**
	 * Determina el valor del coleccionable
	 * @param valor
	 */
	public void setValor(int valor)
	{
		this.valor = valor;
	}
	/**
	 * Regresa el valor del coleccionable
	 * @return valor del coleccionable
	 */
	public int getValor()
	{
		return valor;
	}
	
}
