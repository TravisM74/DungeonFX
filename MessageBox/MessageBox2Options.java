package MessageBox;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox2Options {
	
	public MessageBox2Options(String message, String title,String button1, String button2) {
	 Stage stage = new Stage(); 
	 stage.initModality(Modality.APPLICATION_MODAL); 
	 stage.setTitle(title);
	 stage.setMinWidth(250); 
	 Label lbl = new Label(); 
	 lbl.setText(message);
	 Button btnOK1 = new Button();
	 Button btnOK2 = new Button();
	 btnOK1.setText("button1");
	 btnOK1.setOnAction(e -> stage.close()); 
	 btnOK2.setText("button2");
	 btnOK2.setOnAction(e -> stage.close()); 
	 VBox pane = new VBox(20);
	 pane.getChildren().addAll(lbl, btnOK1,btnOK2);
	 pane.setAlignment(Pos.CENTER); 
	 Scene scene = new Scene(pane); 
	 stage.setScene(scene);
	 stage.showAndWait();
	 }
}
