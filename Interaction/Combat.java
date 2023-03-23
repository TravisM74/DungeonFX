package Interaction;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import Items.Item;
import World.Tile;
import World.WorldArray;
import application.Entities;
import application.Player;
import application.WorldEntity;
import attributes.StatusState;

public class Combat {
	private WorldEntity entity;
	private Player player;
	private ToHitAC0 playerToHit;
	private ToHitAC0 mobToHitPlayer;
	private int damage;
	private Item attackWep;
	private Random rand;
	private ArrayList<String> combatText;
	private WorldArray world;
	private boolean resolved;
	private int round;
	private int mobCount;
	
		public Combat(Player player,WorldEntity entity, WorldArray world ) {
			this.player = player;
			this.entity = entity;
			this.world = world;
			this.resolved= false;
			this.round = 0;
			this.combatText = new ArrayList<>();
			
	}
			
	public boolean checkIfCanFight() {
		if ((this.player.isAlive()) && (this.player.getStatusState() != (StatusState.UNCONCEOUS))) {
			return true;
		} else {
			world.moveEntity(this.player, this.entity);
			return false;
			
		}
	}
	
	public boolean fightRound() {
		this.combatText.clear();
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
		} else {
			this.entity.getMob().isDead();
			this.entity.getMob().getCClass().getEntityForm().getBody().setRotate(90);
			dropLoot();
			this.combatText.add("you Killed "+entity.getMob().getName()+ " for a total damage of "+entity.getMob().getMaxHitPoints()+"\n ");
			this.combatText.add("a fatal blow, the "+this.entity.getMob().getName() + " fall and lays there bleeding.\n");
			this.resolved = true;
			awardXP();
		}
		
		
		if((this.entity.getMob().isAlive() && (this.player.isAlive()))) {
			toHitPlayer(this.player,this.entity);
			
			this.combatText.add("The "+this.entity.getMob().getName()+" attacks,");
			if (mobToHitPlayer.tryTohit()) {
				damageToPlayerFrom();
				if(this.player.getHitPoints() <= 0) {
					this.player.isDead();
					this.resolved = true;
		
					this.combatText.add("The "+this.entity.getMob().getName()+" cheers your defeat...\n");
					this.player.setStatusUnconcious();
					this.player.restrictPlayerMovementOn();
					world.moveEntity(player, entity);
					this.player.getCClass().getEntityForm().getBody().setRotate(90);
				}
			} else {
			
				this.combatText.add("and the "+this.entity.getMob().getName() +" misses !\n");
			}
		}
		if((this.player.getHitPoints() < -9)) {
			this.player.setStateDead();
	
			this.combatText.add("\"as HUDSON says 'GAME OVER man it's GAME OVER.'\"\n");
			this.resolved = true;
		} else if (this.player.getHitPoints() < 0){
				this.player.isAlive();
				this.player.setStatusUnconcious();
				this.resolved = true;
		}
		this.round++;
	
		this.combatText.add("(------ End of round "+ round+" ------)"  );
		this.combatText.add(this.player.getName() +" fighting the "+this.entity.getMob().getName());
		return this.resolved;
	}

	
	public void playerToHitMob(Player player, WorldEntity entity) {
		playerToHit =new ToHitAC0(entity.getMob().getCClass().getArmourClass(),
					player.getLevel(),
					player.getCClass().getCharClass());
		}
		
	public void toHitPlayer(Player player, WorldEntity entity) {
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
	public Player getPlayer() {
		return this.player;
	}
	public WorldEntity getEntity() {
		return this.entity;
		
	}
}


