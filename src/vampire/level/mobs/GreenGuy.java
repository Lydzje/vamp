package vampire.level.mobs;

import vampire.gameplay.Attack;
import vampire.gameplay.ai.AttackArea;
import vampire.gameplay.ai.SimpleFighterAI;
import vampire.graphics.AnimatedSprite;
import vampire.graphics.Screen;
import vampire.graphics.SpriteSheet;
import vampire.utils.sound.Sound;

public class GreenGuy extends Mob {

	// ---------------------------------VARIABLES----------------------------------

	private AnimatedSprite left = new AnimatedSprite(64, 64, 0, 0, SpriteSheet.greenGuy_left, 4);
	private AnimatedSprite right = new AnimatedSprite(64, 64, 0, 0, SpriteSheet.greenGuy_right, 4);
	private AnimatedSprite runningL = new AnimatedSprite(64, 64, 0, 0, SpriteSheet.greenGuy_runningL, 4);
	private AnimatedSprite runningR = new AnimatedSprite(64, 64, 0, 0, SpriteSheet.greenGuy_runningR, 4);

	private AnimatedSprite attackR = new AnimatedSprite(146, 96, 29, 32, SpriteSheet.greenGuy_blood_attackR,
			6);
	private AnimatedSprite attackL = new AnimatedSprite(146, 96, 53, 32, SpriteSheet.greenGuy_blood_attackL,
			6);

	private Attack basicAttack;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public GreenGuy(double x, double y) {
		super(x, y);
		new SimpleFighterAI(this);
		speed = 1.5;
		attackRate = 0;
		maxHealth = health = 150;
		mobX = 10;
		mobWidth = 39;
		dir = Direction.RIGHT;
		animSprite = right;
		animSprite.setFPS(7);

		initAttacks();
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void initAttacks() {
		attacks = new Attack[1];

		areas = new AttackArea[1];
		aKeys = new int[1];
		areas[0] = new AttackArea(this, -50, 87, (int) y, 25, 64, 20, 0);
		aKeys[0] = 3;

		sounds = new Sound[1];
		sKeys = new int[1];
		sounds[0] = Sound.sounds[1];
		sKeys[0] = 4;

		basicAttack = new Attack(this, areas, aKeys, sounds, sKeys);

		attacks[0] = basicAttack;
	}

	public void attack() {

		attackRate = 120;
		if (dir == Direction.RIGHT) animSprite = attackR;
		else if (dir == Direction.LEFT) animSprite = attackL;
		animSprite.setPlayingAnim(true);
		attacking = true;
		attackToPerform = true;

	}

	public void update() {
		super.update();

		timer++;

		animSprite.update();

		if (attacking) {
			for (int i = 0; i < attacks.length; i++)
				attacks[i].update();
		}

		if (attackRate > 0) attackRate--;

		if (walking && !animSprite.isPlayingAnim()) {
			animSprite = dir == Direction.LEFT ? runningL : runningR;
			move(dir);
		} else if (!animSprite.isPlayingAnim()) {
			animSprite = dir == Direction.LEFT ? left : right;
		}
		if (!hasTarget) moveRandomly(left, right, runningL, runningR);
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		int oX = animSprite.getAnimSpriteXoff();
		int oY = animSprite.getAnimSpriteYoff();

		// if(animSprite.isPlayingAnim())screen.drawRectangle((int) x + (dir == Direction.LEFT ? -50 : 87),
		// (int) y, 25, 64, 0xff00ff00, true);

		if (!hurt) screen.renderMob((int) x - oX, (int) y - oY, sprite, true, hurt);
		else if (timer % 30 >= 0 && timer % 30 <= 20) screen.renderMob((int) x - oX, (int) y - oY, sprite,
				true, hurt);

	}

}
