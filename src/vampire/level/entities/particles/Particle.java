package vampire.level.entities.particles;

import vampire.Game;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.level.entities.Entity;

public class Particle extends Entity {

	protected Sprite sprite;

	protected int life;
	private int timer = 0;

	private double speedX, speedY;

	public Particle(double x, double y, int life, Sprite sprite) {
		super(x, y);
		this.life = life + Game.random.nextInt(20) - 10;
		this.sprite = sprite;

		// speedX = Game.random.nextBoolean() ? 1 : -1 * (7 + Game.random.nextInt(5));
		speedX = Game.random.nextGaussian() * 7;
		speedY = (Game.random.nextGaussian() + 1.5) * 7;
	}

	private void move() {
		speedX *= 0.75;
		if (speedY > 0.1) {
			speedY *= 0.75;
			y -= speedY * 0.5;
		}
		else {
			if (speedY > 0) speedY *= -1;
			speedY *= 1.15;
			y -= speedY * 0.5;
		}

		x += speedX * 0.5;

		// if (speedY < 0.05) y += 0.5;
	}

	public void update() {
		timer++;

		timer = timer > 1000 ? 0 : timer;
		if (timer > life) remove();
		if (y < Game.HEIGHT - 68 + Game.random.nextInt(5)) move();
		if (y > Game.HEIGHT - 68) y = Game.HEIGHT - 68;

	}

	public void render(Screen screen) {
		screen.renderSprite((int) x, (int) y, sprite, true);
	}

}
