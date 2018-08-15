package estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import estados.GameStateManager;
import imagenes.Assets;
import otrosRecursos.Hud;
import principal.Game;

/**
 * Clase encargada de mostrar el estado de menú, por lo que éste es el estado en el 
 * que los jugadores inician
 * @author Emmanuel Antonio
 *
 */
public class MenuState extends State{
	
	private int currentChoice;
	private String[] options = {
		"Controles",
		"Jugar",
		"Salir"
	};
	private Font font;
	
	/**
	 * Constructor que recive al administrador de estados para poder cambiar de estado
	 * @param gsm
	 */
	public MenuState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
		

	@Override
	public void tick() {
		
	}

	/**
	 * Método que se encarga de pintar el background y las opciones del menú
	 * @param g
	 */
	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.background, 0,0,Game.width, Game.height, null);
		
		g.setFont(font);
		
		for (int i = 0; i < options.length; i++)
		{
			if (i == currentChoice)
			{
				g.setColor(Color.BLACK);
			}
			else
			{
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 60 + i * 220, 340);
		}		
	}

	
	/**
	 * Método encargado de inicializar al font que se usa posteriormente y de resetear 
	 * los valores del HUD
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		Hud.reset();
		font = new Font("Arial", Font.PLAIN, 25);
	}

	/**
	 * Método encargado de, según la opción seleccionada, mandar al primer nivel, salir
	 * o ver los controles
	 */
	public void select()
	{
		if (currentChoice == 0)
		{
			// Controles
		}
		if (currentChoice == 1)
		{
			// Jugar
			gsm.setSate(GameStateManager.LEVEL1_STATE);
		}
		if (currentChoice == 2)
		{
			System.exit(0);
		}
	}
	
	/**
	 * Método encargado de verificar qe las teclas presionadas sean las que ayudan a navegar
	 * por el menú
	 * @param k
	 */
	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER)
		{
			select();
		}
		
		if (k == KeyEvent.VK_UP || k == KeyEvent.VK_A)
		{
			currentChoice --;
			if (currentChoice == -1)
			{
				currentChoice = options.length - 1;
			}
		}
		
		if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_D)
		{
			currentChoice ++;
			if(currentChoice == options.length)
			{
				currentChoice = 0;
			}
		}
	}


	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
