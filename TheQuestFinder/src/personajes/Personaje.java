package personajes;
import java.awt.Graphics;
import java.awt.Rectangle;

import caracteristicasPrincipales.GameObject;
import caracteristicasPrincipales.ID;
import controlDeObjetos.Handler;
/**
 * Clase padre para los difererntes tipos de personaje
 * @author David Flores
 *@version 1.0
 */
public abstract class Personaje extends GameObject{
	
	protected Handler handler;
	protected int vida;
	protected double daño;
	protected long lastAtackTimer;
	protected long tiempoRecargaAtaque;
	protected long tiempoRecargaEspecial;
	protected long atackTimer;
	protected int dirAtaque;
	protected boolean atacando;
	protected int puntosMuerte;
	
	protected double gravedad;
	
	protected Rectangle bounds;
	public boolean canJump;
	/**
	 * Constructor de Personaje
	 * @param x posiscion x
	 * @param y posiscion y
	 * @param id id del objeto
	 * @param handler handler del objeto
	 */
	public Personaje(double x, double y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler = handler;
		this.dirAtaque = -1;
	}
	/**
	 * Actualiza los valores del objeto
	 */
	public abstract void tick();
	/**
	 * Pinta el objeto
	 */
	public abstract void render(Graphics g);	
	/**
	 * Realiza la accion de atacar
	 */
	public abstract void atacar();
	/**
	 * Cambia el valor de la posiscion en x
	 */
	public abstract void moveX();
	/**
	 * Cambia el valor de la posiscion en y
	 */
	public abstract void moveY();
	/**
	 * Cambia el estado del objeto a muerto
	 */
	public abstract void muerto();
	
	/**
	 * Reduce la cantidad de vida
	 * @param cantidad cantidad de vida a reducir
	 */
	public void herir(double cantidad)
	{
		this.vida -= cantidad;
		muerto();
	}
	/**
	 * Realiza la accion de mover
	 */
	public void move() {
		moveX();
		moveY();
		if(velX > 0) dirAtaque = 1;
		else if (velX < 0) dirAtaque = -1;
	}
	/**
	 * Detecta la colision con los limites del mundo
	 * @param x posiscion en x
	 * @param y posiscion en y
	 * @return verdadero o falso de la colision
	 */
	protected boolean collisionWithTile(int x, int y){
		return handler.getWorld().getTile(x, y).isSolid();
	}
	/**
	 * To string del objeto
	 */
	public String toString()
	{
		return 
				"Estadisticas del personaje tipo: " + id + "\n" +
				"Posicion X: " + x + "\n" +
				"Posicion Y: " + y + "\n" +
				"Ancho: " + width + "\n" +
				"Alto: " + height + "\n" +
				"Archivo de imagen: " + imagen + "\n" +
				"Velocidad en X: " + velX + "\n" +
				"Velocidad en Y: " + velY + "\n" +
				"Vida: " + vida + "\n" +
				"Daño: " + daño + "\n" +
				"Tiempo de recarga de ataque: " + tiempoRecargaAtaque + "\n" +
				"Tiempo de recarga de ataque especial: " + tiempoRecargaEspecial
				;
	}
	/**
	 * Genera un rectangulo a partir del tamaño del objeto
	 * @param xOffset 
	 * @param yOffset
	 * @return
	 */
	public Rectangle getBounds(double xOffset, double yOffset)
	{
		return (new Rectangle((int)(x + bounds.x + xOffset), 
				(int) (y + bounds.y + yOffset), bounds.width, bounds.height));
	}
	/**
	 * Detemina la vida del personaje
	 * @param vida vide del personaje
	 */
	public void setVida(int vida)
	{
		this.vida = vida;
	}
	/**
	 * Detemina el daño del personaje
	 * @param daño daño del personaje
	 */
	public void setDaño(double daño)
	{
		this.daño = daño;
	}
	/**
	 * Determina el tiempo de recaga del ataque
	 * @param tiempoRecargaAtaque tiempo de recarga
	 */
	public void setTiempoRecargaAtaque(int tiempoRecargaAtaque)
	{
		this.tiempoRecargaAtaque = tiempoRecargaAtaque;
	}
	/**
	 * determina el timepo de recarga del atqeu especial
	 * @param tiemporecargaEspecial tiempo de recarga
	 */
	public void setTiempoRecargaEspecial(int tiemporecargaEspecial)
	{
		this.tiempoRecargaEspecial = tiemporecargaEspecial;
	}
	/**
	 * Regresa la vida del personaje
	 * @return vide del personaje
	 */
	public int getVida()
	{
		return vida;
	}
	/**
	 * Regresa el daño del personaje
	 * @return daño del personaje
	 */
	public double getDaño()
	{
		return daño;
	}
	/**
	 * Regresa el tiempo de recarga del ataque
	 * @return tiempo de recarga del ataque
	 */
	public long getTiempoRecargaAtaque()
	{
		return tiempoRecargaAtaque;
	}
	/**
	 * Regres el tiempo de recarga del ataque especial
	 * @return tiempo de recarga del ataque especial
	 */
	public long getTiempoRecargaEspecial()
	{
		return tiempoRecargaEspecial;
	}
	/**
	 * Determina el estado de la accion atacar
	 * @param atacando
	 */
	public void setAtacando(boolean atacando)
	{
		this.atacando = atacando;
	}
}
