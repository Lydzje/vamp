package vampire;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import vampire.gameplay.ai.AIManager;
import vampire.gameplay.ai.AttacksManager;
import vampire.graphics.AnimatedSprite;
import vampire.graphics.Screen;
import vampire.graphics.ui.UIManager;
import vampire.level.Level;
import vampire.level.Prototype;
import vampire.level.StartMenu;
import vampire.level.mobs.Player;
import vampire.utils.Camera;
import vampire.utils.controls.Keyboard;
import vampire.utils.controls.Mouse;
import vampire.utils.sound.Sound;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	// ---------------------------------VARIABLES----------------------------------

	public static final int HEIGHT = 256;
	public static final int WIDTH = HEIGHT * 16 / 9;
	private static final int SCALE = 3;

	private static int canvasHeight = HEIGHT * SCALE;
	private static int canvasWidth = WIDTH * SCALE;

	public static Random random = new Random();;

	public JFrame frame;
	private Thread thread;
	private Screen screen;

	private static Keyboard key;

	private Mouse mouse;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private boolean running = false;

	private static UIManager uiManager;

	private Level level;

	private static StartMenu startMenu;

	private static List<Level> levels = new ArrayList<Level>();

	private static AIManager aiManager;

	private static AttacksManager attacksManager;

	private Player player;

	private Camera camera;

	// STATES

	private static boolean isStartMenu = true;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Game() {

		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame();
		screen = new Screen(WIDTH, HEIGHT, pixels);

		key = new Keyboard();
		addKeyListener(key);

		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		Sound.sounds[0] = new Sound("/sound/titleScreen.wav");
		Sound.sounds[1] = new Sound("/sound/ggHit.wav");
		Sound.sounds[2] = new Sound("/sound/death.wav");

		uiManager = new UIManager();

		startMenu = new StartMenu(AnimatedSprite.animatedMenu);

		aiManager = new AIManager();

		attacksManager = new AttacksManager();

		player = new Player(0, HEIGHT - 128, key);
		camera = new Camera(0, 0, player, key);
		level = new Prototype(player, camera);

		levels = Level.levels;

	}

	public static int getWindowHeight() {
		return HEIGHT * SCALE;
	}

	public static int getWindowWidth() {
		return WIDTH * SCALE;
	}

	public static int getCanvasHeight() {
		return canvasHeight;
	}

	public static int getCanvasWidth() {
		return canvasWidth;
	}

	public static float getCanvasHScale() {
		return canvasHeight / HEIGHT != 0 ? canvasHeight / (float) HEIGHT : 1;
	}

	public static float getCanvasWScale() {
		return canvasWidth / WIDTH != 0 ? canvasWidth / (float) WIDTH : 1;
	}

	public static int getScale() {
		return SCALE;
	}

	public static void setStartMenu(boolean state) {
		isStartMenu = state;
	}

	public static UIManager getUIManager() {
		return uiManager;
	}

	public Level getLevel() {
		return level;
	}

	public static Keyboard getKeyboard() {
		return key;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;
		int iUpdates = 0;
		int iFrames = 0;
		double dDelta = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			dDelta += (now - lastTime) / ns;
			lastTime = now;
			if (dDelta > 1) {
				update();
				iUpdates++;
				dDelta--;
			}

			render();
			iFrames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("vampire   |   " + iUpdates + " ups    " + iFrames + " fps");
				iUpdates = 0;
				iFrames = 0;
			}

		}
		stop();
	}

	private void update() {
		canvasWidth = getWidth();
		canvasHeight = getHeight();

		key.update();

		if (key.escape) {// || player.getHealth() <= 0) {
			startMenu.setActive(true);
			// if (player.getHealth() <= 0) System.out.println("===GAME_OVER===");
		}

		if (!startMenu.isActive()) {
			Sound.sounds[0].stop();
			done = false;
			level.setActive(true);
			player.setActivatedUI(true);
			aiManager.update();
			attacksManager.update();
		}
		else {
			level.setActive(false);
			player.setActivatedUI(false);
		}

		uiManager.update();
		for (Level level : levels)
			if (level.isActive()) level.update();
		if (!done && startMenu.isActive()) {
			Sound.sounds[0].loop();
			done = true;
		}

	}

	public boolean done = false;

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clean();
		screen.setOffset((int) camera.getX(), (int) camera.getY());

		Graphics g = bs.getDrawGraphics();

		// level.render(screen);
		// if (isStartMenu) {
		// startMenu.render(screen);
		// } else if (!isStartMenu) level.render(screen);

		for (Level level : levels)
			if (level.isActive()) level.render(screen);

		uiManager.render(screen);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		uiManager.render(g);

		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {

		Game game = new Game();
		game.frame.setResizable(true);
		game.frame.setTitle("Vampire");
		try {
			game.frame.setIconImage(ImageIO.read(Game.class.getResource("/textures/icons/icon.png")));
		} catch (IOException e) {
			System.err.println("Frame icon loading failed");
			e.printStackTrace();
		}
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();

	}
}
// ---------------------------------VARIABLES----------------------------------
// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------
// ---------------------------------MÉTODOS----------------------------------
