package personajes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import caracteristicasPrincipales.GameObject;
import caracteristicasPrincipales.ID;
import coleccionables.Coleccionable;
import controlDeObjetos.Handler;
import estados.GameStateManager;
import estados.State;
import imagenes.Animation;
import imagenes.Assets;
import otrosRecursos.Hud;
import tiles.Tile;
/**
 * Objeto controlado por el jugador
 * @author David Flores
 *@version 1.0
 */
public class Player extends Personaje{

	private Animation animRight;
	private Animation animLeft;
	private Animation animStand;
	private Animation animAtqR;
	private Animation animAtqL;
	
	private GameStateManager gsm;
	private State s;
	/**
	 * Constructor Player
	 * @param x posiscion x
	 * @param y posiscion y
	 * @param id id del objeto
	 * @param handler handler del objeto
	 * @param s estado del objeto
	 * @param gsm es el administrador de estados
	 */
	public Player(double x, double y, ID id, Handler handler, State s, GameStateManager gsm) {
		super(x, y,  id, handler);
		// TODO Auto-generated constructor stub
		gravedad = 0.25;
		vida = 100;
		daño = 20;
		width = 32;
		height = 32;
		this.s = s;
		bounds= new Rectangle(0, 0, width, height);
		bounds.x = 11;
		bounds.width = 10;
		bounds.height = 30;
		this.id = ID.Jugador;
		this.gsm = gsm;
		
		animLeft = new Animation(50, Assets.playerLeft);
		animRight = new Animation(50, Assets.playerRight);
		animStand = new Animation(500, Assets.playerStand);
		
		animAtqR = new Animation(30, Assets.playerAtackRight);
		animAtqL = new Animation(30, Assets.playerAtackLeft);
		atacando = false;
		
		tiempoRecargaAtaque = 300;
		atackTimer = tiempoRecargaAtaque;
	}

