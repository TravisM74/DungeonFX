package Gfx;

import java.util.Random;

import javafx.scene.Group;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;


public class Humanoid extends Group {
	// .14 good scale for grid
	private double scale = .14;
	
	private double bodyLength= 100 *scale;
	private double upperArmLength = 50 *scale;
	private double lowerArmLength = 50 *scale;
	private double upperLegLength = 60 *scale;
	private double lowerLegLength = 70 *scale;
	private double footSize =18 *scale;
	private double handSize = 15 *scale;
	private double shoulderSize = 10 *scale;
	private double eyeSize = 7 *scale;
	private double pupalSize = 3 *scale;
	
	private double elbowPosture = 20*scale;
	private double hipGape = -20*scale;
	private double hipSize = 10*scale;
	private double shoulderGape = 10*scale;
	private double handSpacing = -30*scale;
	private double gape  = 10 *scale;
	
	private Color hairColor;
	private Color skinColor;
	private Color pantsColor;
	private Color topColor;
	private Color shoeColor;
	private Color eyeColor;
	
	private double xLoc;
	private double yLoc;
	private Group body;
	
	//private double headStartPointX= 200*scale;
	//private double headStartPointY= 90*scale;
	private double headStartPointX= 25;
	private double headStartPointY= 10;
	
	
	private Circle head;
	
	private Circle leftHand;
	private Circle rightHand;
	
	private Polygon torso;
	private double shoulderY; 
	private double leftShoulderX ;
	private double rightShoulderX;
	
	
	private double baseLeftHipX ;
	private double baseRightHipX ;
	
	private double leftElbowX ; 
	private double rightElbowX;
	
	private double leftHandX ;
	private double rightHandX ;
	
	private double kneeY ;
	private double leftKneeX ;
	private double rightKneeX ;
	
	private double hipToLegY;
	
