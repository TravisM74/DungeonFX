package Combat;

import java.util.ArrayList;

import World.WorldArray;
import application.WorldEntity;

public class CombatRound {
	private WorldEntity player;
	private WorldArray world;
	private Opponents opponents;
	private ArrayList<WorldEntity> mobs;
	private WorldEntity currentTarget;
	private ArrayList<String> combatText;
	private boolean targetMobSet;
	
	
	public CombatRound(WorldEntity player,WorldArray world) {
		this.player = player;
		this.world = world;
		this.targetMobSet = false;
		this.combatText = new ArrayList<>();
		this.opponents = new Opponents(this.world, this.player);
		this.mobs = opponents.getMobs();
		getCurrentPlayerTarget();
		playerAttack();
		opponentsAttack();
		
	}
	
	public void playerAttack() {
		if (this.targetMobSet) {
			Attack thisRound = new Attack(this.player, this.currentTarget);
			this.combatText.add(thisRound.getCombatText());
		} else {
			this.combatText.add("no opponent found..");
		}
	}
	
	public void opponentsAttack() {
		System.out.println("Opponents size:" +this.mobs.size());
		for (WorldEntity opponent:this.mobs) {
			if(opponent.getMob().isAlive()) {
				Attack thisAttack = new Attack(opponent, this.player);
				combatText.add(thisAttack.getCombatText());
			}
		}
	}
	
	
	public void getCurrentPlayerTarget() {
		for (int i = 0 ; i < this.mobs.size(); i++) {
			if ((this.mobs.get(i).getMob().isAlive()) && (!this.targetMobSet)) {
				this.currentTarget = this.mobs.get(i);
				this.targetMobSet= true;
				
			}
		}
		
	}
	
	public ArrayList<String> getCombatText(){
		return this.combatText;
	}

}
