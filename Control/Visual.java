package Control;

import Items.Item;
import javafx.scene.layout.Pane;

public class Visual {
	private Pane root;
	
	public Visual(Pane centerwindow) {
		this.root = centerwindow;
		
	}
	public void removeWeaponVisual(Item item) {
		this.root.getChildren().remove(item.getWeaponType().getMainhandHeldItemForm().getItemForm());
	}
	public void AddWeaponVisual(Item item) {
		this.root.getChildren().add(item.getWeaponType().getMainhandHeldItemForm().getItemForm());
	}

}
