package vampire.level.tiles;

import vampire.Game;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;

public class Cloud extends Tile {
	// ---------------------------------VARIABLES----------------------------------

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Cloud(Sprite[] sprites) {
		super(sprites);
		id = 'C';
		x = Game.random.nextInt(30) + 30;
		y = Game.random.nextInt(100) + 30;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void render(int x, int y, Screen screen) {
		screen.renderTile(this.x + x, this.y + y, this, false);
	}
}