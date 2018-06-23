package vampire.graphics.layers.background;

import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.graphics.layers.Layer;

public class MoonLayer extends Layer {

	// ---------------------------------VARIABLES----------------------------------

	private double radius = 1;
	private double speed = 100;
	private double angle = Math.PI;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public MoonLayer(double x, double y, Sprite sprite) {
		super(x, y, sprite);
	}

	// ---------------------------------MÉTODOS----------------------------------

	int timer = 1;

	public void update() {
		timer++;
		if (timer % 60 == 0) {
			x -= radius * Math.cos(angle / speed);
			y += radius * Math.sin(angle / speed);
			angle += 0.1;
		}
	}

	public void render(Screen screen) {
		screen.renderSprite((int) x, (int) y, sprite, false);
	}
}
