package Items;

public class Size {
	private SizeEnum size;
	private String desc;
	private int damageModifier;
	private int usageModifier;
	
	public Size(SizeEnum size) {
		this.size = size;
		
		switch(this.size) {
		case TINY:
			this.desc = " tiny ";
			this.damageModifier = -2;
			this.usageModifier = 2;
			break;
		case SMALL:
			this.desc = " small ";
			this.damageModifier = -1;
			this.usageModifier = 1;
			break;
		case REGULAR:
			this.desc = " normal ";
			this.damageModifier = 0;
			this.usageModifier = 0;
			break;
		case LARGE:
			this.desc = " large ";
			this.damageModifier = -2;
			this.usageModifier = 3;
			break;	
		case HUMUNGUS:
			this.desc = " humungus ";
			this.damageModifier = -4;
			this.usageModifier = 6;
			break;	
		
		}
	}

}
