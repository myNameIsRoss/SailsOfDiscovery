import java.util.Random;

public class Ocean {

	private static int tilesAmount = 48;
	
	private static int isleFreq = 5;
	private static int hazardFreq = 5;
	
	private static int isEmptyFreq = tilesAmount - isleFreq - hazardFreq;
	
	public Tile[][] setTiles(Tile[][] tiles, int tilesAcross, int tilesDown) {
		
		Random rand = new Random();
		
		/*1:- Fill the first x tiles with island and the first y tiles with hazards*/
		
		for (int i=0;i<tilesAcross;i++) {
			
			for (int j=0;j<tilesDown;j++){
									
				if (isleFreq > 0) {
					tiles[i][j] = new Tile(true, false, false);
					isleFreq--;
				}
				else if (hazardFreq > 0) {
					tiles[i][j] = new Tile(false, true, false);
					hazardFreq--;
				}
				else {//if (hazardFreq == 0 && isleFreq == 0){
					tiles[i][j] = new Tile(false, false, true);
				}
				
			}
		}
		
		/*2:- Randomise the islands and hazards*/
		
		shuffleTiles(tiles);
		
		return tiles;
	}
	
	/*Fisher–Yates algorithm*/
	void shuffleTiles(Tile[][] tiles) {
	    Random random = new Random();
	    
	    for (int i=7; i > 0; i--) {
	    	 for (int j=5; j > 0; j--) {
	 	    	int newPosition_x = random.nextInt(i+1);
	 	    	int newPosition_y = random.nextInt(j+1);
	 	    	
	 	    	Tile temp = tiles[i][j];
	 	    	tiles[i][j] = tiles[newPosition_x][newPosition_y];
	 	    	tiles[newPosition_x][newPosition_y] = temp;
	 	    }
	    }
	}
	
	
	
	
}
