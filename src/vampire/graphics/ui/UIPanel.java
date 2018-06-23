package vampire.graphics.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import vampire.graphics.Screen;
import vampire.utils.maths.Vector2f;

public class UIPanel {
	// ---------------------------------VARIABLES----------------------------------

	private Vector2f position;

	private boolean active = true;

	public boolean screenRenderMode = false;

	private List<UIComponent> components = new ArrayList<UIComponent>();

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public UIPanel(Vector2f position) {
		this.position = position;
		this.position.setX(position.getX()); // * Game.getCanvasWScale());
		this.position.setY(position.getY()); // * Game.getCanvasHScale());
	}

	public Vector2f getPosition() {
		return position;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean state) {
		active = state;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void add(UIComponent component) {
		components.add(component);
	}

	public void update() {
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).isActive()) components.get(i).update();
		}
	}

	public void render(Graphics g, Screen screen) {
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).isActive()) components.get(i).render(g, screen);
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).isActive()) components.get(i).render(g);
		}
	}

	public void render(Screen screen) {
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).isActive()) components.get(i).render(screen);
		}
	}
}