	public Humanoid(Color hair,Color skin,Color eyes,Color top,Color pants,Color shoes) {
		this.hairColor = hair;
		this.skinColor =skin;
		this.eyeColor = eyes;
		this.topColor = top;
		this.pantsColor = pants;
		this.shoeColor = shoes;
		
		this.body = new Group();
		this.xLoc = 0;
		this.yLoc = 0;
				
		createHead();
		shoulderY = (head.getCenterY() + head.getRadius()*.85);
		leftShoulderX = ((head.getCenterX() - head.getRadius()*1.25));
		rightShoulderX = ((head.getCenterX() + head.getRadius()*1.25));
		
		baseLeftHipX = (leftShoulderX-hipGape);
		baseRightHipX = (rightShoulderX +hipGape);
		
		leftElbowX = (leftShoulderX - elbowPosture); 
		rightElbowX=(rightShoulderX+elbowPosture);
		
		leftHandX = (leftShoulderX + (elbowPosture*.25));
		rightHandX = (rightShoulderX -(elbowPosture*.25));
		
		createArms();
		double hipUpperY = (shoulderY + bodyLength);
		createTorsoAndHips(hipUpperY);		
		createLegs();
	
	}
	
	
	private void createHead() {
		Color pupalColor = Color.BLACK;
		Color mouthColor = Color.RED;
		Polygon mouth;
		Circle leftEye;
		Circle rightEye;
		Circle leftPupal;
		Circle rightPupal;
		
		
		head = new Circle();
		head.setRadius((125/2.5)*scale);
		head.setCenterX(xLoc + (headStartPointX));
		head.setCenterY(yLoc + (headStartPointY));
		head.setFill(this.skinColor);
		
		Circle hair = new Circle();
		hair.setRadius((head.getRadius()));
		hair.setCenterX(head.getCenterX());
		hair.setCenterY(head.getCenterY() - (8*scale));
		hair.setFill(this.hairColor);
		body.getChildren().add(hair);
		
		body.getChildren().add(head);
		
		leftEye = new Circle();
		leftEye.setRadius(eyeSize);
		leftEye.setCenterX(xLoc + head.getCenterX()-(head.getRadius()*.65));
		leftEye.setCenterY(yLoc + head.getCenterY()-(head.getCenterY()/12)*scale);
		leftEye.setFill(this.eyeColor);
		body.getChildren().add(leftEye);
		
		leftPupal = new Circle();
		leftPupal.setRadius(pupalSize);
		leftPupal.setCenterX(xLoc + head.getCenterX()-(head.getRadius()*.65));
		leftPupal.setCenterY(yLoc + head.getCenterY()-(head.getCenterY()/12)*scale);
		leftPupal.setFill(pupalColor);
		body.getChildren().add(leftPupal);
		
		rightEye = new Circle();
		rightEye.setRadius(eyeSize);
		rightEye.setCenterX(xLoc + head.getCenterX()+(head.getRadius()*.65));
		rightEye.setCenterY(yLoc + head.getCenterY()-(head.getCenterY()/12)*scale);
		rightEye.setFill(this.eyeColor);
		body.getChildren().add(rightEye);
		
		rightPupal = new Circle();
		rightPupal.setRadius(pupalSize);
		rightPupal.setCenterX(xLoc + head.getCenterX()+(head.getRadius()*.65));
		rightPupal.setCenterY(yLoc + head.getCenterY()-(head.getCenterY()/12)*scale);
		rightPupal.setFill(pupalColor);
		body.getChildren().add(rightPupal);
		
		mouth = new Polygon();
		mouth.getPoints().setAll(
				xLoc + head.getCenterX()-(head.getRadius()/3),yLoc +head.getCenterY()+(head.getRadius()/3),
				xLoc + head.getCenterX()-+(head.getRadius()/5),yLoc +head.getCenterY()+(head.getRadius()/2),
				xLoc + head.getCenterX()+(head.getRadius()/5),yLoc +head.getCenterY()+(head.getRadius()/2),
				xLoc + head.getCenterX()+(head.getRadius()/3),yLoc +head.getCenterY()+(head.getRadius()/3)
				);
		mouth.setFill(mouthColor);
		body.getChildren().add(mouth);
	}
	public void setPlayerColors() {
		hairColor = Color.BROWN;
		skinColor =Color.CORNSILK;
		eyeColor = Color.BLUE;
		topColor = Color.BROWN;
		pantsColor = Color.BLUE;
		shoeColor = Color.DARKGREY;
		
	}
	public Circle getLeftHand() {
		return  leftHand;
	}
	public Circle getRightHand() {
		return rightHand;
	}
	
	public void setLeftHandX(double value) {
		this.leftHandX= value;
	}
	public void setRightHandX(double value) {
		this.rightHandX = value;
	}
	
	public Group getBody() {
		return this.body;
	}
	
