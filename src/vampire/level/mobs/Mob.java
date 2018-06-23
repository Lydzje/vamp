package vampire.level.mobs;

import java.util.ArrayList;
import java.util.List;

import vampire.Game;
import vampire.gameplay.Attack;
import vampire.gameplay.ai.AttackArea;
import vampire.graphics.AnimatedSprite;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.level.Level;
import vampire.utils.sound.Sound;

public class Mob {

	// ---------------------------------VARIABLES----------------------------------

	protected static List<Mob> mobs = new ArrayList<Mob>();
	protected static List<Player> players = new ArrayList<Player>();

	protected Mob target;
	protected boolean hasTarget = false;
	protected boolean inRange = false;

	protected double x, y, speed;
	/**
	 * Son la x y el width del mob en el sprite
	 */
	protected int mobX, mobWidth;
	protected int attackRate;
	protected boolean walking = false;
	protected boolean attacking = false;
	protected boolean attackToPerform = false;
	protected boolean hurt = false;
	protected boolean dead = false;

	protected Attack[] attacks;
	protected AttackArea[] areas;
	protected int[] aKeys;
	protected Sound[] sounds;
	protected int[] sKeys;

	protected int maxHealth;
	protected int health;
	protected int damageTaken;
	protected boolean damageTaked = true;

	public static enum Direction {
		LEFT, RIGHT;
	}

	protected Direction dir;

	protected Sprite sprite;
	protected AnimatedSprite animSprite;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Mob(double x, double y) {
		this.x = x;
		this.y = y;
		this.mobs = Level.mobs;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}

	public void setDamageTaked(boolean state) {
		damageTaked = state;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public int getMobX() {
		return mobX;
	}

	public int getMobWidth() {
		return mobWidth;
	}

	public Mob getTarget() {
		return target;
	}

	public boolean hasTarget() {
		return hasTarget;
	}

	public void setTarget(Mob target) {
		this.target = target;
	}

	public void setHasTarget(boolean hasTarget) {
		this.hasTarget = hasTarget;
	}

	public void setInRange(boolean inRange) {
		this.inRange = inRange;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setAttackRate(int attackRate) {
		this.attackRate = attackRate;
	}

	public void setWalking(boolean walking) {
		this.walking = walking;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public void setAnimSprite(AnimatedSprite animSprite) {
		this.animSprite = animSprite;
	}

	public void setHurtTime(int hurtTime) {
		this.hurtTime = hurtTime;
	}

	public boolean isInRange() {
		return inRange;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean state) {
		attacking = state;
	}

	public double getSpeed() {
		return speed;
	}

	public int getAttackRate() {
		return attackRate;
	}

	public boolean isWalking() {
		return walking;
	}

	public boolean isDamageTaked() {
		return damageTaked;
	}

	public Direction getDir() {
		return dir;
	}

	public AnimatedSprite getAnimSprite() {
		return animSprite;
	}

	public int getTimer() {
		return timer;
	}

	public boolean isHurt() {
		return hurt;
	}

	public boolean isDead() {
		return dead;
	}

	private int hurtTime;

	public int getHurtTime() {
		return hurtTime;
	}

	public void setHurt(boolean state) {
		hurt = state;
		hurtTime = 60;
	}

	public List<Mob> getMobs() {
		return mobs;
	}

	public List<Player> getPlayers() {
		return players;
	}

	// ---------------------------------MÉTODOS----------------------------------

	protected void move(Direction dir) {
		// HACER MÉTODO SIN PARÁMETROS
		x += dir == Direction.LEFT ? x <= 0 ? -0 : -speed : speed;
	}

	public void attack() {

	}

	protected int timer = 0;

	protected void moveRandomly(AnimatedSprite left, AnimatedSprite right, AnimatedSprite runningL,
			AnimatedSprite runningR) {
		// HACER MÉTODO SIN PARÁMETROS
		if (timer % (30 + Game.random.nextInt(60)) == 0) {
			dir = Game.random.nextInt(2) == 0 ? Direction.LEFT : Direction.RIGHT;
			walking = Game.random.nextInt(3) == 0 ? true : false;
		}

		if (walking) {
			animSprite = dir == Direction.LEFT ? runningL : runningR;

		} else {
			animSprite = dir == Direction.LEFT ? left : right;
		}
	}

	public void moveTo(Mob target) {
		// HACER MÉTODO SIN PARÁMETROS
		if (target.getX() < x) {
			dir = Direction.LEFT;
		} else dir = Direction.RIGHT;

		if (Math.abs(x - target.getX()) > 64) {
			walking = true;
			inRange = false;
		}
		/*
		 * else if (Math.abs(x - target.getX()) < 63) {
		 * dir = dir == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
		 * walking = true;
		 * inRange = false;
		 * }
		 */
		else {
			walking = false;
			inRange = true;
		}
	}

	public void update() {

			if (hurt && !damageTaked) {
			damageTaked = true;
			if (health < damageTaken) health = 0;
			else health -= damageTaken;
			damageTaken = 0;
		}

		if (hurtTime > 0) hurtTime--;
		else hurt = false;

	}

	public void render(Screen screen) {
	}

}
