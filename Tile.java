import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Tile {

	public Image tileImage;
	
	
	ImageIcon islandIcon = new ImageIcon(getClass().getResource("/images/island.png"));
	Image islandImg = islandIcon.getImage();
	ImageIcon hazardIcon = new ImageIcon(getClass().getResource("/images/hazard.png"));
	Image hazardImg = hazardIcon.getImage();
	ImageIcon emptyIcon = new ImageIcon(getClass().getResource("/images/empty.png"));
	Image emptyImg = emptyIcon.getImage();
	
	/*TODO - add fog.png here*/
	ImageIcon fogIcon = new ImageIcon(getClass().getResource("/images/hazard.png"));
	Image fogImg = fogIcon.getImage();
	
	public boolean isIsle = false;
	public boolean isHazard = false;
	public boolean isEmpty = false;
	
	private String tileType = "unSpecified";
	
	public Tile(boolean island, boolean hazard, boolean empty) {
		this.isIsle = island;
		this.isHazard = hazard;
		this.isEmpty = empty;
		
		if (isIsle) {
			tileImage = islandImg;
			tileType = "Island";
		}
		else if (isHazard) {
			tileImage = hazardImg;
			tileType = "Hazard";
		}
		else {
			tileImage = emptyImg;
			tileType = "Empty";
		}
	}

//	public boolean tileOrNoTile(Point nextTile) {
//		
//		boolean isTile = false;
//		
//		for (int i=0;i<8;i++) {
//
//			for (int j=0;j<6;j++){
//				if (i == nextTile.x && j == nextTile.y ) {
//					isTile=true;
//				}
//			}
//		}
//		
//		return isTile;
//	}
	
	public String toString(){
		
		return tileType;
	}
	
	

	


	
}
