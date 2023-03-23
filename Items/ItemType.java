package Items;

import Gfx.Short_Sword;
import javafx.scene.Group;

public class ItemType {
	
	private ItemTypeEnum itemType;
	private Group itemForm;
	private String description;
	private Quality quality;
	private int damage;
	private DamageType damageType;
	
	
	public ItemType(ItemTypeEnum itemType) {
		this.itemType =itemType;
		this.quality = quality;
		
		switch(this.itemType) {
			case EMPTY:
				this.description = " empty ";
				this.damage = 0;
				break;
			
			case BAG:
				this.description = " bag ";
				this.damage = 0;
				break;
			
			case ARMOUR:
				this.description = " peice of armour ";
				this.damage = 0;
				break;
			case WEAPON:
				this.description = " weapon ";
				this.damage = 0;
				break;
			
			
			default:
				break;
		}
	}
	public String toString() {
		return this.description;
	}
	public int getDamageDiceValue() {
		return this.damage;
	}
	public ItemTypeEnum getEnumType() {
		return this.itemType;
	}

}
