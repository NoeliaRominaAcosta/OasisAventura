
package oasisaventuras;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    //FPS
    int FPS =60;
    
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    
    //set players default position
    
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        //and call automaticaly run method
    }
    
//    @Override
//    public void run() {
//        double drawInterval = 1000000000/FPS;
//        //1 second divive 60
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        while(gameThread != null){
//            // 1 update information such as character positions
//            update();
//            
//            //2 draw screen with the updated info
//            
//            repaint();
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//                //convert to miliseconds
//                
//                if(remainingTime < 0){
//                remainingTime = 0;
//                }
//                Thread.sleep((long)remainingTime);
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//  modern and easiest way to do this: 
    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >=1){
            update();
            repaint();
            delta--;
            drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
            drawCount = 0;
            timer = 0;
            }
        }
    
    }
   
    
    public void update(){
        if(keyH.upPressed == true){
            playerY -= playerSpeed;
          
        }else if(keyH.downPressed == true){
            playerY += playerSpeed;
        }else if(keyH.leftPressed){
        playerX -= playerSpeed;
        }else if(keyH.rightPressed ==true){
             playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
