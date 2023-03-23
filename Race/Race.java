package Race;

public class Race {
	private int hpModifier;
	private int visionModifier;
	private int weightMofifier;
	private int strModifier;
	private int dexModifier;
	private int conModifier;
	private double xpModifier;
	
	
private RaceEnum race;
	
	public Race(RaceEnum race) {
		
		this.race = race;
		
	
		switch(race) {
		
		case HUMAN:
			hpModifier = 0;
			visionModifier = 0;
			weightMofifier = 0;
			strModifier = 0;
			dexModifier = 0;
			conModifier = 0;
			xpModifier = 1.15;
			
			break;
		
		case DWARF:
			hpModifier = 0;
			visionModifier = 5;
			weightMofifier = 0;
			strModifier = +1;
			dexModifier = -2 ;
			conModifier = +1;
			xpModifier = 0;
			break;
		
		case ELF:
			hpModifier = 0;
			visionModifier = 5;
			weightMofifier = 0;
			strModifier = -2;
			dexModifier = +3 ;
			conModifier = -1;
			xpModifier = 0;
			break;
		case ORC:
			hpModifier = 0;
			visionModifier = 2;
			weightMofifier = 0;
			strModifier = +2;
			dexModifier = -2 ;
			conModifier = 2;
			xpModifier = 0;
			break;
		
		
		case UNKNOWN:
			hpModifier = 0;
			visionModifier = 0;
			weightMofifier = 0;
			strModifier = 0;
			dexModifier = 0 ;
			conModifier = 0;
			xpModifier = 0;
			break;
		}
	}

	public int getHpModifier() {
		return hpModifier;
	}

	public void setHpModifier(int hpModifier) {
		this.hpModifier = hpModifier;
	}

	public int getVisionModifier() {
		return visionModifier;
	}

	public void setVisionModifier(int visionModifier) {
		this.visionModifier = visionModifier;
	}

	public int getWeightMofifier() {
		return weightMofifier;
	}

	public void setWeightMofifier(int weightMofifier) {
		this.weightMofifier = weightMofifier;
	}

	public int getStrModifier() {
		return strModifier;
	}

	public void setStrModifier(int strModifier) {
		this.strModifier = strModifier;
	}

	public int getDexModifier() {
		return dexModifier;
	}

	public void setDexModifier(int dexModifier) {
		this.dexModifier = dexModifier;
	}

	public int getConModifier() {
		return conModifier;
	}

	public void setConModifier(int conModifier) {
		this.conModifier = conModifier;
	}

	public double getXpModifier() {
		return xpModifier;
	}

	public void setXpModifier(double xpModifier) {
		this.xpModifier = xpModifier;
	}

	public RaceEnum getRace() {
		return race;
	}

	public void setRace(RaceEnum race) {
		this.race = race;
	}


}
