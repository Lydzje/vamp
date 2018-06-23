package vampire.level.entities.particles;

import vampire.Game;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;

public class DeathParticle extends Particle {

	private int timer = 0;

	private double speedX, speedY;

	public DeathParticle(double x, double y, int life, Sprite sprite) {
		super(x, y, life, sprite);

		// speedX = Game.random.nextBoolean() ? 1 : -1 * (7 + Game.random.nextInt(5));
		speedX = Game.random.nextGaussian() * 4;
		speedY = (Game.random.nextGaussian()) * 5;
	}

	private void move() {
		speedX *= 0.95;

		speedY *= 0.95;

		y -= speedY * 0.25;
		x += speedX * 0.25;

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