import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SailsOfDiscovery extends JFrame implements Runnable, KeyListener,
MouseListener, MouseMotionListener{

	/*
	 * 
	 * Sails of Discovery is an original game concept by Alessio Susi
	 * 
	 * Programming by Ross Clifford
	 * Game art by Ross Clifford
	 * 
	 * 2017
	 * 
	 * */
	
	private static int grid_x, grid_y;
	private static int tilesAcross = 8;/*Original Sails of Discovery Board Game is a grid of 6*8 tiles*/
	private static int tilesDown = 6;
	private static int tileSize = 8; /*Each cell is (tileSize * tileSize) pixels in area*/
	private static int tilePixelArea = tileSize * tileSize;
	
	static Tile [][] boardTiles = new Tile[tilesAcross][tilesDown];
	
	Image currentTile;
	private BufferStrategy strategy;
	
	static Ship player = new Ship(tilePixelArea);
	static Ocean oc = new Ocean();
	
	boolean debug = false;
	
	public static void main (String args[]){
		
		/*
		 * [setTiles]
		 * The board is populated with ocean tiles, tiles with hazards and tiles which have islands
		 * 
		 * These tiles are randomised, with the exception of the player's starting tile, which is always
		 * On the right side of the board (i.e. it's always on of the last /tilesDown/ tiles in /boardTiles/
		 *  
		 * */
		boardTiles = oc.setTiles(boardTiles, tilesAcross, tilesDown);
		
		SailsOfDiscovery SoD = new SailsOfDiscovery(tileSize);
	}
	
	public SailsOfDiscovery(int size) {
		
		grid_x = tilePixelArea * tilesAcross;	grid_y = tilePixelArea * tilesDown;
		
		Thread t = new Thread(this);
		t.start();
		
		/*TODO - Make this not do stupid*/
		setBounds(50, 50, grid_x + 90, grid_y + 110);
		
		setTitle("Sails of Discovery");
		setVisible(true);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		addKeyListener(this);
	}
	
	public void run() {
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

			this.repaint(); /*keep drawing Images / Text to screen depending on game state*/
		}		
	}
	
	public void paint(Graphics g) {
			
			int bufferGrid = 80; /*Extra distance from corner of window to the tiles grid*/
			
			g = strategy.getDrawGraphics();
			g.clearRect(0,0, getWidth(),getHeight());

			Graphics2D g2d = (Graphics2D)g;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0));
				
			for (int i=0;i<tilesAcross;i++) {
				
				for (int j=0;j<tilesDown;j++){
					
					if (player.InThisTile(i, j)) {
						
					    	if (boardTiles[i][j].isIsle) {
					    		
					    		player.reachedIsland();
					    		player.setFood(player.getMaxFood());
					    	
					    	}
					    	
					    	if (boardTiles[i][j].isHazard) {
					    		player.moveBack();
					    	}
					    	
							currentTile = boardTiles[i][j].tileImage;	/*Always reveal the tile the player is located in*/	
					}
					
					/*TODO: display fog always, except when sailing into new tile, or during memory phase*/
					else {
						
						currentTile = boardTiles[i][j].fogImg;	
					}
					
					if (debug) {
						
						currentTile = boardTiles[i][j].tileImage;	
					}
					
				    g2d.drawImage(currentTile, (i*tilePixelArea)+bufferGrid , (j*tilePixelArea)+bufferGrid+10 ,tilePixelArea,tilePixelArea, null);	
				    
				    if (debug) {
					    if (boardTiles[i][j].isHazard) g2d.setColor(Color.WHITE);
					    else  g2d.setColor(Color.BLACK);
					    
					    g2d.drawString(boardTiles[i][j].toString(), (i*tilePixelArea)+bufferGrid+10 , (j*tilePixelArea)+bufferGrid+30);
					    g2d.drawString("Cell#: " + i + "," + j, (i*tilePixelArea)+bufferGrid+10 , (j*tilePixelArea)+bufferGrid+45);
				    }
				   
				}
			}
			
			Point shipLocation = player.getLocation();
						
			g2d.drawImage(player.shipImg, 
					(shipLocation.x*tilePixelArea)+ bufferGrid+ player.middlePosition - player.sailing(),
					(shipLocation.y*tilePixelArea)+ bufferGrid+ player.middlePosition - player.sailing(), null);
			
			g2d.setColor(Color.BLACK);
			g2d.drawString("Food: " + player.getFood() + "               Ships: " + player.getShips(), 50 , 80);
			g2d.drawString("horiz_mv: " + player.horiz_mvmnt + "               verti_mv: " + player.verti_mvmnt, 50 , 50);

			g.dispose();
			strategy.show();
	}

	public void keyPressed(KeyEvent arg0) {
		
		String key = arg0.getKeyText(arg0.getKeyCode());
		
		if (key.equals("W") || key.equals("A") || key.equals("S") || key.equals("D") ) {
			player.move(key);
		}
		
		if (key.equals("E")) {
			debug = !debug;
		}
		
	}

	public void mouseDragged(MouseEvent arg0) {}

	public void mouseMoved(MouseEvent arg0) {}

	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}
	
	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {}

	
}
