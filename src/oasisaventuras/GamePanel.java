
package oasisaventuras;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
    //screen settings
    
    final int originalTileSize = 16; //16 x 16
    final int scale = 3;
    //this scale increments size *3
    final int tileSize = originalTileSize * scale; //48 x 48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768px
    final int screenHeight = tileSize * maxScreenRow; //576 px
    
    Thread gameThread;
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        //and call automaticaly run method
    }
    
    @Override
    public void run() {
        while(gameThread != null){
            // 1 update information such as character positions
            update();
            //2 draw screen with the updated info
            repaint();
        }
    }
    
    public void update(){
    
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose();
    }
}
