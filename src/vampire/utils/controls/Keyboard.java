package vampire.utils.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	// ---------------------------------VARIABLES----------------------------------

	private int iKeys = 6;

	private boolean[] keys = new boolean[iKeys];

	/**
	 * @left => A
	 * @right => D
	 * @jump => space
	 */
	public boolean left, right, up, down, jump, escape;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Keyboard() {
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) keys[0] = true;
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) keys[1] = true;
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) keys[2] = true;
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) keys[3] = true;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) keys[4] = true;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) keys[5] = true;
	}

	public void keyReleased(KeyEvent e) {
		// TODO: RESOLVER CUANDO SE PULSAN P.EJ "A" & "LEFT" A LA VEZ Y SE SUELTA UNO
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) keys[0] = false;
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) keys[1] = false;
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) keys[2] = false;
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) keys[3] = false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) keys[4] = false;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) keys[5] = false;
	}

	public void update() {
		left = keys[0];
		right = keys[1];
		up = keys[2];
		down = keys[3];
		jump = keys[4];
		escape = keys[5];
	}
}
