package vampire.gameplay.ai;

import vampire.Game;
import vampire.level.mobs.Mob;
import vampire.level.mobs.Player;
import vampire.level.mobs.Mob.Direction;

public class SimpleFighterAI extends AIManager {

	public SimpleFighterAI(Mob mob) {
		this.mob = mob;
		implementedAIs.add(this);
	}

	private int time = 1;

	private void simpleFighter() {
		/**
		 * Propiedades:
		 * - Busca objetivos
		 * - Se acerca a ellos
		 * - Los ataca
		 */

		/**
		 * TOFIX:
		 * - cuando el target está muy cerca
		 */

		time++;

		if (mob.getPlayers().isEmpty()) {
			mob.setHasTarget(false);
			mob.setTarget(null);
		}
		else {

			for (Player player : mob.getPlayers()) {
				if (player.getX() >= mob.getX() - 200 && player.getX() <= mob.getX() + 200) {

					mob.setHasTarget(true);
					mob.setTarget(player);
				}
				else {
					mob.setHasTarget(false);
					mob.setTarget(null);
				}
			}

			if (mob.getTarget() != null) mob.moveTo(mob.getTarget());
			if (mob.isInRange() && mob.getAttackRate() == 0) {
				if (time % 20 == 0 && Game.random.nextBoolean()) {
					mob.attack();
					time = 1;
				} else if (Math.abs(mob.getX() - mob.getTarget().getX()) < 63) {
					mob.setDir(mob.getDir() == Direction.LEFT ? Direction.RIGHT : Direction.LEFT);
					mob.setWalking(true);

				}
			}
		}
	}

	protected void perform() {
		if (!mob.isAttacking()) simpleFighter();
	}

}
