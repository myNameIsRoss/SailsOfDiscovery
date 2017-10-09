import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Ship {

	ImageIcon shipIcon = new ImageIcon(getClass().getResource("/images/ship.png"));
	Image shipImg = shipIcon.getImage();
	
	private int maxFood = 5;
	private int food = 5;
	
	public boolean sailingUp = false;
	public boolean beginSailingUp = false;
	public boolean sailingDown = false;
	public boolean sailingLeft = false;
	public boolean sailingRight = false;

	
	public int horiz_mvmnt = 0;
	public int verti_mvmnt = 0;
	
	public int cellArea;

	public int middlePosition = 0;
	public int shipSize = 35;

	private int ships = 3;
	
	private static Point tileLocation;
	private Point nextLocation;
	private Point previousLocation; /*Used for when you reach a hazard and are pushed back.*/
	
	Ship(int cellArea) {
		this.cellArea = cellArea;
		this.tileLocation = new Point(7,3); /*Change to wherver homebase is*/
		this.nextLocation = new Point(7,3); /*Initial*/

		middlePosition = (cellArea/2);
	}
	
	public Point getLocation() {
		return tileLocation;
	}
	
	
	public void move(String direction) {
		
		
		this.nextLocation = new Point(this.tileLocation);
		this.previousLocation = new Point(this.tileLocation);
		
		System.out.println("next: "+nextLocation);
		System.out.println("current: " +tileLocation);
		System.out.println("last: " +tileLocation);


		switch (direction) {
		
		case ("W"):
			sailingUp = true;
			beginSailingUp = true;
			nextLocation.y--;
			break;
	
		case("A"):
			sailingLeft = true;
			nextLocation.x--;
			break;
		
		case("S"):
			sailingDown = true;
			nextLocation.y++;
			break;
			
		case("D"):
			sailingRight = true;
			nextLocation.x++;
			break;
		}
		
	
		if (!canMove(nextLocation)) {
			sailingUp = false;
			sailingDown = false;
			sailingLeft = false;
			sailingRight = false;
		}
		
		else 
		{
			food--;

			this.previousLocation = new Point(this.tileLocation);
			this.tileLocation = this.nextLocation;
		}
				
	}
	
	public void sailing(String direction) {
		System.out.println("sailing");
		
		switch (direction){
			
		case "up":
			verti_mvmnt = cellArea;

			while (verti_mvmnt > 0) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				verti_mvmnt--;
			}
			
			break;
		case "down":
			
		case "right":
			
		case "left":
			
		}
	
		//verti_mvmnt = cellArea;
		
		while (horiz_mvmnt > 0) {
			
		}

	}
	
	public void reduce(int toReduce) {
		while (toReduce > 0) {
			toReduce--;
		}	
	}
	
	public boolean canMove(Point nextTile) {
		
		boolean canMakeMovement = false;
		
		if (food<1) {
			return false;
		}
		
		for (int i=0;i<8;i++) {

			for (int j=0;j<6;j++){
				if (i == nextTile.x && j == nextTile.y ) {
					canMakeMovement=true;
				}
			}
		}
		
		return canMakeMovement;
	}

	public int getShips() {
		return ships;
	}

	public void setShips(int ships) {
		this.ships = ships;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}
	
	public int getMaxFood() {
		return maxFood;
	}

	public boolean InThisTile(int i, int j) {
		if (i == this.getLocation().getX() & j == this.getLocation().getY() ) 
			return true;
		else
			return false;
	}

	public void reachedIsland() {
		// TODO Auto-generated method stub
		
	}

	public void moveBack() {
		// TODO Auto-generated method stub
		
	}

	public int sailing() {
		/*Sailing animation*/
		
		if (sailingUp) {
						
			if (beginSailingUp) {
				verti_mvmnt = cellArea/cellArea;
			}
			
			if (verti_mvmnt > 0) {
				verti_mvmnt--;
				return verti_mvmnt;
			}
			else
			{
				sailingUp = false;
			}
				
			 
		}
		
		return 0;
	}
	
}
