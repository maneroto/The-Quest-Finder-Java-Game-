package imagenes;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Carga una imagen
 * @author David Flores
 *
 */
public class ImageLoader {
	/**
	 * Carga una imagen y regresa el Buffered Image
	 * @param path la direccion del archivo de la imagen
	 * @return la imagen cargada en un buffered Image
	 */
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
