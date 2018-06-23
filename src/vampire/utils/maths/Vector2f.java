package vampire.utils.maths;

public class Vector2f {
	// ---------------------------------VARIABLES----------------------------------

	private float x, y;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f(Vector2f vector) {
		this.x = vector.getX();
		this.y = vector.getY();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Vector2f getCombinationWith(Vector2f vector) {
		return new Vector2f(this.x + vector.x, this.y + vector.y);

	}

	// ---------------------------------MÉTODOS----------------------------------

	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public void add(Vector2f vector) {
		this.x += vector.getX();
		this.y += vector.getY();
	}

}
