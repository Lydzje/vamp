package vampire.graphics.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import vampire.graphics.Screen;

public class UIManager {

	// ---------------------------------VARIABLES----------------------------------

	private List<UIPanel> panels = new ArrayList<UIPanel>();

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	// ---------------------------------MÉTODOS----------------------------------

	public void add(UIPanel panel) {
		panels.add(panel);

	}

	public void update() {
		for (int i = 0; i < panels.size(); i++) {
			if (panels.get(i).isActive()) panels.get(i).update();
		}
	}

	public void render(Graphics g, Screen screen) {
		for (int i = 0; i < panels.size(); i++) {
			if (panels.get(i).isActive()) panels.get(i).render(g, screen);
		}
	}

	public void render(Graphics g) {
		for (UIPanel panel : panels) {
			if (!panel.screenRenderMode && panel.isActive()) panel.render(g);
		}
	}

	public void render(Screen screen) {
		for (UIPanel panel : panels) {
			if (panel.screenRenderMode && panel.isActive()) panel.render(screen);
		}
	}

}
