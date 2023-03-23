package Items;

public class DamageType {
	private DamageTypeEnum damageType;
	private String desc;
	
	public DamageType(DamageTypeEnum damageType) {
		this.damageType = damageType;
		switch(damageType) {
		case NONE:
			this.desc = " causes no damage ";
		case BLUDGEON:
			this.desc = " bludgeoning " ;
			break;
		case SLASH:
			this.desc = " slashing ";
			break;
		case PEARCE:
			this.desc = " pearcing ";
			break;
		}
	}
	public String toString() {
		return this.desc;
	}

}
