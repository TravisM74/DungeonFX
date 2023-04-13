package Combat;

import java.util.ArrayList;


import World.WorldArray;
import application.WorldEntity;

public class Opponents {
	
	private ArrayList<WorldEntity> mobs;
	private WorldEntity player;
	private WorldArray world;
	

	public Opponents(WorldArray world, WorldEntity player) {
		this.player = player;
		this.world = world;
		this.mobs =this.world.getDungeonlevel(this.player.getDepth()).getTile(this.player.getxLoc(),this.player.getyLoc()).getFightingMobEntitys();
		
	}
	

	public ArrayList<WorldEntity> getMobs(){
		return this.mobs;
	}
}
