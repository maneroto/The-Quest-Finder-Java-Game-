package principal;
/**
 * Clase que tiene el �nico prop�sito de inicializar el juego
 * @author David Flores
 *
 */
public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("The Quest Finder", 640, 360);
		game.start();
	}

}
