package Control;

import World.WorldArray;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import old_code.Player;

public class MovementControlButtons {
		BorderPane rootPane;
		Player player;
		
	
	public MovementControlButtons(Player player) {
		this.player = player;
		this.rootPane = new BorderPane();
		VBox upPane = new VBox();
		Button upButton = new Button(" UP ");
		upButton.setOnAction(e -> { 
			this.player.moveUp();
		});
		upPane.getChildren().add(upButton);
		upPane.setAlignment(Pos.CENTER);
		this.rootPane.setTop(upPane);
		
		Button downButton = new Button(" Down ");
		VBox downPane = new VBox();
		downButton.setOnAction(e -> { 
			this.player.moveDown();
		});
		downPane.getChildren().add(downButton);
		downPane.setAlignment(Pos.CENTER);
		this.rootPane.setBottom(downPane);
		
		Button leftButton = new Button(" Left ");
		VBox leftPane = new VBox();
		leftButton.setOnAction(e -> { 
			this.player.moveLeft();
		});
		leftPane.getChildren().add(leftButton);
		leftPane.setAlignment(Pos.CENTER);
		this.rootPane.setLeft(leftPane);
		
		Button rightButton = new Button(" Right ");
		VBox rightPane = new VBox();
		
		rightButton.setOnAction(e -> { 
			this.player.moveRight();
		});
		rightPane.getChildren().add(rightButton);
		rightPane.setAlignment(Pos.CENTER);
		this.rootPane.setRight(rightPane);
		
		Button sheetButton = new Button(" . ");
		VBox centerPane = new VBox();
		centerPane.getChildren().add(sheetButton);
		centerPane.setAlignment(Pos.CENTER);
		this.rootPane.setCenter(centerPane);
		
	}
	public BorderPane getButtons() {
		return rootPane;
	}
}
