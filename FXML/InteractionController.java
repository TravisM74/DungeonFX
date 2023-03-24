package FXML;

import java.io.IOException;
import java.util.ArrayList;

import Interaction.ToHitAC0;
import World.Tile;
import World.WorldArray;
import application.WorldEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import old_code.Player;


public class InteractionController {
	private String playerName;
	private String mobName;
	private ProgressBar playerHP;
	private ProgressBar mobHP;
	private Button attackButton;
	private Button fleeButton;
	private Button drinkPotionButton;
	private Player player;
	private WorldEntity entity;
	private ToHitAC0 playerToHit;
	private ToHitAC0 mobToHitPlayer;
	private ArrayList<WorldEntity> mobsGatheredFromTile;
	private ArrayList<WorldEntity> defeatedMobs;
	private Tile tile;
	private WorldArray world;
	
	
	
	public InteractionController(Player player, WorldEntity entity, WorldArray world) {
	  this.player = player;
	  this.entity = entity;
	  this.world = world;
	}
	
	@FXML
	public void fight(ActionEvent e) {
		System.out.println("player Attacks");
		System.out.print(player.getName() + " attacks,");
		
		playerToHitMob(entity);
		if (playerToHit.tryTohit()) {
			damageToMobFromPlayer();
		} else {
			System.out.print(player.getName() +" missed ,");
		}
		if (entity.getMob().getHitPoints() > 0) {
			System.out.println("the "+ entity.getMob().getName()+" still stands and retaliates.");
		} else {
			mobsGatheredFromTile.get(mobCount).getMob().isDead();
			mobsGatheredFromTile.get(mobCount).getMob().getCClass().getImageView().setRotate(90);
			defeatedMobs.add(entity);
			mobCount++;
			JOptionPane.showMessageDialog(null,
					"you Killed "+entity.getMob().getName(), "For a total damage of "+entity.getMob().getMaxHitPoints()+" ",
					JOptionPane.INFORMATION_MESSAGE);
		}
			
		for(WorldEntity mobEntity: horde) {
			if((mobEntity.getMob().isAlive() && (player.isAlive()))) {
				toHitPlayer(mobEntity);
				System.out.print("The "+mobEntity.getMob().getName()+" attacks,");
				if (mobToHitPlayer.tryTohit()) {
					damageToPlayerFrom(mobEntity);
					
					
					JOptionPane.showMessageDialog(null,
							"Combat", " "+entity.getMob().getName()+" hits you for "+ damage +" damage.",
							JOptionPane.INFORMATION_MESSAGE);
					if(player.getHitPoints() < 0) {
						player.isDead();
						System.out.println("The "+mobEntity.getMob().getName()+" cheers your defeat...");
						player.restrictPlayerMovementOn();
						if(player.getHitPoints() >= -9) {
							player.setStatusUnconcious();
						} else {
							System.out.println("as HUDSON says 'GAME OVER man it's GAME OVER.'");
						}
						
					}
				} else {
					System.out.println("the "+entity.getMob().getName() +" misses!");
				}
			} else {
				System.out.println("a fatal blow, the "+entity.getMob().getName() + " falls and lays there bleeding.");
			}
		}
	}


	public void drinkPotion(ActionEvent e) {
		System.out.println("player drinks a potion");
	}
	public void flee(ActionEvent e) {
		System.out.println("player tries to flee");
	}
		
	

}
