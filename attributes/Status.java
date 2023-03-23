package attributes;

import java.util.Random;

public class Status {
	
	
	
	private StatusState StatusState;
	private String description;
	private int healthRegen = 0;
	private Random rand;
	private boolean visableOnMap;
	
	
	public Status(StatusState statusStates) {
		this.StatusState = statusStates;
		
		switch (statusStates) {
			case ADVENTURING:
				this.description = "Seeking fame and glory and some coin..";
				this.healthRegen = 0;
				
				break;
			case RESTING:
				this.description = "Resting..";
				this.healthRegen = 2 ;
				break;
			case UNCONCEOUS:
				this.description = "Out Cold..";
				this.healthRegen = 1;
				break;
			case HIDING:
				this.description = "..";
				this.healthRegen = 0;
				this.visableOnMap = false;
				break;
			case DETECTED:
				this.description = "Has been detected.";
				this.healthRegen = 0;
				this.visableOnMap = true;
				break;
			case SEARCHING:
				this.description = "Looking for things..";
				this.healthRegen = 0;
				break;
			case HEALING:
				this.description = "using Bandages and stuff..";
				this.rand = new Random();
				this.healthRegen = rand.nextInt(8);
				break;
			case DEAD:
				this.description = "Pushing up dasies..";
				this.healthRegen = -5;
				break;
		
		}
		
	}
	public String toString() {
		return this.description;
	}
	public String getDescription() {
		return this.description;
	}

	public int gethealthRegen() {
		return this.healthRegen;
	}

}
