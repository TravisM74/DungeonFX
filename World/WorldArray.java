package World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import application.Mob;
import application.WorldEntity;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import old_code.Player;

public class WorldArray {
	
	
	private HashMap<Double, Integer> pixelXToArray;
	private HashMap<Integer, Double> arrayXToPixel;
	private HashMap<Double, Integer> pixelYToArray;
	private HashMap<Integer, Double> arrayYToPixel;
	private Random rand;
	private Tile tile;
	
	
	private ArrayList<DungeonLevel> worldDepth;
	private DungeonLevel level;
	
	public WorldArray() {
		
		
		this.worldDepth = new ArrayList<>();
		
		
		pixelXToArray = new HashMap<>();
		arrayXToPixel = new HashMap<>();
		
		int pixelXStart = 0;
		for (int i = 0; i < 78; ++i) {
			double value = i*50 +(pixelXStart);
			pixelXToArray.put(value, i);
			arrayXToPixel.put(i, value);
		}
		pixelYToArray = new HashMap<>();
		arrayYToPixel = new HashMap<>();
		double pixelYStart = 0;
		for (int i = 0; i < 36; ++i) {
			double value = i*50 +(pixelYStart);
			pixelYToArray.put(value, i);
			arrayYToPixel.put(i, value);
		}
		level = new DungeonLevel();
		this.worldDepth.add(level);
		
	}
	public void moveEntity(Mob player,WorldEntity entity) {
		rand = new Random();
		//removing entity tag from tile
		this.worldDepth.get(player.getDepth()).getTile(player.getEntity().getxLoc(), player.getEntity().getyLoc()).remove(entity);
				
		boolean notValidSpot = true;
		while (notValidSpot) {
			int xLoc = rand.nextInt(38);
			int yLoc = rand.nextInt(18);
			
			if ((player.getEntity().getxLoc() == xLoc)&&(player.getEntity().getyLoc() == yLoc)) {
				//not spawning orc on player...
			} else {
				this.tile =this.worldDepth.get(player.getDepth()).getTile(xLoc, yLoc);
				entity.setxLoc(xLoc);
				entity.setyLoc(yLoc);
				System.out.println("moved "+ entity.getDescription()+" to X:"+entity.getxLoc() +" Y:" + entity.getyLoc());
				this.tile.add(entity);
				entity.getMob().getCClass().getEntityForm().getBody().setTranslateX(getXPixel(entity.getxLoc()));
				entity.getMob().getCClass().getEntityForm().getBody().setTranslateY(getYPixel(entity.getyLoc()));
				notValidSpot = false;
			}
		}
	}
	
	public void createNewLevel() {
		DungeonLevel newLevel = new DungeonLevel();
		worldDepth.add(newLevel);
	}
	 
	public DungeonLevel getDungeonlevel(int depth) {
		return this.worldDepth.get(depth);
	}
	

	public double getXPixel(int value) {
		return arrayXToPixel.get(value);
	}
	
	public double getYPixel(int value) {
		return arrayYToPixel.get(value);
	}
	public int getYArray(double value) {
		return pixelYToArray.get(value);
	}
	public int getXArray(double value) {
		return pixelXToArray.get(value);
	}
	
}
