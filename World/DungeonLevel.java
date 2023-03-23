package World;

import java.util.ArrayList;
import java.util.List;

import application.Entities;
import application.WorldEntity;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DungeonLevel {
	private Tile[][] level; 
	private WorldEntity emptySpace;
	private final int WIDTH = 39;
	private final int HEIGHT = 18;

	
	public DungeonLevel() {
			
		
		level = new Tile[WIDTH][HEIGHT];
		for (int i = 0; i < WIDTH; i++) {
			for(int j = 0 ; j < HEIGHT; j++) {
				
				level[i][j] = (new Tile(i,j));
				
			}
		}
		//testArray();
		
	}
	public void testArray() {
		for (int i = 0; i < WIDTH; i++) {
			for(int j = 0 ; j < HEIGHT; j++) {
				System.out.println(level[i][j]);
			}
		}
	
	}

	public Tile getTile(int x, int y) {
		return level[x][y];
	}
	
	public void MoveLeft(int x ,int y, WorldEntity entity) {
		//getTile(x,y).remove(entity);
		//getTile(x,y).add(emptySpace);
		//getTile(x-1,y).add(entity);
	}
	public void MoveRight(int x ,int y, WorldEntity entity) {
		//getTile(x,y).remove(entity);
		//getTile(x,y).add(emptySpace);
		//getTile(x+1,y).add(entity);
		
	}
	public void MoveUp(int x ,int y, WorldEntity entity) {
		//getTile(x,y).remove(entity);
		//getTile(x,y).add(emptySpace);
		//getTile(x,y-1).add(entity);
		
	
	}
	public void MoveDown(int x ,int y, WorldEntity entity) {
		//getTile(x,y).remove(entity);
		//getTile(x,y).add(emptySpace);
		//getTile(x,y+1).add(entity);
		
	}
	

}
