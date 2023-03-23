package Items;

import javafx.scene.paint.Color;

public class Quality {
	
	private String description;
	private int modifer;
	private Color baseColor;
	
	QualityEnum quality;
	public Quality(QualityEnum quality) {
		this.quality = quality;
		switch(quality){
		case POOR:
			this.description = "old and worn";
			this.modifer = -1;
			this.baseColor = Color.BROWN;
			break;
		case COMMON:
			this.description = "well crafted";
			this.modifer = 0;
			this.baseColor = Color.LIGHTBLUE;
			break;
		case IMPROVED:
			this.description = "finely crafted";
			this.modifer = 1;
			this.baseColor = Color.BLUEVIOLET;
			break;
		}
	}

	public Color getBaseColor() {
		return this.baseColor;
	}
	public QualityEnum getQuality() {
		return quality;
	}

	public void setQuality(QualityEnum quality) {
		this.quality = quality;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getModifer() {
		return modifer;
	}

	public void setModifer(int modifer) {
		this.modifer = modifer;
	}

	public String toString() {
		return this.description;
	}
}
