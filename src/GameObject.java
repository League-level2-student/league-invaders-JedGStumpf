import java.awt.Rectangle;

public class GameObject {
	 int x;
	 int y;
	 int width;
	 int height;
	 int speed = 0;
	 boolean isActive = true;
	 Rectangle collisionBox;
	 

	public GameObject(int xx, int yy, int widthw, int heighth) {
		x = xx;
		y = yy;
		width = widthw;
		height = heighth;
		collisionBox = new Rectangle(x, y, width, height);
		
	}
	
	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

}
