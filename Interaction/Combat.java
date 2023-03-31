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
	private Mob player;
	private ToHitAC0 playerToHit;
	private ToHitAC0 mobToHitPlayer;
	private int damage;
	private Item attackWep;
	private Random rand;
	private ArrayList<String> combatText;
	private WorldArray world;

	private int round;
	private int mobCount;
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
			
	public boolean checkIfCanFight() {
		if ((this.player.isAlive()) && (this.player.getStatusState() != (StatusState.UNCONCEOUS))) {
			this.playerDefeated= false;
			return true;
		} else {
			world.moveEntity(this.player, this.entity);
			this.playerDefeated= true;
			return false;
			
		}
	}
	
	public void playerAttacks(WorldEntity entity) {
		this.round++;
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
		
	public void MobAttacks(WorldEntity entity) {
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
			
			this.combatText.add("The "+this.entity.getMob().getName()+" cheers your defeat...\n press(back) \n");
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
	
	public void endTurn() {	
		this.combatText.add("(------ End of combat trun:"+ round+" ------)"  );
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
	public void MessageDialog(String data,String title) {
		JOptionPane.showMessageDialog(null,
				title, data,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	public ArrayList<String> getCombatText() {
		return this.combatText;
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
}


