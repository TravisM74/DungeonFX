package Interaction;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import Items.Item;
import World.Tile;
import World.WorldArray;
import application.Entities;
import application.Mob;
import application.WorldEntity;
import attributes.StatusState;
import old_code.Player;

public class Combat {
	private WorldEntity entity;
	private WorldEntity playerEntity;
	private Mob player;
	private ToHitAC0 playerToHit;
	private ToHitAC0 mobToHitPlayer;
	private int damage;
	private Item attackWep;
	private Random rand;
	private ArrayList<String> combatText;
	private WorldArray world;

	private int round;
	private boolean mobDefeated;
	private boolean playerDefeated;
	
	
	
	public Combat(Mob player, WorldArray world ) {
		this.player = player;
		this.world = world;
		this.round = 0;
		this.combatText = new ArrayList<>();
		this.mobDefeated = false;
		this.playerDefeated = false;
	}
	
	public Combat(WorldEntity playerEnt , WorldEntity mob) {
		this.playerEntity = playerEnt;
		this.entity = mob;
		this.mobDefeated = false;
		this.playerDefeated = false;
		this.combatText = new ArrayList<>();
	}
	
	public void fightRound(Mob attacker, WorldEntity defender) {
		Random rand = new Random();
		int defenderDamage = 0;
		ToHitAC0 currentFight = new ToHitAC0(attacker.getEntity(), defender);
		if (currentFight.tryTohit()) {
			defenderDamage =
					rand.nextInt(attacker.getEntity().getItem().getWeaponType().getDamageDice()+1)
					+attacker.getEntity().getItem().getMagicDamageBonus()
					+attacker.getEntity().getMob().getStats().getSMod()
					+attacker.getEntity().getMob().getDamageMod();
			defender.getMob().takeDamage(defenderDamage);
			this.combatText.add("attacker " + attacker.getEntity().getMob().getName() +" hit "+defender.getMob().getName() + " for " + defenderDamage);
		}
		if ((defender.getMob().getHitPoints() < 0) && defender.getEntity().equals(Entities.MOB)) {
			defender.getMob().setDead();
			this.combatText.add("a fatal blow, the "+ defender.getMob().getName() + " falls to the floor dead and bleeding.\n");
			defender.getMob().getCClass().getEntityForm().getBody().setRotate(90);
			attacker.getEntity().getMob().gainExperiance(defender.getMob().getCClass().getXPValue());
			dropLoot(attacker.getEntity(),defender);
			this.mobDefeated = true ;
		}
		if ((defender.getMob().getHitPoints() < 0) && defender.getEntity().equals(Entities.PLAYER)) {
			if (defender.getMob().getHitPoints() >= -10) {
				defender.getMob().setDead();
				this.combatText.add("a fatal blow, the "+ defender.getMob().getName() + " falls to the floor DEAD and bleeding.\n");
			}
			if (defender.getMob().getHitPoints() > 0) {
				defender.getMob().setStatusUnconcious();
				this.combatText.add("a near fatal blow, the "+ defender.getMob().getName() + " falls to the unconcious and bleeding.\n");
				defender.getMob().getCClass().getEntityForm().getBody().setRotate(90);
				this.playerDefeated = true;
			}
			world.moveEntity(attacker, defender);
			//loot the player here when mob leaves area.
		}
		
	}
	
	public void playerAttacks(WorldEntity entity) {
		this.round = this.round +1;;
		this.entity = entity;
		
		this.combatText.clear();
		if((this.entity.getMob().isAlive()) && (this.player.isAlive())){
			this.combatText.add(this.player.getName() + " attacks an ");
			playerToHitMob(this.player, this.entity);
			this.combatText.add(this.entity.getMob().getName()+ " with "+this.entity.getMob().getHitPoints()+ " HP's\n");
			if (playerToHit.tryTohit()) {
				damageToMobFromPlayer();
			} else {
				this.combatText.add(this.player.getName() +" missed ,");
			}
			if (this.entity.getMob().getHitPoints() > 0) {
				this.combatText.add("the "+ this.entity.getMob().getName()+" still stands and retaliates.\n");
				this.mobDefeated= false;
			} else {
				this.entity.getMob().setDead();
				this.mobDefeated = true;
				this.entity.getMob().getCClass().getEntityForm().getBody().setRotate(90);
				dropLoot();
				this.combatText.add("you Killed "+this.entity.getMob().getName()+ " for a total damage of "+this.entity.getMob().getMaxHitPoints()+"\n ");
				this.combatText.add("a fatal blow, the "+this.entity.getMob().getName() + " fall and lays there bleeding.\n");
				
				awardXP();
			}
		}
	}
		
