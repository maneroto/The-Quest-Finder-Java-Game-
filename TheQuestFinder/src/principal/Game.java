package principal;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import controlDeObjetos.KeyInput;
import estados.GameStateManager;
import imagenes.Assets;
/**
 * Clase primordial para el funcionamiento del juego,
 * sin ésta clase, ninguna de las demás clases tiene sentido, ya que ésta
 * es la encargada de hacer el loop del juego, por lo tanto,
 * es la que actualiza y renderiza todo estado actual
 * @author Emmanuel Antonio
 *
 */
public class Game implements Runnable{
	
	public static int width;
	public static int height;
	public String title;
	
	private Thread thread;
	
	private Ventana ventana;
	private BufferStrategy bs;
	private Graphics g;
	
	private boolean running = false;
	
	private GameStateManager gsm;
	private KeyInput keyInput;
	/**
	 * Constructor de Game
	 * @param title Titulo de la ventana
	 * @param width ancho de la ventana
	 * @param height alto de la ventana
	 */
	public Game( String title, int width, int height)
	{
		Game.width = width;
		Game.height = height;
		this.title = title;
	}
	/**
	 * Inicializa con los valores primarios
	 */
	public void init()
	{
		Assets.init();
		ventana = new Ventana(title, width, height);
		gsm = new GameStateManager();
		keyInput = new KeyInput(gsm);
		
		ventana.getFrame().addKeyListener(keyInput);
	}
	/**
	 * inicia el juego, inicializando los threads
	 */
	public synchronized void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	/**
	 * Detiene el juego
	 */
	public synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try
		{
			thread.join();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Acutaliza el juego a los siguientes valores
	 */
	public void tick()
	{
		gsm.tick();
	}
	/**
	 * Pinta el juego 
	 */
	public void render()
	{
		bs = ventana.getCanvas().getBufferStrategy();
		if (bs == null)
		{
			ventana.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		// Inicia a dibujar
		
		gsm.render(g);
			
		// Termina de dibujar
		bs.show();
		g.dispose();
	}
	/**
	 * 
	 */
	public void run()
	{
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now-lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				tick();
				render();
				ticks ++;
				delta--;
			}
			
			if (timer >= 1000000000)
			{
				System.out.println("Ticks and frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	/**
	 * 
	 * @param var
	 * @param min
	 * @param max
	 * @return
	 */
	public static double clamp(double var, double min, double max)
	{
		if (var <= min) return var = min;
		else if (var >= max) return var = max;
		else return var;
	}
	/**
	 * Regresa el ancho de la ventana
	 * @return ancho de la ventana
	 */
	public int getWidth() { return width;}
	/**
	 * Regresa el alto de la ventana
	 * @return alto de la ventana
	 */
	public int getHeight() { return height;}
}
