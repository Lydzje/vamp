package vampire.graphics.layers.background;

import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.graphics.layers.Layer;

public class SkyLayer extends Layer {
	// ---------------------------------VARIABLES----------------------------------

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public SkyLayer(double x, double y, Sprite sprite) {
		super(x, y, sprite);

	}

	// ---------------------------------MÉTODOS----------------------------------

	public void update() {
	}

	public void render(Screen screen) {
		screen.renderSprite((int) x, (int) y, sprite, false);
	}

}