	@Override
	/**
	 * Actualiza los valores del objeto
	 */
	public void tick() {
		animRight.tick();
		animLeft.tick();
		animStand.tick();
		animAtqR.tick();
		animAtqL.tick();
		
		if(velY < 8) velY += gravedad;
		move();
		
		colisionItem((int)velX, 0);
		colisionItem(0, (int)velY);
		
		vida = Hud.getVida();
		
		vida = (int) clamp(vida, 0, 100);
		
		s.getCamera().centerOnObject(this);
	}
	/**
	 * Disminunye el valor de vide una cierta cantidad
	 * @param cantidad cantidad por disminuir
	 */
	public void herir(double cantidad)
	{
		Hud.setVida((int) (Hud.getVida() - cantidad));;
		muerto();
	}
	/**
	 * Movimeinto de la posiscion en x
	 */
	public void moveX(){
		if(velX > 0){//Moving right
			int tx = (int) (x + velX + bounds.x + bounds.width) / Tile.WIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.HEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.HEIGHT)){
				x += velX;
			}else{
				x = tx * Tile.WIDTH - bounds.x - bounds.width - 1;
			}
			
		}else if(velX < 0){//Moving left
			int tx = (int) (x + velX + bounds.x) / Tile.WIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.HEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.HEIGHT)){
				x += velX;
			}else{
				x = tx * Tile.WIDTH + Tile.WIDTH - bounds.x;
			}
			
		}
	}
	/**
	 * Movimiento de la posiscion en y
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
				canJump = false;
			}else{
				y = ty * Tile.HEIGHT - bounds.y - bounds.height - 1;
				canJump = true;
			}
			
		}
	}
	/**
	 * Remueve el objeto del ArrayList del Handler si su vida llega a 0
	 * Cambia el estado al estado de muerto
	 */
	public void muerto()
	{
		if (Hud.getVida() <= 0)
		{
			handler.removeObject(this);
			gsm.setSate(GameStateManager.DEAD_STATE);
		}
	}
	
	@Override
	/**
	 * Realiza la accion de atacar
	 */
	public void atacar()
	{
		atackTimer += System.currentTimeMillis() - lastAtackTimer;
		lastAtackTimer = System.currentTimeMillis();
		
		if(atackTimer < tiempoRecargaAtaque)return;
		
		atacando = true;
		
		Rectangle cb = getBounds(0,0);
		Rectangle ataque = new Rectangle();
		int atqWidth = 22;
		int atqHeight = 32;
		ataque.width = atqWidth + 5;
		ataque.height = atqHeight;
		
		if (dirAtaque == -1)
		{
			ataque.x = cb.x - atqWidth - 5;
			ataque.y = cb.y + cb.height / 2 - atqHeight / 2;
		}
		else if(dirAtaque == 1)
		{
			ataque.x = cb.x + atqWidth - 10;
			ataque.y = cb.y + cb.height / 2 - atqHeight / 2;
		}
		
		atackTimer = 0;
		
		for (GameObject o: handler.objeto)
		{
			if(o.getId() == ID.Enemigo)
			{
				if(o.getBounds().intersects(ataque))
				{
					System.out.println("Boom");
					((Personaje) o).herir(daño);
					((Personaje) o).setX(((Personaje) o).getX() + (((Personaje) o).getVelX() / 2 - 8) *-1);
					return;
				}
			}
		}
	}
	
	@Override
	/**
	 * Pinta el objeto
	 * @param g Graphics donde se dibuja el objeto
	 */
	public void render(Graphics g) {
		/*Rectangle cb = getBounds(0,0);
		int ay = cb.y + cb.height / 2 - 30 / 2;

		g.setColor(Color.RED);
		g.fillRect((int)(getBounds().x + velX - handler.getCamera().getXOffset())
				,(int) (getBounds().y - handler.getCamera().getYOffset()),
				getBounds().width, height);
				g.setColor(Color.RED);
		if(dirAtaque == 1)
		{
			g.drawRect((int) (cb.x + 22 - 10 - s.getCamera().getXOffset()), (int)(ay- s.getCamera().getYOffset()), 27, 30);
		}
		else
		{
			g.drawRect((int) (cb.x - 22 - 5 - s.getCamera().getXOffset()), (int)(ay- s.getCamera().getYOffset()), 27, 30);
		}*/
		g.drawImage(getCurrentAnimationFrame(), (int) (x - s.getCamera().getXOffset())
				, (int) (y - s.getCamera().getYOffset()), 
				width, height, null);
	}
	/**
	 * Consigue el siguiente frame de la animacion
	 * @return frame de la animacion
	 */
	public BufferedImage getCurrentAnimationFrame()
	{
		if (atacando)
		{
			if(dirAtaque == 1) return animAtqR.getCurrentFrame();
			else if(dirAtaque == -1) return animAtqL.getCurrentFrame();
		}
		if (velX > 0) return animRight.getCurrentFrame();
		else if(velX < 0) return animLeft.getCurrentFrame();
		else return animStand.getCurrentFrame();
	}
	
	@Override
	/**
	 * Construye un rectangulo a partir del tamaño del objeto
	 * @return rectangulo creado
	 */
	public Rectangle getBounds() {
			return new Rectangle((int)x + 11, (int)y, 12, height);
	}
	/**
	 * Determina la colision con un objeto solido
	 * @param x posiscion en x
	 * @param y posiscion en y
	 * @return regresa el valor booleano sobre si colisiono o no
	 */
	public boolean colisionSolido(int x, int y)
	{
		return handler.getWorld().getTile(x, y).getId() == ID.Solido;
	}
	/**
	 * Determina la coliscion con un coleccionable
	 * @param xOffset posiscion en x
	 * @param yOffset posiscion en y
	 */
	public void colisionItem(double xOffset, double yOffset)
	{
		for (GameObject o: handler.objeto)
		{
			if (o.getId() == ID.Coleccionable)
			{
				if(((Coleccionable)o).getBounds(0,0).intersects(getBounds(xOffset,yOffset)))
				{
					((Coleccionable)o).sumarValor();
				}
			}
		}
	}

}
