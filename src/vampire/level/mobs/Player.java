package vampire.level.mobs;

import java.awt.event.MouseEvent;

import vampire.Game;
import vampire.gameplay.ai.AttackArea;
import vampire.graphics.AnimatedSprite;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.graphics.SpriteSheet;
import vampire.graphics.ui.UIHealthBar;
import vampire.graphics.ui.UIManager;
import vampire.graphics.ui.UIPanel;
import vampire.level.Level;
import vampire.level.entities.particles.ParticleSpawner;
import vampire.utils.Camera;
import vampire.utils.controls.Keyboard;
import vampire.utils.controls.Mouse;
import vampire.utils.maths.Vector2f;
import vampire.utils.sound.Sound;

public class Player extends Mob {

	// ---------------------------------VARIABLES----------------------------------

	private Keyboard input;

	private UIManager ui;
	private UIPanel panel;
	private UIHealthBar healthBar;

	private int vitalEnergy = 0;

	private AnimatedSprite left = new AnimatedSprite(64, 64, 0, 0, SpriteSheet.player_left, 4);
	private AnimatedSprite right = new AnimatedSprite(64, 64, 0, 0, SpriteSheet.player_right, 4);
	private AnimatedSprite runningL = new AnimatedSprite(64, 64, 0, 0, SpriteSheet.player_runningL, 4);
	private AnimatedSprite runningR = new AnimatedSprite(64, 64, 0, 0, SpriteSheet.player_runningR, 4);

	private AnimatedSprite deathR = new AnimatedSprite(64, 64, 0, 0, SpriteSheet.player_deathR, 8);
	private AnimatedSprite deathL = new AnimatedSprite(64, 64, 0, 0, SpriteSheet.player_deathL, 8);

	private AnimatedSprite attackR = new AnimatedSprite(96, 96, 0, 32, SpriteSheet.player_blood_attackR, 10);
	private AnimatedSprite attackL = new AnimatedSprite(96, 96, 32, 32, SpriteSheet.player_blood_attackL, 10);

	/*
	 * private AnimatedSprite attackR = new AnimatedSprite(146, 96, SpriteSheet.greenGuy_blood_attackR, 6);
	 * private AnimatedSprite attackL = new AnimatedSprite(146, 96, SpriteSheet.greenGuy_blood_attackL, 6);
	 * 
	 * private AnimatedSprite left = new AnimatedSprite(64, 64, SpriteSheet.greenGuy_left, 4);
	 * private AnimatedSprite right = new AnimatedSprite(64, 64, SpriteSheet.greenGuy_right, 4);
	 * private AnimatedSprite runningL = new AnimatedSprite(64, 64, SpriteSheet.greenGuy_runningL, 4);
	 * private AnimatedSprite runningR = new AnimatedSprite(64, 64, SpriteSheet.greenGuy_runningR, 4);
	 */

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Player(double x, double y, Keyboard input) {
		super(x, y);
		this.input = input;
		ui = Game.getUIManager();
		speed = 2.5;
		attackRate = 0;
		maxHealth = health = 125;
		mobX = 11;
		mobWidth = 28;
		dir = Direction.RIGHT;
		animSprite = right;
		players.add(this);

		initUI();

	}

	public int getVitalEnergy() {
		return vitalEnergy;
	}

	public void addVitalEnergy(int amount) {
		vitalEnergy += amount;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void initUI() {
		ui = Game.getUIManager();
		panel = new UIPanel(new Vector2f(10, 10));
		panel.setActive(false);
		panel.screenRenderMode = true;
		ui.add(panel);

		healthBar = new UIHealthBar(panel.getPosition(), new Vector2f(0, 0), this, Sprite.healthBarBack,
				Sprite.healthBarFront);
		/*
		 * BufferedImage back, front;
		 * 
		 * try {
		 * back = ImageIO.read(new File("res/textures/UI/healthBarBack.png"));
		 * front = ImageIO.read(new File("res/textures/UI/healthBarFront.png"));
		 * healthBar = new UIHealthBar(panel.getPosition(), new Vector2f(0, 0), this, back,
		 * front);
		 * } catch (IOException e) {
		 * System.err.println("Health bar images couldn't load!");
		 * e.printStackTrace();
		 * }
		 */
		panel.add(healthBar);
	}

	public void setActivatedUI(boolean state) {
		panel.setActive(state);
	}

	public void move() {

		Camera.setLocked(false);
		animSprite.setFPS(7);
		if (input.left) {
			dir = Direction.LEFT;
			animSprite = runningL;
			move(dir);
		}
		if (input.right) {
			dir = Direction.RIGHT;
			animSprite = runningR;
			move(dir);
		}

		if (!input.left && !input.right) {
			if (dir == Direction.RIGHT) animSprite = right;
			else animSprite = left;
		}

	}

	public void attack() {
		Camera.setLocked(true);
		attackRate = 60;
		if (dir == Direction.RIGHT) animSprite = attackR;
		else if (dir == Direction.LEFT) animSprite = attackL;
		animSprite.setPlayingAnim(true);
		animSprite.setFPS(13);
		attacking = true;
	}

	private boolean deathSoundPlayed = false;

	public void update() {
		// TODO: ESTRUCTURAR MEJOR O REDISEÑAR

		super.update();
		timer++;

		animSprite.update();

		if (attacking && animSprite.getFrame() == 5) {
			new AttackArea((int) x + (dir == Direction.LEFT ? -30 : 52), (int) y, 40, 64, 30);
			attacking = false;
		}

		if (attackRate > 0) attackRate--;

		if (Mouse.getButton() == MouseEvent.BUTTON1 && attackRate == 0) {
			attack();

		}
		if (!animSprite.isPlayingAnim()) {
			move();
		}

		if (health <= 0) {
			if (dir == Direction.RIGHT) animSprite = deathR;
			else if (dir == Direction.LEFT) animSprite = deathL;
			animSprite.setPlayingAnim(true);
			animSprite.setFPS(7);
		}
		if (health <= 0 && !deathSoundPlayed) {
			Camera.setLocked(true);
			deathSoundPlayed = true;
			Sound.sounds[2].play();
		}

	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		int oX = animSprite.getAnimSpriteXoff();
		int oY = animSprite.getAnimSpriteYoff();

		if (!hurt) screen.renderMob((int) x - oX, (int) y - oY, sprite, true, hurt);
		else if (timer % 30 >= 0 && timer % 30 <= 20) screen.renderMob((int) x - oX, (int) y - oY, sprite,
				true, hurt);

		if (health <= 0 && animSprite.getFrame() == 7) {
			Level.levels.get(1).add(
					new ParticleSpawner(x + sprite.width / 2.0, y + sprite.height / 1.5, 60, 120,
							Sprite.deathParticle, Level.levels.get(1)));
			dead = true;
			players.remove(this);
		}

	}

}
