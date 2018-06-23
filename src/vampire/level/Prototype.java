package vampire.level;

import vampire.Game;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.level.mobs.GreenGuy;
import vampire.level.mobs.Player;
import vampire.utils.Camera;

public class Prototype extends Level {
	// ---------------------------------VARIABLES----------------------------------

	private boolean randomSpawning = false;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Prototype(Player player, Camera camera) {
		super(player, camera);
		menu = false;

		initMobs();
	}

	public Prototype() {
		super();
		menu = false;

		initMobs();
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void initMobs() {
		for (int i = 0; i < 3; i++) {
			add(new GreenGuy(200 + Game.random.nextInt(150) + 150 * i, 125 + Game.random.nextInt(4)));
		}
		add(player);
	}

	private int timer = 1;

	public void update() {
		super.update();

		timer++;
		if (mobs.size() == 1) randomSpawning = true;
		if (randomSpawning && timer % (60 + Game.random.nextInt(60)) == 0 && mobs.size() < 10) {
			add(new GreenGuy(200 + Game.random.nextInt(200) + 150 * Game.random.nextInt(5),
					125 + Game.random.nextInt(4)));
		}

		camera.update();
		moon.update();
		clouds.update();
	}

	public void render(Screen screen) {
		sky.render(screen);
		moon.render(screen);
		clouds.render(screen);

		for (int i = 0; i < 10; i++) {
			if (camera.contains(200 * i, 200 * i + Sprite.tree.width)) {
				screen.renderSprite(200 * i, Game.HEIGHT - 168, Sprite.tree, true);
			}
		}

		if (camera.contains(bg)) bg.render(screen);
		if (camera.contains(bg2)) bg2.render(screen);

		super.render(screen);

		if (camera.contains(fg)) fg.render(screen);
		if (camera.contains(fg2)) fg2.render(screen);
	}

}
