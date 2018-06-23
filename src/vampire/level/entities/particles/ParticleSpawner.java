package vampire.level.entities.particles;

import vampire.Game;
import vampire.graphics.Sprite;
import vampire.level.Level;
import vampire.level.entities.Entity;

public class ParticleSpawner extends Entity {

	private int life;

	public ParticleSpawner(double x, double y, int life, int amount, Sprite sprite,
			Level level) {
		super(x, y);
		this.life = life;
		initLevel(level);

		for (int i = 0; i < amount; i++) {
			if (sprite == Sprite.normalParticle) this.level.add(new Particle(x + Game.random.nextInt(5), y
					+ Game.random.nextInt(3), this.life,
					sprite));
			else this.level.add(new DeathParticle(x + Game.random.nextInt(5), y
					+ Game.random.nextInt(3), this.life,
					sprite));
		}
	}

}
