package attributes;

import java.util.Random;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Stats {
	private int strength;
	private int sMod;
	private int dexterity;
	private int dMod;
	private int constitution;
	private int cMod;
	private Random rand;
	private StatsModifier statsMod;
	
	public Stats() {
		
		this.strength = rollAttrib();
		this.sMod = 0;
		this.dMod = 0;
		this.cMod = 0;
		this.dexterity = rollAttrib();
		this.constitution = rollAttrib();
		this.statsMod = new StatsModifier();
		this.sMod = statsMod.statApprase(this.strength);
		this.dMod = statsMod.statApprase(this.dexterity);
		this.cMod = statsMod.statApprase(this.constitution);
		
	}

	private int rollAttrib() {
		int value =0;
		for (int i = 0 ; i < 3 ;i++) {
			rand = new Random();
			value+= rand.nextInt(5)+1;
		}
		return value;
	}
	
	public VBox displayStatsBox() {
		VBox statsBox = new VBox();
		Label statsLabel = new Label("<-------Stats-------->");
		Label strStat = new Label("Strength    \t:"+getStrength()+" ("+getSMod()+")");
		Label dexStat = new Label("Dexterity  \t:"+getDexterity()+" ("+getDMod()+")");
		Label conStat = new Label("Constitution\t:"+getConstitution()+" ("+getCMod()+")");
		Label statsLabelBottom = new Label("<-------------------->");
		statsBox.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, null, null)));
		statsBox.getChildren().addAll(statsLabel,strStat,dexStat,conStat,statsLabelBottom);
		return statsBox;
	}
	
	public int getStrength() {
		return this.strength ;
	}
	public int getDexterity() {
		return this.dexterity;
	}
	public int getConstitution() {
		return this.constitution ;
	}
	
	
	public int getSMod() {
		return statsMod.statApprase(this.strength);
	}
	public int getDMod() {
		return  statsMod.statApprase(this.dexterity);
	}
	public int getCMod() {
		return statsMod.statApprase(this.constitution);
	}
	
	
	
	public String toSting() {
		return ("< --   Current Stats    --> \n"+
				"Strength     :" +this.strength + "   \t (" + this.sMod +") \n" +
				"Dexterity    :" +this.dexterity + "  \t (" + this.dMod +") \n" +
				"Constitution :" +this.constitution + "  \t (" + this.cMod +") \n"
				);
	}


}
