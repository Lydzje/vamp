package vampire.gameplay;

import vampire.gameplay.ai.AttackArea;
import vampire.graphics.Sprite;
import vampire.level.Level;
import vampire.level.entities.particles.ParticleSpawner;
import vampire.level.mobs.Mob;
import vampire.utils.sound.Sound;

public class Attack {
	private Mob mob;
	private AttackArea[] areas;
	private int[] aKeys;
	private int attackFramePerformed = -1;
	private Sound[] sounds;
	private int[] sKeys;
	private int soundFramePerformed = -1;

	private Level level;

	public Attack(Mob mob, AttackArea[] areas, int[] aKeys, Sound[] sounds, int[] sKeys) {
		this.mob = mob;
		this.areas = areas;
		this.aKeys = aKeys;
		this.sounds = sounds;
		this.sKeys = sKeys;

		this.level = Level.levels.get(1);

	}

	public void update() {

		for (int i = 0; i < sKeys.length && mob.getAnimSprite().getFrame() != soundFramePerformed; i++)
			if (mob.getAnimSprite().getFrame() == sKeys[i]) {
				sounds[i].play();

				double xx = (areas[0].getMob().getDir() == Mob.Direction.LEFT ? areas[0].getMob().getX()
						+ areas[0].getxL()
						: areas[0].getMob().getX() + areas[0].getxR()) + areas[0].getWidth() / 2;

				level.add(new ParticleSpawner(xx, areas[0].getY()
						+ areas[0].getHeight(), 30, 200, Sprite.normalParticle, level));
			}
		soundFramePerformed = mob.getAnimSprite().getFrame();

		for (int i = 0; i < aKeys.length && mob.getAnimSprite().getFrame() != attackFramePerformed; i++)
			if (mob.getAnimSprite().getFrame() == aKeys[i]) {
				new AttackArea(areas[i]);
			}
		attackFramePerformed = mob.getAnimSprite().getFrame();

		if (mob.getAnimSprite().getFrame() == mob.getAnimSprite().getLength() - 1) {
			areas[0].getMob().setAttacking(false);
			attackFramePerformed = -1;
			soundFramePerformed = -1;
		}

	}
}
