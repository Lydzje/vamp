package vampire.utils;

import vampire.Game;
import vampire.graphics.layers.Layer;
import vampire.level.Level;
import vampire.level.mobs.Mob;
import vampire.utils.controls.Keyboard;

public class Camera {

	// ---------------------------------VARIABLES----------------------------------

	private double x, y;
	private Keyboard input;
	private static boolean locked = false;
	private double speed = 1.8;
	private Mob actor;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Camera(double x, double y, Mob actor, Keyboard input) {
		this.x = x;
		this.y = y;
		this.actor = actor;
		this.input = input;
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

	public void setActor(Mob actor) {
		this.actor = actor;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public static boolean isLocked() {
		return locked;
	}

	public static void setLocked(boolean state) {
		locked = state;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public boolean contains(Layer layer) {
		int lWidth = layer.getWidth() * 96;

		if (layer.getX() > x + Game.WIDTH || layer.getX() + lWidth < x) return false;
		else return true;
	}

	public boolean contains(int init, int end) {
		if (init > x + Game.WIDTH || end < x) return false;
		else return true;
	}

	private void move() {
		if (!input.left && !input.right) Level.clouds.setSpeed(0.25);
		else {
			if (input.left) {
				Level.clouds.setSpeed(-0.25);
				x -= speed;
			}
			if (input.right) {
				Level.clouds.setSpeed(0.5);
				x += speed;
			}
		}
		if (input.up) y -= speed;

		if (input.down) y += speed;
	}

	public void update() {
		if (actor.getX() > x + 198) x += 0.5;
		if (actor.getX() < x + 198 && x > 0) x -= 0.5;

		if (!locked) move();
		if (actor.getX() + 64 > x + 455 || actor.getX() < x) {
			if (actor.getX() >= x) Level.clouds.addPosition(-32, 0);
			x = actor.getX();
		}

		// if(actor.getX() < x+150 || actor.getX() > x + 400)x=actor.getX() - 150;

	}

}
