package vampire.graphics;

public class AnimatedSprite extends Sprite {

	// ---------------------------------VARIABLES----------------------------------

	private Sprite sprite;
	private int frame = 0;
	private int length = -1;
	// private int rate = 9;
	private int fps = 6;
	private int time = 0;
	private boolean playingAnim = false;

	private int animSpriteXoff, animSpriteYoff;

	// ANIMATED MENU
	public static AnimatedSprite animatedMenu = new AnimatedSprite(455, 256, 0, 0, SpriteSheet.animatedMenu,
			4);

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public AnimatedSprite(int width, int height, int animSpriteXoff, int animSpriteYoff, SpriteSheet sheet,
			int length) {
		super(0, 0, width, height, sheet);
		this.animSpriteXoff = animSpriteXoff;
		this.animSpriteYoff = animSpriteYoff;
		this.length = length;
		sprite = sheet.sprites[0];
		if (length > sheet.sprites.length) System.err.println("Error! Length of animation is too long!");
	}

	public Sprite getSprite() {
		return sprite;
	}

	// public void setFrameRate(int rate) {
	// this.rate = rate;
	// }

	public void setFPS(int fps) {
		this.fps = fps;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int index) {
		if (index > sheet.sprites.length - 1) {
			System.err.println("ERROR, index out of bounds in" + this);
			return;
		}
		sprite = sheet.sprites[index];
	}

	public int getLength() {
		return length;
	}

	public boolean isPlayingAnim() {
		return playingAnim;
	}

	public void setPlayingAnim(boolean playingAnim) {
		this.playingAnim = playingAnim;
	}

	public int getAnimSpriteXoff() {
		return animSpriteXoff;

	}

	public int getAnimSpriteYoff() {
		return animSpriteYoff;

	}

	// ---------------------------------MÉTODOS----------------------------------

	public void update() {
		time++;

		if (playingAnim) {
			playAnimation();
		}
		else {
			if (time % (60 / fps) == 0) {
				if (frame >= length - 1) frame = 0;
				else frame++;
				sprite = sheet.sprites[frame];
			}
		}
	}

	public void playAnimation() {
		if (time % (60 / fps) == 0) {
			if (frame >= length - 1) {
				frame = 0;
				playingAnim = false;
			} else frame++;
			sprite = sheet.sprites[frame];
		}
	}

}
