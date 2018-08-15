package personajes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import caracteristicasPrincipales.GameObject;
import caracteristicasPrincipales.ID;
import controlDeObjetos.Handler;
import imagenes.Animation;
import imagenes.Assets;
import otrosRecursos.Hud;
import tiles.Tile;
/**
 * Personaje Spider
 * @author David Flores
 *@version 1.0
 */
public class Spider extends Personaje{

	private Animation animRight;
	private Animation animLeft;
	private int speed;
	/**
	 * Constructor Spider
	 * @param x posiscion x
	 * @param y posiscion y
	 * @param id id del objeto
	 * @param handler hanlder del objeto
	 */
	public Spider(double x, double y, ID id, Handler handler) {
		super(x, y,  id, handler);
		// TODO Auto-generated constructor stub
		gravedad = 0.25;
		vida = 80;
		daño = 2;
		width = 32;
		height = 27;
		bounds= new Rectangle(0, 0, width, height);
		bounds.x = 5;
		bounds.width = 15;
		bounds.height = 25;
		
		animLeft = new Animation(20, Assets.spiderLeft);
		animRight = new Animation(20, Assets.spiderRight);
		
		speed = 3;
		velX = speed;
		puntosMuerte = 35;
		
		tiempoRecargaAtaque = 100;
		atackTimer = tiempoRecargaAtaque;
	}

	@Override
	/**
	 * Actualiza los valores del objeto
	 */
	public void tick() {
		animRight.tick();
		animLeft.tick();
		
		if(velY < 10) velY += gravedad;
		move();
		
		colisionJugador((int)velX, 0);
		colisionJugador(0, (int)velY);
	}
	/**
	 * Cambia la posiscion en X
	 */
	public void moveX(){
		if(velX > 0){//Moving right
			int tx = (int) (x + velX + bounds.x + bounds.width) / Tile.WIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.HEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.HEIGHT)){
				x += velX;
			}else{
				x = tx * Tile.WIDTH - bounds.x - bounds.width - 1;
				velX *= -1;
			}
			
		}else if(velX < 0){//Moving left
			int tx = (int) (x + velX + bounds.x) / Tile.WIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.HEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.HEIGHT)){
				x += velX;
			}else{
				x = tx * Tile.WIDTH + Tile.WIDTH - bounds.x;
				velX *= -1;
			}
			
		}
		checkEmptyTile();
	}
	
	/**
	 * Cambia la posiscion en Y
	 */
	public void moveY(){
		if(velY < 0){//Up
			int ty = (int) (y + velY + bounds.y) / Tile.HEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.WIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.WIDTH, ty)){
				y += velY;
			}else{
				y = ty * Tile.HEIGHT + Tile.HEIGHT - bounds.y;
				velY = 1;
			}
			
		}else if(velY > 0){//Down
			int ty = (int) (y + velY + bounds.y + bounds.height) / Tile.HEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.WIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.WIDTH, ty)){
				y += velY;
			}else{
				y = ty * Tile.HEIGHT - bounds.y - bounds.height - 1;
			}
			
		}
	}
	/**
	 * Checa si hay piso en la posiscion delante de el en caso de que sea cierto cambia la direccion del movimiento
	 */
	public void checkEmptyTile()
	{
		if (velX > 0 && velY > 0)
		{
			int tx = (int) (x + velX + bounds.x + bounds.width) / Tile.WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y + velY) / Tile.HEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height + velY) / Tile.HEIGHT)){
				velX *= -1;
			}
		}
		
		if (velX < 0 && velY > 0)
		{
			int tx = (int) (x + velX + bounds.x) / Tile.WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y + velY) / Tile.HEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height + velY) / Tile.HEIGHT)){
				velX *= -1;
			}
		}
	}
	/**
	 * Pinta el objeto
	 * @param g Graphics donde pinta el objeto
	 */
	@Override
	public void render(Graphics g) {
		/*g.setColor(Color.RED);
		g.fillRect((int)(getBounds().x + velX - handler.getCamera().getXOffset())
				,(int) (getBounds().y - handler.getCamera().getYOffset()),
				getBounds().width, height);*/
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getCamera().getXOffset())
				, (int) (y - handler.getCamera().getYOffset()), 
				width, height, null);
	}
	/**
	 * Regresa el frame siguiente de la animacion
	 * @return frame de la animacion 
	 */
	public BufferedImage getCurrentAnimationFrame()
	{
		if (velX > 0) return animRight.getCurrentFrame();
		else return animLeft.getCurrentFrame();
	}
	/**
	 * Crea un rectangulo a partir del tamaño del objeto
	 */
	@Override
	public Rectangle getBounds() {
			return new Rectangle((int)x + 11, (int)y, 12, height);
	}
	/**
	 * Regresa si hay colision con un solido
	 * @param x posiscion x
	 * @param y posiscion y
	 * @return Boolean sobre la colision
	 */
	public boolean colisionSolido(int x, int y)
	{
		return handler.getWorld().getTile(x, y).getId() == ID.Solido;
	}
	/**
	 * Determina si colisiona con el jugador en caso de que sea cierto ataca
	 * @param xOffset posisicon x
	 * @param yOffset posiscion y
	 */
	public void colisionJugador(double xOffset, double yOffset)
	{
		for (GameObject o: handler.objeto)
		{
			if (o.getId() == ID.Jugador)
			{
				if(((Player)o).getBounds(0,0).intersects(getBounds(xOffset,yOffset)))
				{
					atackTimer += System.currentTimeMillis() - lastAtackTimer;
					lastAtackTimer = System.currentTimeMillis();
					
					setX(getX() - (getVelX() / 1.5));
					
					if (atackTimer < tiempoRecargaAtaque) return;
					
					((Player)o).herir(daño);
					
					atackTimer = 0;
				}
			}
		}
	}
	/**
	 * Realiza la accion de atacar
	 */
	@Override
	public void atacar() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Elimina el objeto del ArrayListe del Handler
	 */
	public void muerto()
	{
		if (this.vida <= 0)
		{
			handler.removeObject(this);
			Hud.setScore(Hud.getScore() + puntosMuerte);
		}
	}
	

}
