import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens= new ArrayList<Alien>();
	Random randomAlien = new Random();
	int score = 0;
	
	public ObjectManager(Rocketship rocketa) {
		rocket = rocketa;
	}
	
	int getScore() {
		return score;
	}
	
	void addProjectile(Projectile bullets) {
		projectiles.add(bullets);
	}
	
	void addAlien() {
		aliens.add(new Alien(randomAlien.nextInt(LeagueInvaders.WIDTH),0,50,50));
//		System.out.println(aliens.size());
	}
	
	void update() {
//		
		if (rocket.isActive) {
			rocket.update();
			purgeObjects();
		}
		
		for (Alien alien : aliens) {
			alien.update();
			if (alien.y > LeagueInvaders.HEIGHT) {
				
				alien.isActive = false;
			}
		}
		
		for (Projectile projectile : projectiles) {
			if (projectile.y < 0) {
				projectile.isActive = false;
			}
		}
		checkCollision();
		
	}
	
	void draw(Graphics g) {
		rocket.draw(g);
		for (Alien alien : aliens) {
			alien.draw(g);
			alien.update();
			
		}
		
		for (Projectile projectile : projectiles) {
			projectile.draw(g);
			projectile.update();
		}
		rocket.update();
	}
	
	void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}
	}
	
	void checkCollision(){
		if (aliens.size() > 0) {
			for (int i = 0; i < aliens.size(); i++) {
				if (aliens.get(i).collisionBox.intersects(rocket.collisionBox)) {
					rocket.isActive = false;
				} 
				if (projectiles.size() > 0) {
					for (int p = 0; p < projectiles.size(); p++) {
						if (aliens.get(i).collisionBox.intersects(projectiles.get(p).collisionBox)) {
							aliens.get(i).isActive = false;
							projectiles.get(p).isActive = false;
							score++;
						}
					}
				}

			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();
		
	}
	
	

}
