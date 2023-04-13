package Control;

import World.WorldArray;
import application.Mob;
import application.WorldEntity;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class MovementControlButtons {
		BorderPane rootPane;
		WorldEntity player;
		
	
	public MovementControlButtons(Mob player2) {
		this.player = player2;
		this.rootPane = new BorderPane();
		VBox upPane = new VBox();
		Button upButton = new Button(" UP ");
		upButton.setOnAction(e -> { 
			this.player.getMob().moveUp();
		});
		upPane.getChildren().add(upButton);
		upPane.setAlignment(Pos.CENTER);
		this.rootPane.setTop(upPane);
		
		Button downButton = new Button(" Down ");
		VBox downPane = new VBox();
		downButton.setOnAction(e -> { 
			this.player.getMob().moveDown();
		});
		downPane.getChildren().add(downButton);
		downPane.setAlignment(Pos.CENTER);
		this.rootPane.setBottom(downPane);
		
		Button leftButton = new Button(" Left ");
		VBox leftPane = new VBox();
		leftButton.setOnAction(e -> { 
			this.player.getMob().moveLeft();
		});
		leftPane.getChildren().add(leftButton);
		leftPane.setAlignment(Pos.CENTER);
		this.rootPane.setLeft(leftPane);
		
		Button rightButton = new Button(" Right ");
		VBox rightPane = new VBox();
		
		rightButton.setOnAction(e -> { 
			this.player.getMob().moveRight();
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