	public void mobAttackTurn(WorldEntity entity) {
		this.entity = entity;
		
		if((entity.getMob().isAlive() && (this.player.isAlive()))) {
			toHitPlayer(this.player,this.entity);
			
			this.combatText.add("The "+this.entity.getMob().getName()+" attacks,");
			if (mobToHitPlayer.tryTohit()) {
				damageToPlayerFrom();
			} else {
				this.combatText.add("and the "+this.entity.getMob().getName() +" misses !\n");
				this.playerDefeated = false;
			}
		}
		if(this.player.getHitPoints() <= 0) {
			this.player.setDead();
			this.playerDefeated = true;
			
			this.combatText.add("The "+this.entity.getMob().getName()+" cheers your defeat...\n  \n");
			this.player.setStatusUnconcious();
			this.player.restrictPlayerMovementOn();
			moveMob();
			this.player.getCClass().getEntityForm().getBody().setRotate(90);
		}
		if((this.player.getHitPoints() < -9)) {
			this.player.setStateDead();
			this.playerDefeated = true;
			this.combatText.add("\"as HUDSON says 'GAME OVER man it's GAME OVER.'\"\n");
		
		} else if (this.player.getHitPoints() < 0){
				this.player.isAlive();
				this.player.setStatusUnconcious();
				this.playerDefeated = true;
				
		}
		
	}
	public void moveMob() {
		world.moveEntity(this.player, this.entity);
		
	}
	
	public void endTurn(String name) {	
		this.combatText.add("(------ End of "+name +"'s combat turn:"+ this.round+" ------)"  );
		this.combatText.add(this.player.getName() +" fighting the "+this.entity.getMob().getName());
	}

	
	public void playerToHitMob(Mob player, WorldEntity entity) {
		playerToHit =new ToHitAC0(entity.getMob().getCClass().getArmourClass(),
					player.getLevel(),
					player.getCClass().getCharClass());
		}
		
	public void toHitPlayer(Mob player, WorldEntity entity) {
		mobToHitPlayer=new ToHitAC0(this.player.getInventory().armourClass(),
				this.entity.getMob().getLevel(),
				this.entity.getMob().getCClass().getCharClass());
	}
	public void damageToMobFromPlayer() {
		rand = new Random();
		this.damage = 0;
		this.damage = rand.nextInt(this.player.getInventory().getMainHandItem().getWeaponType().getDamageDice())
				+1
				+this.player.getStats().getSMod()
				+this.player.getInventory().getMainHandItem().getQuality().getModifer()
				+this.player.getInventory().getMainHandItem().getMagicDamageBonus()
				;
		if (this.damage < 0) {
			this.damage = 0;
		}
		this.entity.getMob().takeDamage(this.damage);
		this.combatText.add(this.player.getName() + " hits the " + this.entity.getMob().getName()+ " for "+this.damage+" damage with a "+this.player.getInventory().getMainHandItem()+".\n");
			this.combatText.add("Combat"+ " You hit the "+this.entity.getMob().getName()+" for "+ this.damage +" damage.\n");
	}

	public void damageToPlayerFrom() {
		System.out.println("in mob damage to player");
		attackWep  = this.entity.getMob().getInventory().getMainHandItem();
		this.damage = 0;
		rand = new Random();
		this.damage = rand.nextInt(this.entity.getMob().getInventory().getMainHandItem().getType().getDamageDiceValue()
				+1)
				+this.entity.getMob().getDamageMod()
				+this.entity.getMob().getInventory().getMainHandItem().getQuality().getModifer()
				+this.entity.getMob().getInventory().getMainHandItem().getMagicDamageBonus();
		System.out.println("damage by mob :" +this.damage);
		/*
		if (this.damage < 0) {
			this.damage = 0 ;
		}
		*/
		System.out.println("damage by mob :" +this.damage);
		this.player.takeDamage(this.damage);
		this.combatText.add(this.entity.getMob().getName() + " hits " + this.player.getName()+ " for "+this.damage+" damage with a "+this.entity.getMob().getInventory().getMainHandItem()+".\n");
	}
	
