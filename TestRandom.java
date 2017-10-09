//import java.awt.AlphaComposite;
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.Point;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//import java.awt.image.BufferStrategy;
//import java.util.Random;
//
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//
//public class TestRandom extends JFrame implements Runnable, KeyListener,
//MouseListener, MouseMotionListener{
//
//	/*
//	 * 
//	 * Sails of Discovery is an original game concept by Alessio Susi
//	 * 
//	 * */
//	
//
//	Image currentTile;
//	
//	private static int grid_x, grid_y;
//	private BufferStrategy strategy;
//	
//	 /*Ocean is a grid of 6*8 tiles*/
//	
//	static Tile [][] allTiles = new Tile[8][6];
//	
//	private static int cellSize = 10; /*Each cell is (cellSize * cellSize) pixels in area*/
//	private static int cellArea = cellSize * cellSize;
//	
//	Ship player = new Ship();
//	
//	public static void main (String args[]){
//		
//		Ocean oc = new Ocean();
//		
//		allTiles = oc.setTiles(allTiles);
//		
//		SailsOfDiscovery SoD = new SailsOfDiscovery(cellSize);
//	}
//	
//	public TestRandom(int size) {
//		
//		grid_x = cellArea * 8;	grid_y = cellArea * 6;
//		
//		Thread t = new Thread(this);
//		t.start();
//		
//		setBounds(50, 50, grid_x+50 + 40, grid_y+50 + 60);
//		setTitle("Sails of Discovery");
//		setVisible(true);
//		createBufferStrategy(2);
//		strategy = getBufferStrategy();
//		
//		addKeyListener(this);
//	}
//	
//	public void run() {
//		
//		while (true) {
//
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//			}
//
//			this.repaint();
//
//		}
//		
//	}
//	
//	public void paint(Graphics g) {
//			
//			int bufferGrid = 80;
//			
//			g = strategy.getDrawGraphics();
//			g.clearRect(0,0, getWidth(),getHeight());
//
//			Graphics2D g2d = (Graphics2D)g;
//			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0));
//			
//			
//			for (int i=0;i<8;i++) {
//				
//				for (int j=0;j<6;j++){
//					
//					currentTile = allTiles[i][j].tileImage;
//					
//				    g2d.drawImage(currentTile, (i*cellArea)+bufferGrid , (j*cellArea)+bufferGrid+10 ,cellArea,cellArea, null);	
//				    
//				    if (allTiles[i][j].isHazard) g2d.setColor(Color.WHITE);
//				    else  g2d.setColor(Color.BLACK);
//				    
//				    g2d.drawString(allTiles[i][j].toString(), (i*cellArea)+bufferGrid+10 , (j*cellArea)+bufferGrid+30);
//				    g2d.drawString("Cell#: " + i + "," + j, (i*cellArea)+bufferGrid+10 , (j*cellArea)+bufferGrid+45);
//				    
//				    if (i == player.getLocation().getX() & j == player.getLocation().getY() ) {
//				    	if (allTiles[i][j].isIsle) player.setFood(player.getMaxFood());
//				    }
//				}
//			}
//			
//			
//			
//
//			Point shipLocation = player.getLocation();
//			g2d.setColor(Color.BLACK);
//			g2d.drawString("Ship", (shipLocation.x*cellArea)+bufferGrid+50 , (shipLocation.y*cellArea)+bufferGrid+80);
//			g2d.drawString("Food: " + player.getFood() + "               Ships: " + player.getShips(), 50 , 80);
//
//			g.dispose();
//			strategy.show();
//	}
//
//	@Override
//	public void mouseDragged(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyPressed(KeyEvent arg0) {
//		String key = arg0.getKeyText(arg0.getKeyCode());
//		
//		player.move(key);
//	}
//
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyTyped(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	
//}
