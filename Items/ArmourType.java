package Items;

import java.util.ArrayList;

public class ArmourType {
	private ArmourTypeEnum armourType;
	private int baseAC;
	private String desc;
	private ArrayList<DamageType> resists;
	private ArrayList<DamageType> vulnerable;
	
	
	public ArmourType(ArmourTypeEnum type) {
		this.resists = new ArrayList<>();
		this.vulnerable = new ArrayList<>();
		this.armourType = type;
		
		switch (armourType) {
		case NONE:
			this.baseAC = 0;
			this.desc = " nothing ";
			break;
		case CLOTH:
			this.baseAC = 0;
			this.desc = " cloth ";
			break;
		case LEATHER:
			this.baseAC = 1;
			this.desc = " leather ";
			break;
		case CHAIN:
			this.baseAC = 2;
			this.desc = " chain ";
			break;	
		case SCALE:
			this.baseAC = 3;
			this.desc = " scale ";
			break;	
		case PLATE:
			this.baseAC = 4;
			this.desc = " plate ";
			break;	
		}
	}
	public String toString() {
		return this.desc;
	}

}
