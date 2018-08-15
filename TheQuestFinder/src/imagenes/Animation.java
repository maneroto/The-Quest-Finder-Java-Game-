package imagenes;

import java.awt.image.BufferedImage;
/**
 * Si un objeto requiere animacion maneja el frame del objeto necesario
 * @author David Flores
 *
 */
public class Animation {
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	/**
	 * Constructor de animacion
	 * @param speed velocidad de la animacion
	 * @param frames cantidad de frames en la animacion
	 */
	public Animation(int speed, BufferedImage[] frames)
	{
		this.speed = speed;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
		timer = 0;
	}
	/**
	 * Regresa el frame necesario para seguir la animacion
	 * @return siguiente frame de la animacion
	 */
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}
	/**
	 * Actualiza el numero de frame de la animacion
	 */
	public void tick()
	{
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed)
		{
			index ++;
			timer = 0;
			if (index >= frames.length) index = 0;
		}
	}
}
