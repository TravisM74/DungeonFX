package Combat;

import java.util.ArrayList;
import java.util.Random;

import application.Entities;
import application.WorldEntity;

public class Attack {
	private WorldEntity attacker;
	private WorldEntity defender;
	private String combatText;
	
	public Attack(WorldEntity attacker, WorldEntity defender) {
		this.attacker = attacker;
		this.defender = defender;
		this.combatText = "";
		ToHitAC0 thac0 = new ToHitAC0(this.attacker, this.defender);
		if (thac0.tryToHit()) {
			damageDefender();
		} else {
			reportMiss();
		}
		checkForDeath();
		
	}

	public void checkForDeath() {
		if (this.defender.getMob().getHitPoints() > 0) {
			if(this.defender.getEntityEnum().equals(Entities.MOB)) {
				this.defender.getMob().setDead();
			}
			if (this.defender.getEntityEnum().equals(Entities.PLAYER)) {
				if (this.defender.getMob().getHitPoints() > -9) {
					this.defender.getMob().setDead();
				} else {
					this.defender.getMob().setStatusUnconcious();
				}
			}
		}
	}
	public void damageDefender() {
		int damage = damageAmount();
		this.defender.getMob().takeDamage(damage);
		this.combatText =(this.attacker.getMob().getName() + " hit " + this.defender.getMob().getName() + " for "+ damage +".");
		
	}
	public void reportMiss() {
		this.combatText =(this.attacker.getMob().getName() + " missed " + this.defender.getMob().getName() +".");
	}
	
	public int damageAmount() {
		Random rand = new Random();
		int damage = 0;
		if (this.attacker.getEntityEnum().equals(Entities.MOB)) {
			damage += this.attacker.getMob().getDamageMod();
		}
		if (attacker.getEntityEnum().equals(Entities.PLAYER)) {
			damage += this.attacker.getMob().getStats().getSMod();
		}
		damage += this.attacker.getMob().getInventory().getMainHandItem().getMagicDamageBonus();
		damage += rand.nextInt(this.attacker.getMob().getInventory().getMainHandItem().getType().getDamageDiceValue())+1;
		return damage;
	}
	
	public String getCombatText(){
		return this.combatText;
	}
}
