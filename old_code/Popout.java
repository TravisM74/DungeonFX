package old_code;

import java.util.ArrayList;

import application.Player;
import application.WorldEntity;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Popout extends Application{
	private WorldEntity entity;
	private Player player;
	private ArrayList<String> text;
	private Label displayLabel;
	public Popout(Player player,WorldEntity entity, ArrayList<String> text) {
		this.entity = entity;
		this.player = player;
		this.text = text;
		
	}
	
	@Override
	public void start(Stage stage) {
			
		
		BorderPane popout = new BorderPane();
		Scene scene = new Scene(popout,600,600);
		
		VBox pStats = new VBox();
		Pane playerStats = new Pane();
		
		playerStats = player.getStatsPane();
		pStats.getChildren().add(playerStats);
		popout.setLeft(playerStats);
		
		VBox mobStats = new VBox();
		mobStats.getChildren().add(entity.getMob().getStatsPane());
		popout.setRight(mobStats);

		HBox centreHBox = new HBox();
		Pane leftCentre = new Pane();
		Pane combatTextPane = new Pane();
		for(String textLine:this.text) {
			centreHBox.getChildren().add(new Label(textLine));
			
		}
		Text combatText = new Text();
		Pane rightCentre = new Pane();
		popout.setCenter(centreHBox);
		centreHBox.getChildren().addAll(leftCentre,combatText,rightCentre);
		
		stage.setScene(scene);
		stage.show();
		}

	}

