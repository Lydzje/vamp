package vampire.graphics.layers.background;

import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.graphics.layers.Layer;

public class CloudsLayer extends Layer {
	// ---------------------------------VARIABLES----------------------------------

	private double speed = 0.25;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public CloudsLayer(double x, double y, Sprite[] sprites) {
		super(x, y, sprites);
		id = 'C';
	}

	public CloudsLayer(double x, double y, int width) {
		super(x, y, width, 1, 'C');

	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	// ---------------------------------MÉTODOS----------------------------------

	private int timer = 0;

	public void update() {
		timer++;
		if (timer % 12 == 0) x -= speed;

	}

	public void render(Screen screen) {
		screen.renderTiledLayer((int) x, (int) y, this);
	}

}
