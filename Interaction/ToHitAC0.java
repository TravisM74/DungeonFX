package Interaction;

import java.util.Random;

import application.CharClass;
import application.WorldEntity;

public class ToHitAC0 {
	private CharClass cClass;
	private int level;
	private int armourClass;
	private int thac0;
	private int toHit;
	
	private Random rand;
	
	public ToHitAC0(WorldEntity attacker, WorldEntity defender) {
		this.cClass = attacker.getMob().getCClass().getCharClass();
		this.level = attacker.getMob().getLevel();
		switch(attacker.getMob().getCClass().getCharClass()) {
			case FIGHTER:
				this.thac0 = 20-(level - 1);
				break;
			case ORC_FIGHTER:
				this.thac0 = 20-(level - 1);
				break;
			case BARBARIAN:
				this.thac0 = 20-(level - 1);;
				break;
			case THEIF: 
				this.thac0 = 20-((level/2)- 1);
				break;
		}
		
	}
	
	public ToHitAC0(int armourClass ,int level ,CharClass cClass) {
		this.armourClass = armourClass;
		this.level = level;
		this.cClass = cClass;
		this.rand = new Random();
		
		switch (this.cClass) {
			case FIGHTER:
				this.thac0 = 20-(level - 1);
				break;
			case ORC_FIGHTER:
				this.thac0 = 20-(level - 1);
				break;
			case BARBARIAN:
				this.thac0 = 20-(level - 1);;
				break;
			case THEIF: 
				this.thac0 = 20-((level/2)- 1);
				break;
		}	
		
	}
	
		
	
	
	public boolean tryTohit() {
		
		toHit = (thac0 - level)- armourClass;
		//System.out.print("a " +toHit + ":(20) is needed to hit, ");
		int roll = (rand.nextInt(20)+1);
		//System.out.print("rolls a "+ roll +" ");
		if(toHit <= roll) {
			//System.out.println(" and hit.");
			return true;
		} else {
			//System.out.println(" and missed.");
			return false;
		}
	}
}
