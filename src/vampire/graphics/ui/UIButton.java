package vampire.graphics.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import vampire.Game;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.utils.controls.Mouse;
import vampire.utils.listeners.ButtonActionListener;
import vampire.utils.listeners.UIButtonListener;
import vampire.utils.maths.Vector2f;

public class UIButton extends UIComponent {
	// ---------------------------------VARIABLES----------------------------------

	private Image image;
	private Sprite sprite;
	private UIButtonListener listener;
	private ButtonActionListener actionListener;

	private boolean imageMode;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public UIButton(Vector2f position, Vector2f offset, BufferedImage image,
			ButtonActionListener actionListener) {
		super(position, offset);
		this.image = image;
		this.actionListener = actionListener;
		listener = new UIButtonListener();
		imageMode = true;
	}

	public UIButton(Vector2f position, Vector2f offset, Sprite sprite, ButtonActionListener actionListener) {
		super(position, offset);
		this.sprite = sprite;
		this.image = sprite.image;
		this.actionListener = actionListener;
		listener = new UIButtonListener();
		imageMode = true;
	}

	public Image getImage() {
		return image;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public boolean isImageMode() {
		return imageMode;
	}

	public void setButtonListener(UIButtonListener listener) {
		this.listener = listener;
	}

	private boolean isMouseInside() {
		float xA = position.getX() + offset.getX();
		float yA = position.getY() + offset.getY();

		if (imageMode) {
			if (Mouse.getX() >= xA && Mouse.getX() <= xA + image.getWidth(null) && Mouse.getY() >= yA
					&& Mouse.getY() <= yA + image.getHeight(null)) return true;
			return false;
			/**
			 * if (Mouse.getUnscaledX() >= xA
			 * && Mouse.getUnscaledX() <= xA + Game.getCanvasWScale() * image.getWidth(null)
			 * && Mouse.getUnscaledY() >= yA
			 * && Mouse.getUnscaledY() <= yA + Game.getCanvasHScale() * image.getHeight(null)) return true;
			 * return false;
			 */
		}
		else {
			if (Mouse.getX() >= xA && Mouse.getX() <= xA + sprite.width && Mouse.getY() >= yA
					&& Mouse.getY() <= yA + sprite.height) {
				System.out.println("3333");
				return true;
			}
			return false;
		}
	}

	// ---------------------------------MÉTODOS----------------------------------

	private boolean leftMouseButtonDown = false, inside = false, ignorePressed = false, pressed = false;

	public void update() {
		leftMouseButtonDown = Mouse.getButton() == MouseEvent.BUTTON1;
		if (isMouseInside()) {
			if (!inside) {
				if (leftMouseButtonDown) ignorePressed = true;
				else ignorePressed = false;

				listener.entered(this);
			}
			inside = true;

			if (!pressed && !ignorePressed && leftMouseButtonDown) {
				listener.pressed(this);
				pressed = true;

			} else if (Mouse.getButton() == MouseEvent.NOBUTTON) {
				if (pressed) {
					listener.released(this);
					actionListener.perform();
					pressed = false;
				}
				ignorePressed = false;
			}
		} else {
			if (inside) {
				listener.exited(this);
				pressed = false;
			}
			inside = false;
		}
	}

	public void render(Graphics g) {
		if (image != null) {

			g.drawImage(image.getScaledInstance((int) (Game.getCanvasWScale() * image.getWidth(null)),
					(int) (Game.getCanvasHScale() * image.getHeight(null)), 1), (int) (getAbsolutePosition()
					.getX() * Game.getCanvasWScale()), (int) (getAbsolutePosition().getY() * Game
					.getCanvasHScale()), null);
		} else System.err.println("The button image is null");
	}

	public void render(Screen screen) {
		screen.renderSprite((int) getAbsolutePosition().getX(), (int) getAbsolutePosition().getY(), sprite,
				false);
	}

}
