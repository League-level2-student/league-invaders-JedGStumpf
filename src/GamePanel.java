import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    
    int currentState = MENU;
    
    Font titleFont;
    Font menuInstruction;
    
    Timer frameDraw;
    Timer alienSpawn;
    
    Rocketship rocket = new Rocketship(240, 700, 50, 50);
    
    ObjectManager object;
    
    public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
    
    
    public GamePanel() {
    	titleFont = new Font("Arial", Font.PLAIN, 48);
    	menuInstruction = new Font("Arial", Font.PLAIN, 24);
    	
    	frameDraw = new Timer(1000/60, this);
    	frameDraw.start();
    	
    	object  = new ObjectManager(rocket);
    	
    	if (needImage) {
    	    loadImage ("space.png");
    	}

    }
    
    void startGame() {
        alienSpawn = new Timer(1000 , object);
        alienSpawn.start();
    }
	
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	
	 void updateMenuState() {  
		 
	 }
	 void updateGameState() {  
		 
		 repaint();
		 if (!rocket.isActive) {
			 currentState = END;
		 }
		 object.update();
	 }
	 void updateEndState()  {  
		 
	 }
	 
	 void drawMenuState(Graphics g) {  
		 g.setColor(Color.BLUE);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		 
		 g.setFont(titleFont);
		 g.setColor(Color.YELLOW);
		 g.drawString("LEAGUE INVADERS", 15, 70);
		 
		 g.setFont(menuInstruction);
		 g.drawString("Press ENTER To Play", 115, 500);
		 g.drawString("Press SPACE For Instructions", 70, 600);
		 
		 
		 
	 }
	 void drawGameState(Graphics g) {  
		 g.setColor(Color.BLACK);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
//		 rocket.draw(g);
		 if (gotImage) {
				g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
			} else {
				 g.setColor(Color.BLACK);
				 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
			}
		 object.draw(g);
//		 rocket.draw(g);
	 }
	 void drawEndState(Graphics g)  {  
		 g.setFont(titleFont);
		 g.setColor(Color.RED);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		 g.setColor(Color.BLACK);
		 g.drawString("GAME OVER", 90, 300);
		 g.setFont(menuInstruction);
		 g.drawString("You Killed " + object.getScore() + " Enemies", 120, 400);
		 g.drawString("Press ENTER to Restart", 100, 500);
	 }


	@Override
	public void actionPerformed(ActionEvent e) {
		
		repaint();
		
		
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}

	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		    	alienSpawn.stop();
		        currentState = MENU;
				rocket = new Rocketship(240, 700, 50, 50);
				object  = new ObjectManager(rocket);
		    } else {
		        currentState++;
		    }
		    if (currentState == GAME) {
		    	startGame();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    if (rocket.y < LeagueInvaders.HEIGHT && rocket.y > 0) {
		    	rocket.up();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    if (rocket.y >= 0  && rocket.y < LeagueInvaders.HEIGHT-90) {
		    	rocket.down();
		    }
		
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    if (rocket.x > 0 && rocket.x < LeagueInvaders.WIDTH) {
		    	rocket.left();
		    }
			
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if (rocket.x < LeagueInvaders.WIDTH - 60 && rocket.x >= -20) {
				rocket.right();
			}
		}
		
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				object.addProjectile(rocket.getProjectile());
			}
		}
		repaint();
		

		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		repaint();
	}
	
}
