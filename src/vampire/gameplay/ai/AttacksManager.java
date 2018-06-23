package vampire.gameplay.ai;

import java.util.List;

import vampire.level.Level;
import vampire.level.mobs.Mob;

public class AttacksManager {

	private List<AttackArea> attacks;
	private List<Mob> mobs;

	public AttacksManager() {
		attacks = AttackArea.getAttacks();
		mobs = Level.mobs;
	}

	public void update() {
		// TODO: El ataque solo dura un update, aplicar timers para que se updateen durante X tiempo pero solo
		// afecte una vez por mob
		for (int i = 0; i < attacks.size(); i++) {
			for (Mob mob : mobs)
				// Posible ConcurrentModificationException si desaparece un mob mientras se lleva a cabo este
				// for
				if (attacks.get(i).contains(mob)) {
					mob.setHurt(true);
					mob.setDamageTaken(attacks.get(i).getDamage());
					mob.setDamageTaked(false);
				}
			attacks.get(i).remove();
			i--;
		}
	}

}
