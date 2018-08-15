package estados;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Se encarga de dar paso al estado que es requerido
 * @author Emmanuel Antonio
 * @version 1.0
 */
public class GameStateManager {
	
	private ArrayList <State> gameStates;
	private int currentState;
	
	/**
	 * Variablese estáticas que ayudan a identificar el número correspondiente a cada estado
	 */
	private static int numStates = 5;
	public static final int MENU_STATE = 0;
	public static final int LEVEL1_STATE = 1;
	public static final int LEVEL2_STATE = 2;
	public static final int VICTORY_STATE = numStates - 2;
	public static final int DEAD_STATE = numStates - 1;
	
	/**
	 * Constructor del GameStateManager, que inicializa los estados existentes, en el órden
	 * que ha sido proporcionado en las variables estáticas
	 */
	public GameStateManager()
	{
		gameStates = new ArrayList <State> ();
		
		currentState = MENU_STATE;
		gameStates.add(new MenuState(this));	
		gameStates.add(new Level1State(this));
		gameStates.add(new Level2State(this));
		gameStates.add(new VictoryState(this));
		gameStates.add(new DeadState(this));
	}
	
	/**
	 * Método que hace el update del estado actual
	 */
	public void tick()
	{
		gameStates.get(currentState).tick();
	}
	
	/**
	 * Método que ayuda a realizar el renderizado del estado actual
	 * @param g
	 */
	public void render(Graphics g)
	{
		gameStates.get(currentState).render(g);
	}
	
	/**
	 * Método que recibe la tecla presionada y la manda al estado actual
	 * @param k
	 */
	public void keyPressed(int k)
	{
		gameStates.get(currentState).keyPressed(k);
	}
	
	/**
	 * Método que recibe la tecla que ha sido soltada y la manda al estado actual
	 * @param k
	 */
	public void keyReleased(int k)
	{
		gameStates.get(currentState).keyReleased(k);
	}
	
	public void setSate(int state)
	{
		currentState = state;
		if (currentState > numStates) currentState = MENU_STATE;
		gameStates.get(currentState).init();
	}
	
	public int getState()
	{
		return currentState;
	}
}