	public void awardXP() {
		this.player.gainExperiance(this.entity.getMob().getCClass().getXPValue());
		
	}
	/*
	public void MessageDialog(String data,String title) {
		JOptionPane.showMessageDialog(null,
				title, data,
				JOptionPane.INFORMATION_MESSAGE);
	}
	*/
	
	public ArrayList<String> getCombatText() {
		return this.combatText;
	}
	public void dropLoot(WorldEntity player, WorldEntity defeatedEntity) {
		System.out.println("mob copper coin:"+defeatedEntity.getMob().getInventory().getCopperCoin());
		System.out.println("mob silver coin:"+defeatedEntity.getMob().getInventory().getSilverCoin());
		System.out.println("mob Gold coin:"+defeatedEntity.getMob().getInventory().getGoldCoin());
		System.out.println("mob plat coin:"+defeatedEntity.getMob().getInventory().getPlatCoin());
		player.getMob().getInventory().addCopperCoin(defeatedEntity.getMob().getInventory().getCopperCoin());
		player.getMob().getInventory().addSilverCoin(defeatedEntity.getMob().getInventory().getSilverCoin());
		player.getMob().getInventory().addGoldCoin(defeatedEntity.getMob().getInventory().getGoldCoin());
		player.getMob().getInventory().addPlatCoin(defeatedEntity.getMob().getInventory().getPlatCoin());
		System.out.println("player copper coin:"+defeatedEntity.getMob().getInventory().getCopperCoin());
		
		WorldEntity newItemEntity = new WorldEntity(Entities.ITEM);
		newItemEntity.setItem(defeatedEntity.getMob().getInventory().getMainHandItem());
		world	.getDungeonlevel(player.getDepth())
				.getTile(player.getxLoc(), player.getyLoc())
				.createWorldEntityItem(newItemEntity);
		
		ArrayList<Item> mobsLoot = new ArrayList<>();
		mobsLoot = defeatedEntity.getMob().getInventory().getBackPackContents();
		for (Item item :mobsLoot) {
			newItemEntity = new WorldEntity(Entities.ITEM);
			newItemEntity.setItem(item);
			world	.getDungeonlevel(player.getDepth())
					.getTile(player.getxLoc(), player.getyLoc())
					.createWorldEntityItem(newItemEntity);
		}
	}
	public void dropLoot() {
		System.out.println("mob copper coin:"+this.entity.getMob().getInventory().getCopperCoin());
		System.out.println("mob silver coin:"+this.entity.getMob().getInventory().getSilverCoin());
		System.out.println("mob Gold coin:"+this.entity.getMob().getInventory().getGoldCoin());
		System.out.println("mob plat coin:"+this.entity.getMob().getInventory().getPlatCoin());
		this.player.getInventory().addCopperCoin(this.entity.getMob().getInventory().getCopperCoin());
		this.player.getInventory().addSilverCoin(this.entity.getMob().getInventory().getSilverCoin());
		this.player.getInventory().addGoldCoin(this.entity.getMob().getInventory().getGoldCoin());
		this.player.getInventory().addPlatCoin(this.entity.getMob().getInventory().getPlatCoin());
		System.out.println("player copper coin:"+this.player.getInventory().getCopperCoin());
		
		WorldEntity newItemEntity = new WorldEntity(Entities.ITEM);
		newItemEntity.setItem(entity.getMob().getInventory().getMainHandItem());
		world	.getDungeonlevel(this.player.getDepth())
				.getTile(this.player.getEntity().getxLoc(), this.player.getEntity().getyLoc())
				.createWorldEntityItem(newItemEntity);
		
		ArrayList<Item> mobsLoot = new ArrayList<>();
		mobsLoot = entity.getMob().getInventory().getBackPackContents();
		for (Item item :mobsLoot) {
			newItemEntity = new WorldEntity(Entities.ITEM);
			newItemEntity.setItem(item);
			world	.getDungeonlevel(this.player.getDepth())
					.getTile(this.player.getEntity().getxLoc(), this.player.getEntity().getyLoc())
					.createWorldEntityItem(newItemEntity);
		}
		
	}
	public Mob getPlayer() {
		return this.player;
	}
	public WorldEntity getEntity() {
		return this.entity;
		
	}
	public boolean getPlayerDefeated() {
		return this.playerDefeated;
	}
	public boolean getMobDefeated() {
		return this.mobDefeated;
	}
	public void setMobDefeatFalse() {
		this.mobDefeated = false;
	}
	public void setPlayerDefeatedFalse() {
		this.playerDefeated = false;
	}
}


