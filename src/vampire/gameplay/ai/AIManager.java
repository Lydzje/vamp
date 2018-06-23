package vampire.gameplay.ai;

import java.util.ArrayList;
import java.util.List;

import vampire.level.mobs.Mob;

public class AIManager {

	protected static List<AIManager> implementedAIs = new ArrayList<AIManager>();

	protected Mob mob;

	public AIManager() {

	}

	protected void perform() {

	}

	public void update() {
		for (AIManager implementedAI : implementedAIs)
			implementedAI.perform();
	}

}
