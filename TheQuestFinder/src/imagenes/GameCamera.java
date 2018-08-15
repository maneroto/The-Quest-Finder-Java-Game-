package imagenes;

import caracteristicasPrincipales.GameObject;
import controlDeObjetos.Handler;
import principal.Game;
import tiles.Tile;
/**
 * Camara del juego
 * @author David Flores
 *@version 1.0
 */
public class GameCamera {
	private double xOffset;
	private double yOffset;
	private Handler handler;
	/**
	 * Constructor de la Camara del juego
	 * @param handler el Handler de la camara
	 * @param xOffset la posiscion de la camara en x
	 * @param yOffset la posiscion de la camara en y
	 */
	public GameCamera(Handler handler, double xOffset, double yOffset)
	{
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	/**
	 * Mueve la camara una cierta distancia en x y y
	 * @param cantidadX cantidad a deslpazarse en x
	 * @param cantidadY cantidad a deslpazarse en y
	 */
	public void move(double cantidadX, double cantidadY)
	{
		xOffset += cantidadX;
		yOffset += cantidadY;
		checkBlankSpace();
	}
	/**
	 * Encuentra el margen de la ventana para dejar de desplazarse y no mostrar cuadros blancos
	 */
	public void checkBlankSpace()
	{
		if (xOffset < 0) xOffset = 0;
		else if(xOffset > handler.getWorld().getWidth() * Tile.WIDTH - Game.width)
		{
			xOffset = handler.getWorld().getWidth() * Tile.WIDTH - Game.width;
		}
		if (yOffset < 0) yOffset = 0;
		else if(yOffset > handler.getWorld().getHeight() * Tile.HEIGHT - Game.height)
		{
			yOffset = handler.getWorld().getHeight() * Tile.HEIGHT - Game.height;
		}
	}
	/**
	 * Centra la camara en un objeto en especifico
	 * @param o objeto de refernecia para centrar la camara
	 */
	public void centerOnObject(GameObject o)
	{
		xOffset = o.getX() - Game.width / 2 + o.getWidth() / 2;
		yOffset = o.getY() - Game.height / 2 + o.getHeight() / 2;
		checkBlankSpace();
	}
	/**
	 * Regresa la posiscion en x de la camara
	 * @return posiscion en x de la camara
	 */
	public double getXOffset() { return xOffset; }
	/**
	 * Regresa la posiscion en y de la camara
	 * @return posiscion en y de la camara
	 */
	public double getYOffset() { return yOffset; }
}
