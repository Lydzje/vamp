package vampire.utils.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import vampire.Game;

public class Mouse implements MouseListener, MouseMotionListener {

	// ---------------------------------VARIABLES----------------------------------

	private static int mouseX = -1, mouseY = -1, mouseB = -1;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public Mouse() {

	}

	public static int getX() {
		return (int) (mouseX / Game.getCanvasWScale());
	}

	public static int getY() {
		return (int) (mouseY / Game.getCanvasHScale());
	}

	public static int getUnscaledX() {
		return mouseX;
	}

	public static int getUnscaledY() {
		return mouseY;
	}

	public static int getButton() {
		return mouseB;
	}

	// ---------------------------------MÉTODOS----------------------------------

	// MOUSE LISTENER

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		mouseB = MouseEvent.NOBUTTON;
	}

	// MOUSE MOTION LISTENER

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

}
