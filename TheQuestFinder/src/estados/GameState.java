package estados;

import java.awt.Graphics;
public class GameState extends State{
	
	public GameState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
	}

	@Override
	public void init() {

	}

	@Override
	public void keyPressed(int k) {
	
	}

	@Override
	public void keyReleased(int k) {
		
	}

}
