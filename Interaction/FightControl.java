package Interaction;

import java.util.ArrayList;
import java.util.Random;

import application.Entities;
import application.Mob;
import application.WorldEntity;

public class FightControl {
	private WorldEntity player;
	private ArrayList<WorldEntity> mobsToBattle;
	private WorldEntity targetMob;
	
	
	
	public FightControl(WorldEntity Player, ArrayList<WorldEntity> mobsToBattle) {
		this.player = player;
		this.mobsToBattle = mobsToBattle;
		this.targetMob = mobsToBattle.get(0);
		
	}
	public void attack(WorldEntity attacker, WorldEntity defender) {
		Random rand = new Random();
		ToHitAC0 currentFight = new ToHitAC0(attacker, defender);
		if (currentFight.tryTohit()) {
			defender.getMob().takeDamage(
					rand.nextInt(attacker.getItem().getWeaponType().getDamageDice()+1)
					+attacker.getItem().getMagicDamageBonus()
					+attacker.getMob().getStats().getSMod()
					+attacker.getMob().getDamageMod());
		}
		if ((defender.getMob().getHitPoints() < 0) && defender.getEntityEnum().equals(Entities.MOB)) {
			defender.getMob().setDead();
		}
		
	}

}
