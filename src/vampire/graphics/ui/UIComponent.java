package vampire.graphics.ui;

import java.awt.Graphics;

import vampire.graphics.Screen;
import vampire.utils.maths.Vector2f;

public class UIComponent {
	// ---------------------------------VARIABLES----------------------------------

	protected Vector2f position;
	protected Vector2f offset;

	protected boolean active = true;
	protected boolean screenRenderMode = false;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public UIComponent(Vector2f position, Vector2f offset) {
		this.position = position;
		this.offset = offset;

		this.position.setX(position.getX()); // * Game.getCanvasWScale());
		this.position.setY(position.getY());// * Game.getCanvasHScale());

	}

	public void setOffset(Vector2f offset) {
		this.offset.setX(offset.getX());
		this.offset.setY(offset.getY());
	}

	public Vector2f getAbsolutePosition() {
		return position.getCombinationWith(offset);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean state) {
		active = state;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void update() {
	}

	public void render(Graphics g, Screen screen) {
		if (screenRenderMode) render(screen);
		else render(g);
	}

	public void render(Graphics g) {

	}

	public void render(Screen screen) {

	}

}
