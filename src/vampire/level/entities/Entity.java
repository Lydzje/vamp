package vampire.level.entities;

import vampire.graphics.Screen;
import vampire.level.Level;

public class Entity {

	protected double x, y;
	protected Level level;
	protected boolean removed = false;

	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void remove() {
		removed = true;
	}

	protected void initLevel(Level level) {
		this.level = level;
	}

	public void update() {
	}

	public void render(Screen screen) {
	}

}
