package UserInterface;

import Gfx.Axe;
import Gfx.Humanoid;
import Gfx.Long_Sword;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

public class ItemInterface {
	private BorderPane root;
	private Humanoid playerForm;
	private Group item;
	private Button back;
	private Scene scene;
	private Stage stage;
	private Scene itemSheet;
	
	public ItemInterface(Humanoid player, Scene scene, Stage stage) {
		Axe shortSword = new Axe();
		this.item = shortSword.getItemForm();
		this.playerForm = player;
		System.out.println("hand x:"+playerForm.getRightHandX());
		System.out.println("hand Y:"+playerForm.getRightHandY());
		System.out.println("body x:"+playerForm.getTranslateX()+" body y:"+playerForm.getTranslateY());
		;
		this.scene = scene;
		this.stage = stage;
		this.root = new BorderPane();
		
		System.out.println("item x:"+item.getTranslateX()+" item y:"+item.getTranslateY());
		Pane centerPane = new Pane();
		item.setTranslateX(playerForm.getRightHandX());
		item.setTranslateY(playerForm.getRightHandY());
		//item.setRotate(15);
		
		centerPane.getChildren().addAll(playerForm.getBody(),item);
		
		
		back = new Button("Back");
		back.setOnAction(e -> backClicked());
		VBox bottomPane = new VBox();
		bottomPane.getChildren().addAll(back);
		root.setBottom(bottomPane);
		
		root.setCenter(centerPane);
		this.itemSheet = new Scene(root,900,800);
		stage.setScene(itemSheet);
		
	}
	public void openItemPane() {
		stage.setScene(itemSheet);
	}
	private void backClicked() {
		stage.setScene(this.scene);
	}

}
