package vampire.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import vampire.Game;
import vampire.graphics.AnimatedSprite;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.graphics.ui.UIButton;
import vampire.graphics.ui.UIPanel;
import vampire.utils.ImageUtils;
import vampire.utils.listeners.ButtonActionListener;
import vampire.utils.listeners.UIButtonListener;
import vampire.utils.maths.Vector2f;

public class StartMenu extends Level {

	private UIPanel panel;
	private UIButton newGame;
	private UIButton quit;

	private BufferedImage newGameButton, quitButton;

	public StartMenu(AnimatedSprite animSprite) {
		super(animSprite);
		menu = true;
		active = true;
		animSprite.setFPS(6);
		ui = Game.getUIManager();
		panel = new UIPanel(new Vector2f(9, 156));
		ui.add(panel);

		try {
			newGameButton = ImageIO.read(StartMenu.class.getResource("/textures/menu/newGameNew.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		newGame = new UIButton(new Vector2f(0, 0), panel.getPosition(), newGameButton,
				new ButtonActionListener() {
					public void perform() {
						System.out.println("button pressed!");
						active = false;
						panel.setActive(false);
						Game.setStartMenu(false);
						levels.get(1).setActive(true);
					}
				});

		newGame.setButtonListener(new UIButtonListener() {

			public void entered(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(newGameButton, 50));
			}

			public void exited(UIButton button) {
				button.setImage(newGameButton);
			}

			public void pressed(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(newGameButton, -50));
			}

			public void released(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(newGameButton, 50));
			}
		});

		panel.add(newGame);

		try {
			quitButton = ImageIO.read(StartMenu.class.getResource("/textures/menu/quitNew.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		quit = new UIButton(new Vector2f(0, 36), panel.getPosition(), quitButton, new ButtonActionListener() {
			public void perform() {
				active = false;
				panel.setActive(false);
				System.exit(0);
			}
		});

		quit.setButtonListener(new UIButtonListener() {

			public void entered(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(quitButton, 50));
			}

			public void exited(UIButton button) {
				button.setImage(quitButton);
			}

			public void pressed(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(quitButton, -50));
			}

			public void released(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(quitButton, 50));
			}
		});

		panel.add(quit);
	}

	public StartMenu(Sprite sprite) {
		super(sprite);
		menu = true;
		active = true;
		ui = Game.getUIManager();
		panel = new UIPanel(new Vector2f(20, 170));
		ui.add(panel);

		try {
			newGameButton = ImageIO.read(StartMenu.class.getResource("/textures/menu/newGame.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		newGame = new UIButton(new Vector2f(0, 0), panel.getPosition(), newGameButton,
				new ButtonActionListener() {
					public void perform() {
						System.out.println("button pressed!");
						active = false;
						panel.setActive(false);
						Game.setStartMenu(false);
						levels.get(1).setActive(true);
					}
				});

		newGame.setButtonListener(new UIButtonListener() {

			public void entered(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(newGameButton, 50));
			}

			public void exited(UIButton button) {
				button.setImage(newGameButton);
			}

			public void pressed(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(newGameButton, -50));
			}

			public void released(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(newGameButton, 50));
			}
		});

		panel.add(newGame);

		try {
			quitButton = ImageIO.read(StartMenu.class.getResource("/textures/menu/quit.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		quit = new UIButton(new Vector2f(0, 15), panel.getPosition(), quitButton, new ButtonActionListener() {
			public void perform() {
				active = false;
				panel.setActive(false);
				System.exit(0);
			}
		});

		quit.setButtonListener(new UIButtonListener() {

			public void entered(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(quitButton, 50));
			}

			public void exited(UIButton button) {
				button.setImage(quitButton);
			}

			public void pressed(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(quitButton, -50));
			}

			public void released(UIButton button) {
				button.setImage(ImageUtils.changeBrightness(quitButton, 50));
			}
		});

		panel.add(quit);
	}

	public void setActive(boolean state) {
		this.active = state;
		panel.setActive(state);
	}

	public void update() {
		if (animated) {
			sprite = animSprite.getSprite();
			animSprite.update();
		}
	}

	public void render(Screen screen) {
		screen.renderSprite(0, 0, sprite, false);
	}
}
