package caracteristicasPrincipales;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * La clase GameObject tiene la funcion de determinar las principales caracterisitcas para un objeto dentro del juego
 * @author Emmanuel Antonio
 * @version 1.0
 *
 */
public abstract class GameObject {

	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected BufferedImage imagen;
	protected ID id;
	protected double velX;
	protected double velY;
	/**
	 * GameObject constructor
	 * @param imagen--imagen del objeto
	 */
	public GameObject(BufferedImage imagen)
	{
		this.imagen = imagen;
	}
	/**
	 * GameObject constructor 2
	 * @param x--x position
	 * @param y-- y position
	 * @param id-- object id
	 * 
	 * @see ID
	 */
	public GameObject(double x, double y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	/**
	 * 
	 * @param x  x position
	 * @param y  y position
	 * @param o  other object to compare
	 * @return a boolean value of wether the to object meet or not
	 */
	public boolean placeMeeting(double x, double y, GameObject o)
	{
		if ((new Rectangle((int)x, (int)y , width, height)).intersects(o.getBounds())) 
			return true;
		return false;
	}
	/**
	 * 
	 * @param var valor variable
	 * @param min valor minimo
	 * @param max valor maximo
	 * @return regresa el valor var
	 */
	public double clamp(double var, double min, double max)
	{
		if (var >= max) return var = max;
		else if(var <= min) return var = min;
		else return var;
	}
	/**
	 * Realiza una actualizacion del objeto
	 */
	public abstract void tick();
	/**
	 * Pinta el objeto
	 * @param g
	 */
	public abstract void render(Graphics g);
	/**
	 * Regresa el rectangulo creado a partir de los limietes del objeto
	 * @return
	 */
	public abstract Rectangle getBounds();
	/**
	 * Determina x
	 * @param x
	 */
	public void setX(double x)
	{
		this.x = x;
	}
	/**
	 * Determina y
	 * @param y
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	/**
	 * Determina las variables de tamaño del objeto
	 * @param width ancho
	 * @param height  alto
	 */
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	/**
	 * Determina la imagen del objeto
	 * @param imagen imagen del objeto
	 */
	public void setImagen(BufferedImage imagen)
	{
		this.imagen = imagen;
	}
	/**
	 * Determina el ID del objeto
	 * @param id id del objeto
	 */
	public void setID(ID id)
	{
		this.id = id;
	}
	/**
	 * Determina velociadad en x del objeto
	 * @param velX- velocidad en x
	 */
	public void setVelX(int velX)
	{
		this.velX = velX;
	}
	/**
	 * Determina velocidad en y del objeto
	 * @param velY velocidad en y
	 */
	public void setVelY(int velY)
	{
		this.velY = velY;
	}
	/**
	 * Regresa la posisicon en x
	 * @return posiscion en x
	 */
	public int getX()
	{
		return (int)x;
	}
	/**
	 * Regresa la posisicon en y
	 * @return posiscion en y
	 */
	public int getY()
	{
		return (int)y;
	}
	/**
	 * Regresa el ancho del objeto
	 * @return ancho
	 */
	public int getWidth()
	{
		return width;
	}
	/**
	 * Regresa el alto del objeto
	 * @return alto del objeto
	 */
	public int getHeight()
	{
		return height;
	}
	/**
	 * Regresa la imagen del objeto
	 * @return imagen del objeto
	 */
	public BufferedImage getImagen()
	{
		return imagen;
	}
	/**
	 * Regresa el ID del objeto
	 * @return ID del objeto
	 */
	public ID getId()
	{
		return id;
	}
	/**
	 * Regresa la velociad en x del objeto
	 * @return velocidad en x
	 */
	public int getVelX()
	{
		return (int)velX;
	}
	/**
	 * Regresa velocidad en y
	 * @return velocidad en y
	 */
	public int getVelY()
	{
		return (int)velY;
	}
}
