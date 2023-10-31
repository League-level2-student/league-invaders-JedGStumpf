import javax.swing.JFrame;


public class LeagueInvaders {
	JFrame window;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	GamePanel panel;
	
	public LeagueInvaders() {
		window = new JFrame();
		panel = new GamePanel();
	}
	
	public static void main(String[] args) {
		LeagueInvaders instance = new LeagueInvaders();
		instance.setup();
		
	}
	
	public void setup() {
		window.add(panel);
		window.setSize(WIDTH, HEIGHT);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		window.addKeyListener(panel);
	}
	
}
