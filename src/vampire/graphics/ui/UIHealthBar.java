package vampire.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import vampire.Game;
import vampire.graphics.Screen;
import vampire.graphics.Sprite;
import vampire.level.mobs.Mob;
import vampire.utils.maths.Vector2f;

public class UIHealthBar extends UIComponent {

	// ---------------------------------VARIABLES----------------------------------

	private Mob mob;
	private float health = 1;
	private float damage = 1;

	private BufferedImage backB;
	private BufferedImage frontB;
	private Sprite backS;
	private Sprite frontS;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public UIHealthBar(Vector2f position, Vector2f offset, Mob mob, BufferedImage back, BufferedImage front) {
		super(position, offset);
		this.mob = mob;
		this.backB = back;
		this.frontB = front;
	}

	public UIHealthBar(Vector2f position, Vector2f offset, Mob mob, Sprite back, Sprite front) {
		super(position, offset);
		this.mob = mob;
		this.backS = back;
		this.frontS = front;
		screenRenderMode = true;
	}

	// ---------------------------------MÉTODOS----------------------------------

	private float damageTaken; // Porcentaje respecto a la vida máxima de daño recibido
	private float damageTaked; // el damageTaken del update anterior
	private boolean wait = false;
	private boolean done = false;
	private float dmgBarSpeed;

	public void update() {
		if (mob.getDamageTaken() > 0 && mob.getHealth() > 0) {
			damageTaked = damageTaken;
			damageTaken += mob.getDamageTaken() / (float) mob.getMaxHealth();
			dmgBarSpeed = 0.0001f;
			wait = true;
		}

		if (damageTaken > health && mob.getHealth() <= 0 && !done) {
			done = true;
			damageTaken = damageTaked + health;
		}

		health = (float) mob.getHealth() / (float) mob.getMaxHealth();
		if (mob.isDamageTaked()) {
			if (damageTaken > 1.0f) damageTaken = 1.0f;
			damage = (float) mob.getHealth() / (float) mob.getMaxHealth() + damageTaken;

		}
		if (damageTaken > 0 && !wait) damageTaken -= dmgBarSpeed;
		dmgBarSpeed += 0.0001f;
		wait = false;
	}

	public void render(Graphics g) {
		if (backB != null) {

			g.drawImage(backB.getScaledInstance((int) (Game.getCanvasWScale() * backB.getWidth(null)),
					(int) (Game.getCanvasHScale() * backB.getHeight(null)), 1), (int) (getAbsolutePosition()
					.getX() * Game.getCanvasWScale()), (int) (getAbsolutePosition().getY() * Game
					.getCanvasHScale()), null);
		} else System.err.println("The button image is null");

		int x = (int) getAbsolutePosition().getX();
		int y = (int) getAbsolutePosition().getY();

		g.setColor(new Color(0xFF600009));
		g.fillRect((int) ((x + 35) * Game.getCanvasWScale()), (int) ((y + 31) * Game.getCanvasHScale()),
				(int) (149 * health * Game.getCanvasWScale()), (int) (7 * Game.getCanvasHScale()));

		if (frontB != null) {

			g.drawImage(frontB.getScaledInstance((int) (Game.getCanvasWScale() * frontB.getWidth(null)),
					(int) (Game.getCanvasHScale() * frontB.getHeight(null)), 1), (int) (getAbsolutePosition()
					.getX() * Game.getCanvasWScale()), (int) (getAbsolutePosition().getY() * Game
					.getCanvasHScale()), null);
		} else System.err.println("The button image is null");
	}

	public void render(Screen screen) {
		int x = (int) getAbsolutePosition().getX();
		int y = (int) getAbsolutePosition().getY();
		screen.renderSprite((int) x, y, backS, false);
		screen.drawRectangle(x + 35, y + 31, (int) ((149 * damage) > 149 ? 149 : 149 * damage), 7,
				0xFFCFA459, false);
		screen.drawRectangle(x + 35, y + 31, (int) (149 * health), 7, 0xFF72000B, false);// 600009
		screen.renderSprite(x, y, frontS, false);
	}

}
