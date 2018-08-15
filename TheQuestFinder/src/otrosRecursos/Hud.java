package otrosRecursos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import principal.Game;
/**
 * Es la intefaz grafica de los valores del jugador como la vida, las flechas, el puntaje y la llaves
 * @author Emmanuel Antonio
 *@version 1.0
 */
public class Hud {
	private static int score;
	private static int vida;
	private static int arrows;
	private static int keys;
	private Font font;
	private int greenValue;
	/**
	 * Construcor del Hud
	 */
	public Hud()
	{
		this.font = new Font("Arial", Font.BOLD, 15);
		greenValue = 255;
	}
	/**
	 * Actualiza los valores del Hud
	 */
	public void tick() 
	{
		Hud.arrows = ArrowsCounter.arrowsNumber;
		Hud.keys = KeysCounter.keysNumber;
		Hud.score = ScoreCounter.score;
		Hud.vida = HealthKeeper.vida;
		vida = (int) Game.clamp(vida, 0, 100);
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		greenValue = vida * 2;
	}
	/**
	 * Dibuja el Hud
	 * @param g Graphics donde se dibuja
	 */
	public void render(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, Game.width, 30);
		g.setFont(font);
		g.setColor(new Color(110, greenValue, 0));
		g.fillRect(10, 5, vida * 2, 20);
		g.setColor(Color.WHITE);
		g.drawRect(10, 5, 200, 20);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, Game.width - 290, 20);
		g.drawString("Arrows: " + arrows , Game.width - 200, 20);
		g.drawString("Keys: " + keys, Game.width - 100, 20);
	}
	
	/**
	 * Hace un reset de los valores que son usados por el HUD
	 */
	public static void reset()
	{
		HealthKeeper.vida = 100;
		ArrowsCounter.arrowsNumber = 0;
		KeysCounter.keysNumber = 0;
		ScoreCounter.score = 0;
	}
	
	/**
	 * Regresa el Score
	 * @return Score
	 */
	public static int getScore() {
		return score;
	}
	/**
	 * Determina el Score
	 * @param score Score
	 */
	public static void setScore(int score) {
		ScoreCounter.score = score;
	}
	/**
	 * Regresa la vida del jugador
	 * @return vida del jugador
	 */
	public static int getVida() {
		return vida;
	}
	/**
	 * Determina la vida del jugador
	 * @param vida vida del jugador
	 */
	public static void setVida(int vida) {
		HealthKeeper.vida = vida;
	}
	/**
	 * Regresa la cantidad de flechas
	 * @return cantidad de flechas
	 */
	public static int getArrows() {
		return arrows;
	}
	/**
	 * Determina la cantidad de flechas que el jugador posee
	 * @param arrows cantidad de flechas
	 */
	public static void setArrows(int arrows) {
		ArrowsCounter.arrowsNumber = arrows;
	}
	/**
	 * Regresa la cantidad de llaves que el jugador posee
	 * @return cantida de llaves
	 */
	public static int getKeys() {
		return keys;
	}
	/**
	 * Determina la cantida de llaves que el jugador posee
	 * @param keys cantida de llaves
	 */
	public static void setKeys(int keys) {
		KeysCounter.keysNumber = keys;
	}
}
