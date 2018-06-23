package vampire.graphics.layers.front;

import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.graphics.layers.Layer;

public class FrontGroundLayer extends Layer {

	// ---------------------------------VARIABLES----------------------------------

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public FrontGroundLayer(double x, double y, Sprite[] sprites) {
		super(x, y, sprites);
		id = 'f' + 'G';
	}

	public FrontGroundLayer(double x, double y, int width) {
		super(x, y, width, 1, 'f' + 'G');

	}

	// ---------------------------------MÉTODOS----------------------------------

	public void update() {
	}

	public void render(Screen screen) {
		screen.renderTiledLayer((int) x, (int) y, this);
	}

}
