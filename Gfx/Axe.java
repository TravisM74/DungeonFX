package Gfx;



import java.util.Random;

import Items.Quality;
import Items.QualityEnum;
import application.WorldEntity;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class Axe extends Group implements HeldItem{
	
	private Group item;
	
	private Color color;
	private Quality quality;
	private Color baseColor;
	//private double scale = .13;
	private double scale = .14;
	private double startPointX = 0;
	private double startPointY = 0;
	private double swordLength = 40;
	private double handleEnd = 120;
	private double xLoc;
	private double yLoc;
	private double itemRotateAngle;
	
	public Axe() {
		
		this.item = new Group();
		
		//this.baseColor = quality.getBaseColor();
		Random rand = new Random();
		itemRotateAngle = rand.nextDouble(180) -90;
		
		Rotate rotate = new Rotate();
		rotate.setPivotX(startPointX);
		rotate.setPivotY(startPointY);
		
		item.getTransforms().add(rotate);
		rotate.setAngle(itemRotateAngle);
		
		
		drawHandle();
		drawBlade();
	}
	public void drawHandle() {
		Line handleLine = new Line();
		handleLine.setStroke(Color.SADDLEBROWN);
		handleLine.setStartX(startPointX);
		handleLine.setStartY(startPointY);
		handleLine.setEndX(startPointX + handleEnd*scale);
		handleLine.setEndY(startPointY);
		handleLine.setStrokeWidth(12*scale);
		item.getChildren().add(handleLine);
		
	}
	public void drawBlade() {
		Polygon axeHead = new Polygon();
		axeHead.getPoints().setAll(
				startPointX + handleEnd*scale				,startPointY,
				startPointX + handleEnd*scale -(20*scale)	, startPointY +(35*scale),
				startPointX + handleEnd*scale +(30*scale)	, startPointY +(35*scale),
				startPointX + handleEnd*scale +(10*scale)	, startPointY ,
				startPointX + handleEnd*scale 				, startPointY-(20*scale) );
				
		axeHead.setStroke(Color.GRAY);
		
		axeHead.setStrokeWidth(24*scale);
		item.getChildren().add(axeHead);
		
	}
	
	public Group getItemForm() {
		return this.item;
	}
	public void setStartPointX(double value) {
		this.startPointX = value;
		
	}
	public void setStartPointY(double value) {
		this.startPointY = value;
	}
	public void setQuality(Quality quality) {
		this.quality =quality;
	}
}


