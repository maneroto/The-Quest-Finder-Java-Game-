package controlDeObjetos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import estados.GameStateManager;
/**
 * 
 * @author Emmanuel Antonio
 *@version 1.0
 */

public class KeyInput implements KeyListener{
	
	GameStateManager gsm;
	
	/**
	 * @ Constructor KeyInput
	 * @param gsm que es el administrador de estados 
	 */
	public KeyInput(GameStateManager gsm)
	{
		this.gsm = gsm;
	}
	
	/**
	 * Método encargado de leer las teclas que son presionadas y,
	 * dicha tecla, es enviada al estado actual en el que el juego se encuentra
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();	
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		gsm.keyPressed(key);
	}

	/**
	 * Método encargado de leer las teclas que son soltadas y,
	 * dicha tecla, es enviada al estado actual en el que el juego se encuentra
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		gsm.keyReleased(key);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
