package controlDeObjetos;

import java.awt.Graphics;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

import caracteristicasPrincipales.GameObject;
import imagenes.GameCamera;
import worlds.World;
/**
 * Se encarga de manejar el estado de cada objeto en el juego por medio de un ArrayList
 * @author Emmanuel Antonio
 *@version 1.0
 */
public class Handler {

	public CopyOnWriteArrayList <GameObject> objeto;
	private World world;
	private GameCamera gameCamera;
	/**
	 * Constructor del Handler
	 */
	public Handler()
	{
		objeto = new CopyOnWriteArrayList <GameObject>();
	}
	/**
	 * Actualiza cada objeto dentro del ArrayList
	 */
	public void tick()
	{
		ListIterator <GameObject> itr = objeto.listIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.next();
			tempObject.tick();
		}
	}
	/**
	 * Determina la camara del Juego
	 * @param gameCamera camara del juego
	 */
	public void setCamera(GameCamera gameCamera)
	{
		this.gameCamera = gameCamera;
	}
	/**
	 * Determina el mundo del juego
	 * @param world mundo del juego
	 */
	public void setWorld(World world)
	{
		this.world = world;
	}
	/**
	 * Regresa el mundo del juego
	 * @return mundo del juego
	 */
	public World getWorld() { return world; }
	/**
	 * regresa la camara del juego
	 * @return camara del juego
	 */
	public GameCamera getCamera() { return gameCamera; }
	/**
	 * Pinta cada objeto del ArrayList por medio del Render de cada objeto
	 * @param g grafico donde se pinta
	 */
	public void render(Graphics g)
	{
		ListIterator <GameObject> itr = objeto.listIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.next();
			tempObject.render(g);
		}
	}
	/**
	 * Añade un objeto al ArrayList
	 * @param object objeto a añadir
	 */
	public void addObject(GameObject object)
	{
		this.objeto.add(object);
	}
	/**
	 * Quita un objeto del ArrayList
	 * @param object Objeto a remover
	 */
	public void removeObject(GameObject object)
	{
		this.objeto.remove(object);
	}
	
}
