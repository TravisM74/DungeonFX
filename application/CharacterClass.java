package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import Gfx.Humanoid;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class CharacterClass {
	private int hitDice;
	private String classname;
	private int requiredStat;
	private int armourClass;
	private int init;
	private int xpValue;
	private Humanoid entityForm;
	private Color hairColor;
	private Color skinColor;
	private Color eyeColor ;
	private Color topColor ;
	private Color pantsColor ;
	private Color shoeColor ;
	
	
	CharClass charClass;
	
	public CharacterClass(CharClass characterClass) {
		this.charClass = characterClass;
		Random rand = new Random();
		
		switch (charClass) {
		case FIGHTER:
			this.hitDice = 10;
			this.classname = "Fighter";
			this.hairColor = Color.BROWN;
			this.skinColor =Color.CORNSILK;
			this.eyeColor = Color.BLUE;
			this.topColor = Color.BROWN;
			this.pantsColor = Color.BLUE;
			this.shoeColor = Color.DARKGREY;
			this.entityForm = new Humanoid(hairColor,skinColor,eyeColor,topColor,pantsColor,shoeColor);
			break;
			
		case BARBARIAN:
			this.hitDice = 12;
			rand = new Random();
			int red = rand.nextInt(255);
			int green = rand.nextInt(255);
			int blue = rand.nextInt(255);
			this.hairColor = Color.rgb(red, green, blue);
			this.skinColor =Color.BROWN;
			this.eyeColor = Color.LIGHTBLUE;
			this.topColor = Color.BROWN;
			int redbp = rand.nextInt(255);
			int greenbp = rand.nextInt(255);
			int bluebp = rand.nextInt(255);
			this.pantsColor = Color.rgb(redbp, greenbp, bluebp);
			this.shoeColor = Color.DARKGREY;
			this.entityForm = new Humanoid(hairColor,skinColor,eyeColor,topColor,pantsColor,shoeColor);
			this.classname = "Barbarian";
			break;
			
		case THEIF:
			this.hitDice = 6;
			this.classname = "Theif";
			break;
		
		case ORC_FIGHTER:
			this.hitDice = 8;
			this.classname = "Orrrck Bashaa";
			this.armourClass = 7;
			this.init = 12;
			this.xpValue = 100;
			rand = new Random();
			int ored = rand.nextInt(255);
			int ogreen = rand.nextInt(255);
			int oblue = rand.nextInt(255);
			this.hairColor = Color.rgb(ored, ogreen, oblue);
			this.skinColor =Color.GREEN;
			int redp = rand.nextInt(255);
			int greenp = rand.nextInt(255);
			int bluep = rand.nextInt(255);
			this.pantsColor = Color.rgb(redp, greenp, bluep);
			int redt = rand.nextInt(255);
			int greent = rand.nextInt(255);
			int bluet = rand.nextInt(255);
			this.topColor = Color.rgb(redt, greent, bluet);
			this.eyeColor = Color.RED;
			this.shoeColor = Color.GREEN;
			this.entityForm = new Humanoid(hairColor,skinColor,eyeColor,topColor,pantsColor,shoeColor);
									
			break;
		}
	}
	public Humanoid getEntityForm() {
		return this.entityForm;
	}
	public int getXPValue() {
		return this.xpValue;
	}
	
	public int getHitDice () {
		return this.hitDice;
	}
	public String getClassName() {
		return this.classname;
	}
	public int getRequiredStat() {
		return requiredStat;
	}
	public int getArmourClass() {
		return armourClass;
	}
	
	public int getInit() {
		return init;
	}
	
	public CharClass getCharClass() {
		return charClass;
	}
	
	
}
/*
 * removed for self created images...
			try {
				this.charImage = new Image(new FileInputStream("src/Images/orc.png"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.image = new ImageView(this.charImage);
 */
/*
		this.image.setFitHeight(25);
		this.image.setFitWidth(25);
		image.setPreserveRatio(false);
 */

/*
	public Image getCharImage() {
		return charImage;
	}
 */	
/*
	public ImageView getImageView() {
		return this.image;
	}
	public ImageView setImageXY(double d,double e) {
		this.image.setX(d);
		this.image.setY(e);
		return this.image;
	}
	public void setTranslateX(double d) {
		this.image.setTranslateX(d);
		
	}
	public void setTranslateY(double d) {
		this.image.setTranslateY(d);
	}
 */

/*
	public double getTranslateX() {
		return this.image.getTranslateX();
	}
	public double getTranslateY() {
		return this.image.getTranslateY();
	}
 */