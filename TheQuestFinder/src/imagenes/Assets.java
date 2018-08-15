package imagenes;

import java.awt.image.BufferedImage;
/**
 * Precarga todas las texturas de los objetos
 * @author David Flores
 *@version 1.0
 */
public class Assets {

	public static BufferedImage 
	background,
	victory,
	dead,
	
	grass, 
	dirt,
	sand,
	rock,
	cloud1,
	cloud2,
	tree,
	sky,
	door,
	
	coin,
	key,
	arrow,
	moneyBag,
	chocolat;
	
	public static BufferedImage[] 
			playerRight,
			playerLeft,
			playerStand,
			playerAtackRight,
			playerAtackLeft,
			
			slimeRight,
			slimeLeft,
			spiderRight,
			spiderLeft
			;
	/**
	 * Inicializa cada textura de cada objeto para que esten guardadas en memoria,
	 * cada una de las texturas hace uso ya sea, de una parte de una imágen o de una
	 * imágen completa
	 */
	public static void init()
	{
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/TileSheet.png"));
		SpriteSheet visualSheet = new SpriteSheet(ImageLoader.loadImage("/textures/VisualTiles.png"));
		SpriteSheet playerR = new SpriteSheet(ImageLoader.loadImage("/textures/jugadorCaminando.png"));
		SpriteSheet playerL = new SpriteSheet(ImageLoader.loadImage("/textures/jugadorCaminandoL.png"));
		SpriteSheet playerS = new SpriteSheet(ImageLoader.loadImage("/textures/jugadorParado.png"));
		SpriteSheet playerAR = new SpriteSheet(ImageLoader.loadImage("/textures/jugadorAtacando.png"));
		SpriteSheet playerAL = new SpriteSheet(ImageLoader.loadImage("/textures/jugadorAtacandoL.png"));
		SpriteSheet slimeR = new SpriteSheet(ImageLoader.loadImage("/textures/slime.png"));
		SpriteSheet slimeL = new SpriteSheet(ImageLoader.loadImage("/textures/slimeL.png"));
		SpriteSheet spiderR = new SpriteSheet(ImageLoader.loadImage("/textures/SpiderRight.png"));
		SpriteSheet spiderL = new SpriteSheet(ImageLoader.loadImage("/textures/SpiderLeft.png"));
		SpriteSheet itemsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/itemsSheet.png"));
		
		playerRight = new BufferedImage[14];
		for (int i = 0; i < 14; i ++)
		{
			playerRight[i] = playerR.crop(i*32, 0, 32, 32);
		}
		playerLeft = new BufferedImage[14];
		for (int i = 0; i < 14; i ++)
		{
			playerLeft[i] = playerL.crop(i*32, 0, 32, 32);
		}
		playerStand = new BufferedImage[2];
		for (int i = 0; i < 2; i ++)
		{
			playerStand[i] = playerS.crop(i*32, 0, 32, 32);
		}
		playerAtackRight = new BufferedImage[8];
		for (int i = 0; i < 8; i ++)
		{
			playerAtackRight[i] = playerAR.crop(i*32, 0, 32, 32);
		}
		playerAtackLeft = new BufferedImage[8];
		for (int i = 0; i < 8; i ++)
		{
			playerAtackLeft[i] = playerAL.crop(i*32, 0, 32, 32);
		}
		
		//Slime
		slimeRight = new BufferedImage[4];
		for (int i = 0; i < 4; i ++)
		{
			slimeRight[i] = slimeR.crop(i*32, 0, 32, 27);
		}
		slimeLeft = new BufferedImage[4];
		for (int i = 0; i < 4; i ++)
		{
			slimeLeft[i] = slimeL.crop(i*32, 0, 32, 27);
		}
		
		//Spider
		spiderRight = new BufferedImage[16];
		for (int i = 0; i < 16; i ++)
		{
			spiderRight[i] = spiderR.crop(i*32, 0, 32, 32);
		}
		spiderLeft = new BufferedImage[16];
		for (int i = 0; i < 16; i ++)
		{
			spiderLeft[i] = spiderL.crop(i*32, 0, 32, 32);
		}
		
		// Tiles
		sky = visualSheet.crop(0, 0, 32, 32);
		cloud1 = visualSheet.crop(32, 0, 32, 32);
		cloud2 = visualSheet.crop(0, 32, 32, 32);
		tree = visualSheet.crop(32, 32, 32, 32);
		
		door = ImageLoader.loadImage("/textures/entrance.png");
		
		grass = tileSheet.crop(32, 32, 32, 32);
		dirt = tileSheet.crop(0, 32, 32, 32);
		sand = tileSheet.crop(0, 0, 32, 32);
		rock = tileSheet.crop(32, 0, 32, 32);
		
		//Items
		key = itemsSheet.crop(0, 0, 28, 11);
		arrow = itemsSheet.crop(0, 11, 25, 7);
		moneyBag = itemsSheet.crop(0, 18, 24, 23);
		chocolat = itemsSheet.crop(0, 41, 28, 20);
		coin = itemsSheet.crop(0, 61, 10, 10);
		
		// Fondo para el menu
		background = ImageLoader.loadImage("/textures/BG.png");
		victory = ImageLoader.loadImage("/textures/Victory Message.png");
		dead = ImageLoader.loadImage("/textures/Dead Message.png");

	}
	
}
