package imagenes;

import java.awt.image.BufferedImage;
/**
 * Hoja con las texturas de los objetos
 * @author David Flores
 *@version 1.0
 */
public class SpriteSheet {
	
	private BufferedImage sheet;
	/**
	 * Constructor del Sprite Sheet
	 * @param sheet imagen a cargar en el sprite sheet
	 */
	public SpriteSheet(BufferedImage sheet)
	{
		this.sheet = sheet;
	}
	/**
	 * Corta una seccion de la imagen y la regresa
	 * @param x posiscion x de corte
	 * @param y	posiscion y de corte
	 * @param width	ancho de corte
	 * @param height	largo de corte
	 * @return	regresa la imagen cortada del sprite sheet
	 */
	public BufferedImage crop(int x, int y, int width, int height)
	{
		return sheet.getSubimage(x, y, width, height);
	}
}
