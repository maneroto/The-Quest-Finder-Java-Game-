package estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import estados.GameStateManager;
import imagenes.Assets;
import principal.Game;

/**
 * Clase encargada de ejecutar el estado producido cuando el jugador pasa todos
 * los niveles ("you win")
 * @author Emmanuel Antonio
 * @version 1.0
 *
 */
public class VictoryState extends State{
	
	private String message = "Presiona enter para continuar";
	private Font font;
	
	/**
	 * Constructor de DeadState que recibe el manager para poder mandar a otro estado
	 * @param gsm
	 */
	public VictoryState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
		

	@Override
	public void tick() {
		
	}

	/**
	 * Método encargado de dibujar el background y algo de texto orientativo para el jugador
	 * @param g
	 */
	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.victory, 0,0,Game.width, Game.height, null);
		
		g.setFont(font);
		
		g.setColor(Color.WHITE);
		
		g.drawString(message, 150, 300);
	}


	/**
	 * Método encargado de inicializar la variable font, que es usada para dibujar
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		font = new Font("Arial", Font.PLAIN, 25);
	}
	
	/**
	 * Método encargado de que, en caso de que se pulse la tecla enter,
	 * cambie al estado del menú
	 */
	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER)
		{
			gsm.setSate(GameStateManager.MENU_STATE);
		}
	}


	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
