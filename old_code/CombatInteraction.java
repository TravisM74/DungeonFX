package old_code;

import java.io.IOException;

import World.WorldArray;
import application.WorldEntity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class CombatInteraction {
	private WorldArray world;
	private Player player;
	private WorldEntity entity;
	
	public CombatInteraction(Player player,WorldEntity entity) {
		try {
		
			FXMLLoader loader = new FXMLLoader();
			Parent root = loader.load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		this.player = player;
		this.entity = entity;
		//this.world = world;
		
	}

}
