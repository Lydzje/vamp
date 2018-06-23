package vampire.graphics.layers.background;

import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.graphics.layers.Layer;

public class BackGroundLayer extends Layer {
	// ---------------------------------VARIABLES----------------------------------

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public BackGroundLayer(double x, double y, Sprite[] sprites) {
		super(x, y, sprites);
		id = 'b' + 'G';
	}

	public BackGroundLayer(double x, double y, int width) {
		super(x, y, width, 1, 'b' + 'G');

	}

	// ---------------------------------MÉTODOS----------------------------------

	public void update() {
	}

	public void render(Screen screen) {
		screen.renderTiledLayer((int) x, (int) y, this);
	}

}
