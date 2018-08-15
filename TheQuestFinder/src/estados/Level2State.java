package estados;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import caracteristicasPrincipales.GameObject;
import caracteristicasPrincipales.ID;
import coleccionables.Chocolat;
import coleccionables.Coin;
import coleccionables.Key;
import personajes.Personaje;
import personajes.Player;
import personajes.Slime;
import personajes.Spider;
import tiles.Tile;
import worlds.World;
import controlDeObjetos.Handler;
import imagenes.GameCamera;
import otrosRecursos.Hud;
import otrosRecursos.Puerta;
/**
 * Clase encargada de hacer la ejecución de todo lo que sucede en el nivel 2
 * @author Emmanuel Antonio
 * @version 1.0
 */
public class Level2State extends State{

	private Handler handler;
	private World world;
	private boolean left, right, up, down;
	private Hud hud;
	
	/**
	 * Constructor de Level2State que recibe el administrador de estados para poder 
	 * cambiar de estado cuando sea requerido
	 * @param gsm
	 */
	public Level2State(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	/**
	 * Método encargado de cargar todo lo que será usado a lo largo del nivel,
	 * tal como el mundo, la cámara, el hud y los objetos que se deberán visualizar 
	 * a lo largo del juego
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		left = false;
		right = false;
		up = false;
		down = false;
		
		handler = new Handler();

		gameCamera = new GameCamera(handler, 0,0);
	
		world = new World(handler, "res/world/world2.txt");
		
		hud = new Hud();
		
		handler.setCamera(gameCamera);
		handler.setWorld(world);
		handler.addObject(new Puerta(48 * 32, 12*32, ID.Coleccionable, handler, gsm));
		handler.addObject(new Coin(21*32, (4*32) + 16, ID.Coleccionable, handler));
		handler.addObject(new Key(48*32, (1*32) + 16, ID.Coleccionable, handler));
		handler.addObject(new Chocolat(19*32, (17*32) + 16, ID.Coleccionable, handler));
		handler.addObject(new Player(handler.getWorld().getSpawnX() * Tile.WIDTH
				, handler.getWorld().getSpawnY() * Tile.HEIGHT, ID.Jugador, handler, this, gsm));
		handler.addObject(new Spider(29 * 32, 17 *32 , ID.Enemigo, handler));
		handler.addObject(new Slime(5 * 32, 17 *32 , ID.Enemigo, handler));
	}

	/**
	 * Método encargado de actualizar el estado tando del hud, como de los objetos
	 */
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		hud.tick();
		handler.tick();
	}

	/**
	 * Método encargado de repintar el estado tando del hud, como de los objetos y el
	 * mundo
	 */
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		world.render(g);
		handler.render(g);
		hud.render(g);
	}
	
	/**
	 * Método encargado de que, si las teclas presionadas son las de movimiento o golpe,
	 * el objeto jugador realice dichas acciones
	 * @param k
	 */
	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		for (GameObject o: handler.objeto)
		{
			if(o.getId() == ID.Jugador)
			{
				switch(k)
				{
					case KeyEvent.VK_A:
						o.setVelX(-3);
						left = true;
						break;
					case KeyEvent.VK_D:
						o.setVelX(3);
						right = true;
						break;
//					case KeyEvent.VK_RIGHT:
//						o.setVelX(3);
//						right = true;
//						break;
//					case KeyEvent.VK_LEFT:
//						o.setVelX(-3);
//						left = true;
//						break;
//					case KeyEvent.VK_W:
//						o.setVelY(-3);
//						up = true;
//						break;
//					case KeyEvent.VK_S:
//						o.setVelY(3);
//						down = true;
//						break;
					
					case KeyEvent.VK_J:
						((Personaje)o).atacar();
						break;
//					case KeyEvent.VK_DOWN:
//						((Personaje)o).atacar();
//						break;
						
				}
				if (((Personaje)o).canJump && k == KeyEvent.VK_SPACE)
				{
					o.setVelY(-7);
					((Personaje)o).canJump = false;
				}
				if(left && right) o.setVelX(0);
//				if(up && down) o.setVelY(0);
			}
		}
	}

	/**
	 * Método encargado de que, si las teclas soltadas son las de movimiento o golpe,
	 * el objeto jugador realice dichas acciones
	 * @param k
	 */
	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		for (GameObject o: handler.objeto)
		{
			if(o.getId() == ID.Jugador)
			{
				switch(k)
				{
					case KeyEvent.VK_A:
						left = false;
						break;
					case KeyEvent.VK_D:
						right = false;
						break;
//					case KeyEvent.VK_LEFT:
//						left = false;
//						break;
//					case KeyEvent.VK_RIGHT:
//						right = false;
//						break;
//					case KeyEvent.VK_W:
//						up = false;
//						break;
//					case KeyEvent.VK_S:
//						down = false;
//						break;
					case KeyEvent.VK_J:
						((Personaje)o).atacar();
						((Personaje)o).setAtacando(false);
						break;
//					case KeyEvent.VK_DOWN:
//						((Personaje)o).atacar();
//						((Personaje)o).setAtacando(false);
//						break;
				}
				if(!left && !right) o.setVelX(0);
				if(!up && !down) o.setVelY(0);
			}
		}
	}
}