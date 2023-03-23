package attributes;

public class StatsModifier {
	
	private int modifier;
	public StatsModifier() {
		
	}
	

	public int statApprase(int stat) {
		switch (stat) {
		case 3: 
			this.modifier = -5;
			break;
		case 4:
			this.modifier = -4;
			break;
		case 5:
			this.modifier = -4;
			break;
		case 6:
			this.modifier = -3;
			break;
		case 7:
			this.modifier = -3;
			break;
		case 8:
			this.modifier = -2;
			break;
		case 9:
			this.modifier = -1;
			break;
		case 10:
			this.modifier = -0;
			break;
		case 11:
			this.modifier = -0;
			break;
		case 12:
			this.modifier = 1;
			break;
		case 13:
			this.modifier = 1;
			break;
		case 14:
			this.modifier = 2;
			break;
		case 15:
			this.modifier = 2;
			break;
		case 16:
			this.modifier = 3;
			break;
		case 17:
			this.modifier = 3;
			break;
		case 18:
			this.modifier = 4;
			break;
		case 19:
			this.modifier = 4;
			break;
		case 20:
			this.modifier = 5;
			break;
		case 21:
			this.modifier = 5;
			break;
		case 22:
			this.modifier = 6;
			break;
		case 23:
			this.modifier = 6;
			break;
		case 24:
			this.modifier = 7;
			break;
		}
		return this.modifier;
	}
		

}
