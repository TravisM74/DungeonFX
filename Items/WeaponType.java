package Items;

import Gfx.Axe;
import Gfx.Dagger;
import Gfx.Fist;
import Gfx.HeldItem;
import Gfx.Long_Sword;
import Gfx.Short_Sword;

public class WeaponType {
	private WeaponTypeEnum weaponType;
	private int damageDice;
	private DamageType damageType;
	private String desc;
	private Size size;
	private HeldItem mainhandHeldItem;  
	
	
	
	public WeaponType(WeaponTypeEnum wep) {
		this.weaponType = wep;
		
		switch (weaponType){
		case NONE:
			this.desc = " not a weapon ";
			this.damageDice = 0;
			break;
		case HANDS:
			this.desc = " fists ";
			this.damageDice = 3;
			this.damageType = new DamageType(DamageTypeEnum.BLUDGEON);
			this.mainhandHeldItem = new Fist();
			break;
		case SHORT_SWORD:
			this.desc = " short sword ";
			this.damageDice = 6;
			this.damageType = new DamageType(DamageTypeEnum.SLASH);
			this.mainhandHeldItem = new Short_Sword();
			
			break;
		case LONG_SWORD:
			this.damageDice = 8;
			this.damageType = new DamageType(DamageTypeEnum.SLASH);
			this.desc = " long sword ";
			this.mainhandHeldItem =new Long_Sword();
			break;	
		case TWO_HANDED_SWORD:
			this.damageDice = 12;
			this.damageType = new DamageType(DamageTypeEnum.SLASH);
			this.desc = " two handed sword ";
			break;	
		case AXE:
			this.damageDice = 8;
			this.damageType = new DamageType(DamageTypeEnum.SLASH);
			this.desc = " axe ";
			this.mainhandHeldItem = new Axe();
			break;	
		case TWO_HANDED_AXE:
			this.damageDice = 12;
			this.damageType = new DamageType(DamageTypeEnum.SLASH);
			this.desc = " two handed axe ";
			break;
		case DAGGER:
			this.damageDice = 4;
			this.damageType = new DamageType(DamageTypeEnum.PEARCE);
			this.desc = " dagger ";
			this.mainhandHeldItem = new Dagger();
			break;
		
			
		}
	}
	public HeldItem getMainhandHeldItemForm() {
		return this.mainhandHeldItem;
	}
	public int getDamageDice() {
		return this.damageDice;
	}
	public DamageType getDamageType() {
		return this.damageType;
	}
	public String toString() {
		return this.desc;
	}
	public WeaponTypeEnum getweaponsTypeEnum() {
		return this.weaponType;
	}
}
