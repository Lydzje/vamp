package vampire.level;

import java.util.ArrayList;
import java.util.List;

import vampire.Game;
import vampire.graphics.AnimatedSprite;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.graphics.layers.background.BackGroundLayer;
import vampire.graphics.layers.background.CloudsLayer;
import vampire.graphics.layers.background.MoonLayer;
import vampire.graphics.layers.background.SkyLayer;
import vampire.graphics.layers.front.FrontGroundLayer;
import vampire.graphics.ui.UIManager;
import vampire.level.entities.Entity;
import vampire.level.entities.particles.Particle;
import vampire.level.entities.particles.ParticleSpawner;
import vampire.level.mobs.Mob;
import vampire.level.mobs.Player;
import vampire.utils.Camera;

public class Level {

	// ---------------------------------VARIABLES----------------------------------

	public static List<Level> levels = new ArrayList<Level>();
	public static List<Mob> mobs = new ArrayList<Mob>();
	public List<Particle> particles = new ArrayList<Particle>();
	public List<ParticleSpawner> particleSpawners = new ArrayList<ParticleSpawner>();

	// LYDZM LOADER
	private List<Object> elements = new ArrayList<Object>();

	protected UIManager ui;
	protected Player player;
	protected Camera camera;

	protected SkyLayer sky = new SkyLayer(0, 0, Sprite.sky);
	protected MoonLayer moon = new MoonLayer(Game.WIDTH - 60, 0, Sprite.moon);
	public static CloudsLayer clouds = new CloudsLayer(0, 0, 10);

	protected BackGroundLayer bg = new BackGroundLayer(0, Game.HEIGHT - 78, 10);// 13
	protected BackGroundLayer bg2 = new BackGroundLayer(960, Game.HEIGHT - 78, 10);// 672=7*96
	protected FrontGroundLayer fg = new FrontGroundLayer(0, Game.HEIGHT - 68, 10);
	protected FrontGroundLayer fg2 = new FrontGroundLayer(960, Game.HEIGHT - 68, 10);

	protected Sprite sprite;
	protected AnimatedSprite animSprite;
	protected boolean animated = false;
	protected boolean menu = false;
	protected boolean active = false;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Level(Player player, Camera camera) {
		this.player = player;
		this.camera = camera;
		levels.add(this);
	}

	public Level(Sprite sprite) {
		this.sprite = sprite;
		levels.add(this);
	}

	public Level(AnimatedSprite animSprite) {
		this.animSprite = animSprite;
		levels.add(this);
		sprite = animSprite.getSprite();
		animated = true;
	}

	public Level() {
		player = new Player(0, Game.HEIGHT - 128, Game.getKeyboard());
		camera = new Camera(0, 0, player, Game.getKeyboard());
	}

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean state) {
		this.menu = state;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean state) {
		this.active = state;
	}

	public Camera getCamera() {
		return camera;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void add(Mob mob) {
		// TODO: Incluir player en la lista mobs y ordenar dichos mobs en función de su y
		mobs.add(mob);
	}

	public void add(Entity e) {
		if (e instanceof Particle) particles.add((Particle) e);
		else if (e instanceof ParticleSpawner) particleSpawners.add((ParticleSpawner) e);
	}

	public void clear() {
		mobs.clear();
		particles.clear();
		particleSpawners.clear();
	}

	public void update() {

		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).update();
			if (mobs.get(i) instanceof Player && mobs.get(i).isDead()) mobs.remove(i);
			else if (mobs.get(i).getHealth() <= 0 && !(mobs.get(i) instanceof Player)) mobs.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
	}

	public void render(Screen screen) {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
	}

}
