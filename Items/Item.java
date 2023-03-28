package Items;

import javafx.scene.Group;

public class Item {
	
	private String description;
	private int weight;
	private int baseValueInCopper;
	private ArmourType armourType;
	private WeaponType weaponType;
	private ItemType type;
	private Quality quality;
	private int weightCapacity;
	private UsedEnum location;
	private int magicBonus;
	private ItemTypeEnum itemTypeEnum;
	private double opacity;
	

	

	//adding a generalItem
	public Item(String description,ItemTypeEnum type,UsedEnum location, QualityEnum quality,int magicBonus, int weight, int valueCopperCoin) {
		this.description = description;
		this.armourType = new ArmourType(ArmourTypeEnum.NONE);
		this.weaponType = new WeaponType(WeaponTypeEnum.NONE);
		this.type =  new ItemType(type);
		this.itemTypeEnum = type;
		this.quality = new Quality(quality);
		this.weightCapacity = 0;
		this.location = location;
		this.magicBonus = magicBonus;
		this.weight = weight;
		this.baseValueInCopper = valueCopperCoin;
		
		
	}
	//Adding a weapon item
	public Item(String weaponDesc,ItemTypeEnum typeOfItem,WeaponTypeEnum weaponType, QualityEnum quality,UsedEnum location,int magicBonus, int weight, int valueCopperCoin) {
		this.description = weaponDesc;
		this.armourType = new ArmourType(ArmourTypeEnum.NONE);
		this.weaponType = new WeaponType(weaponType);
		this.type = new ItemType(typeOfItem);
		this.quality = new Quality(quality);
		this.weightCapacity = 0;
		this.location = location;
		this.magicBonus = magicBonus;
		this.itemTypeEnum = typeOfItem;
		this.weight = weight;
		this.baseValueInCopper = valueCopperCoin;
	
	}
	
	//Adding Armour
	public Item (String armourDesc,ItemTypeEnum type,ArmourTypeEnum armourType,	QualityEnum common,UsedEnum location,int magicBonus, int weight, int valueCopperCoin) {
		this.description = armourDesc;
		this.type = new ItemType(type);
		this.itemTypeEnum = type;
		this.weaponType = new WeaponType(WeaponTypeEnum.NONE);
		this.armourType = new ArmourType(armourType);
		this.quality = new Quality(common);
		this.location = location;
		this.magicBonus = magicBonus;
		this.weight = weight;
		this.baseValueInCopper = valueCopperCoin;
		this.weightCapacity = 0;
		
	}
	
	
	public ArmourType getArmourtype() {
		return this.armourType;
	}
	public UsedEnum locationUsed() {
		return this.location;
	}
	public Quality getQuality() {
		return this.quality;
	}
	public void setCarryCapacity(int value) {
		this.weightCapacity = value;
	}
	public WeaponType getWeaponType() {
		return this.weaponType;
	}
	public ItemTypeEnum getItemTypeEnum() {
		return this.itemTypeEnum;
	}
	public String getDescription() {
		return this.description;
	}

	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public int getMagicDamageBonus() {
		return this.magicBonus;
	}
	public String toString() {
		switch(this.type.getEnumType()) {
			case ARMOUR:
				return this.quality+" " + this.type + this.armourType +this.description;
			case WEAPON:
				return  this.quality+" " + this.type + this.weaponType +this.description;
			default:
				return this.description;
			}
		
		
	}
	
}
