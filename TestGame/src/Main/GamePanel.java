package Main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    JFrame window  = new JFrame();

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //16x3=48 actual tile size= 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize; //1152
    public final int screenHeight = maxScreenRow * tileSize; //960

    //FPS
    int FPS=60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;//keeps the program running , used to repeat processes, to use add implements runnable
    Player player = new Player(this,keyHandler);
    TileManager tileM = new TileManager(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);//game panel is focused to receive key input
    }

    public void startGameThread(){
        gameThread = new Thread(this); //parsing gamepanel to the constructor
        gameThread.start();
    }

    @Override
    public void run() { //creating a game loop

        double drawInterval = 1000000000/FPS; //0.01666 SECONDS
        double delta=0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer=0;
        long drawCount=0;

        while(gameThread !=null){

            currentTime = System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            timer += (currentTime-lastTime);
            lastTime = currentTime;


            if(delta >=1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: "+drawCount);
                drawCount=0;
                timer=0;
            }

        }
    }

    public void update(){
        player.update();
    }
    // graphics is a class used to draw objects on screen
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        player.draw(g2);


        g2.dispose(); //dispose of this graphic contest and release any system source that is using
    }

}