	public void setXLoc(double value) {
		this.xLoc = value;
	}
	public void setYLoc(double value) {
		this.yLoc = value;
	}
	public void setEntityColor(Color value) {
		this.skinColor =value;
		this.pantsColor = value;
		this.topColor = value;
		this.shoeColor = value;
		Random rand = new Random();
		int red = rand.nextInt(255);
		int green = rand.nextInt(255);
		int blue = rand.nextInt(255);
		this.hairColor = Color.rgb(red, green, blue);
		
	}
	private void createTorsoAndHips(double hipUpperY) {
		Polygon hips;

		torso = new Polygon();
		torso.getPoints().setAll(
				xLoc + rightShoulderX 	,yLoc + shoulderY ,
				xLoc + baseRightHipX	,yLoc + hipUpperY,
				xLoc + baseLeftHipX		,yLoc + hipUpperY,
				xLoc + leftShoulderX	,yLoc +shoulderY );
		torso.setFill(topColor);
		body.getChildren().add(torso);
		
		double waistToHipY = hipUpperY +10*scale ;
		double hipSizeY = waistToHipY +10 *scale;
		hipToLegY = hipSizeY +10*scale;
		
		hips = new Polygon();
		hips.getPoints().setAll(
				xLoc + baseRightHipX 					, yLoc + hipUpperY,
				xLoc + baseRightHipX+(hipSize*scale) 	, yLoc + waistToHipY ,
				xLoc + baseRightHipX+(hipSize*scale) 	, yLoc + hipSizeY,
				xLoc + baseRightHipX 					, yLoc + hipToLegY ,
				
				xLoc + baseLeftHipX 					, yLoc + hipToLegY ,
				xLoc + baseLeftHipX-(hipSize*scale) 	, yLoc + hipSizeY,
				xLoc + baseLeftHipX-(hipSize*scale) 	, yLoc + waistToHipY ,
				xLoc + baseLeftHipX						, yLoc + hipUpperY );
		hips.setFill(pantsColor);
		body.getChildren().add(hips);
	}
	private void createLegs() {
		
		kneeY = (hipToLegY + upperLegLength);
		double footY = (kneeY + lowerLegLength);
		
		leftKneeX = baseLeftHipX -gape;
		rightKneeX = baseRightHipX + gape;
		
		double leftFootX = leftKneeX +(gape*2);
		double rightFootX = rightKneeX -(gape*2);		


		Line rightLegUpper= new Line();
		rightLegUpper = new Line();
		rightLegUpper.setStartX(baseRightHipX);
		rightLegUpper.setStartY(hipToLegY);
		rightLegUpper.setEndX(rightKneeX);
		rightLegUpper.setEndY(kneeY);
		rightLegUpper.setStrokeWidth(20*scale);
		rightLegUpper.setStroke(this.pantsColor);
		body.getChildren().add(rightLegUpper);
		
		Line leftLegUpper= new Line();
		leftLegUpper = new Line();
		leftLegUpper.setStartX(baseLeftHipX);
		leftLegUpper.setStartY(hipToLegY);
		leftLegUpper.setEndX(leftKneeX);
		leftLegUpper.setEndY(kneeY);
		leftLegUpper.setStrokeWidth(20*scale);
		leftLegUpper.setStroke(this.pantsColor);
		body.getChildren().add(leftLegUpper);
		
		Line LeftLegLower= new Line();
		LeftLegLower = new Line();
		LeftLegLower.setStartX(leftKneeX);
		LeftLegLower.setStartY(kneeY);
		LeftLegLower.setEndX(leftFootX);
		LeftLegLower.setEndY(footY);
		LeftLegLower.setStrokeWidth(15*scale);
		LeftLegLower.setStroke(this.pantsColor);
		
		body.getChildren().add(LeftLegLower);
		
		Line rightLegLower= new Line();
		rightLegLower = new Line();
		rightLegLower.setStartX(rightKneeX);
		rightLegLower.setStartY(kneeY);
		rightLegLower.setEndX(rightFootX);
		rightLegLower.setEndY(footY);
		rightLegLower.setStrokeWidth(15*scale);
		rightLegLower.setStroke(this.pantsColor);
		body.getChildren().add(rightLegLower);
		
		
		Line leftFoot = new Line();
		leftFoot.setStartX(leftFootX);
		leftFoot.setStartY(footY);
		leftFoot.setEndX((leftFootX-footSize));
		leftFoot.setEndY(footY);
		leftFoot.setStrokeWidth(10*scale);
		leftFoot.setStroke(this.shoeColor);
		body.getChildren().add(leftFoot);	
		
		Line rightFoot = new Line();
		rightFoot.setStartX(rightFootX);
		rightFoot.setStartY(footY);
		rightFoot.setEndX((rightFootX+footSize));
		rightFoot.setEndY(footY);
		rightFoot.setStrokeWidth(10*scale);
		rightFoot.setStroke(this.shoeColor);
		body.getChildren().add(rightFoot);	
		
	}
	
