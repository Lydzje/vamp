package vampire.utils.maths;

public class MathsUtils {

	private MathsUtils() {
	}

	public static int clamp(int value, int min, int max) {
		if (value < min) return min;
		else if (value > max) return max;
		else return value;
	}

}