	private void createArms() {
		
		Circle leftShoulder;
		Circle rightShoulder;
		Line leftArmUpper;
		Line leftArmLower;
		Line rightArmUpper;
		Line rightArmLower;
		
		double elbowY = (shoulderY + upperArmLength);
		double handY = (elbowY + lowerArmLength);
		leftShoulder = new Circle();
		leftShoulder.setRadius((shoulderSize));
		leftShoulder.setCenterX(leftShoulderX);
		leftShoulder.setCenterY(shoulderY);
		leftShoulder.setFill(this.topColor);
		body.getChildren().add(leftShoulder);
				
		/*
		Rotate leftShoulder = new Rotate();
		leftShoulder.setPivotX(leftShoulderX);
		leftShoulder.setPivotY(shoulderY);
		leftShoulder.setAngle(0);
		*/
				
		leftArmUpper = new Line();
		leftArmUpper.setStartX(leftShoulderX);
		leftArmUpper.setStartY(shoulderY);
		leftArmUpper.setEndX(leftElbowX);
		leftArmUpper.setEndY(elbowY);
		leftArmUpper.setStrokeWidth(15*scale);
		leftArmUpper.setStroke(topColor);
		//leftArmUpper.getTransforms().add(leftShoulder);
		//leftArmUpper.setRotate(45);
		body.getChildren().add(leftArmUpper);
		
		rightShoulder = new Circle();
		rightShoulder.setRadius((shoulderSize*scale));
		rightShoulder.setCenterX(rightShoulderX);
		rightShoulder.setCenterY(shoulderY);
		rightShoulder.setFill(this.topColor);
		body.getChildren().add(rightShoulder);
		
		rightArmUpper = new Line();
		rightArmUpper.setStartX(rightShoulderX);
		rightArmUpper.setStartY(shoulderY);
		rightArmUpper.setEndX(rightElbowX);
		rightArmUpper.setEndY(elbowY);
		rightArmUpper.setStrokeWidth(15*scale);
		rightArmUpper.setStroke(topColor);
		body.getChildren().add(rightArmUpper);
		
		rightArmLower = new Line();
		rightArmLower.setStartX(rightElbowX);
		rightArmLower.setStartY(elbowY);
		rightArmLower.setEndX(rightHandX - handSpacing);
		rightArmLower.setEndY(handY);
		rightArmLower.setStrokeWidth(10*scale);
		rightArmLower.setStroke(topColor);
		
		//rightArmLower.setRotate(+25);
		body.getChildren().add(rightArmLower);
		
		leftArmLower = new Line();
		leftArmLower.setStartX(leftElbowX);
		leftArmLower.setStartY(elbowY);
		leftArmLower.setEndX(leftHandX + handSpacing);
		leftArmLower.setEndY(handY);
		leftArmLower.setStrokeWidth(10*scale);
		leftArmLower.setStroke(topColor);
		body.getChildren().add(leftArmLower);
		
		rightHand = new Circle();
		rightHand.setRadius((handSize));
		rightHand.setCenterX(xLoc + rightHandX - handSpacing);
		rightHand.setCenterY(yLoc + handY);
		rightHand.setFill(this.skinColor);
		body.getChildren().add(rightHand);
		
		leftHand = new Circle();
		leftHand.setRadius((handSize));
		leftHand.setCenterX(xLoc + leftHandX + handSpacing);
		leftHand.setCenterY(yLoc + handY);
		leftHand.setFill(this.skinColor);
		body.getChildren().add(leftHand);
		
	}

	public void setSkinColor(Color value) {
		this.skinColor = value;
	}
	public double getxLoc() {
		return xLoc;
	}
	public void setxLoc(double xLoc) {
		this.xLoc = xLoc;
	}
	public double getyLoc() {
		return yLoc;
	}
	public void setyLoc(double yLoc) {
		this.yLoc = yLoc;
	}
	
	public void setBodyLength(double bodyLength) {
		this.bodyLength = bodyLength;
	}
	
	public void setElbowPosture(double elbowPosture) {
		this.elbowPosture = elbowPosture;
	}
	
	public void setHipGape(double hipGape) {
		this.hipGape = hipGape;
	}
	
	public void setHandSpacing(double handSpacing) {
		this.handSpacing = handSpacing;
	}
	
	public void setGape(double gape) {
		this.gape = gape;
	}
	public double getRightHandX() {
		return rightHand.getCenterX();
	}
	public double getRightHandY() {
		return rightHand.getCenterY();
	}
}
