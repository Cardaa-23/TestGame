package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gp = gamePanel;
        this.keyH = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x= 100;
        y= 100;
        speed=4;
        direction="down";
    }
    public void getPlayerImage(){
        try{
            down1= ImageIO.read(getClass().getResource("/player/tile000.png"));
            down2= ImageIO.read(getClass().getResource("/player/tile001.png"));
            down3= ImageIO.read(getClass().getResource("/player/tile002.png"));
            down4= ImageIO.read(getClass().getResource("/player/tile003.png"));
            left1= ImageIO.read(getClass().getResource("/player/tile004.png"));
            left2= ImageIO.read(getClass().getResource("/player/tile005.png"));
            left3= ImageIO.read(getClass().getResource("/player/tile006.png"));
            left4= ImageIO.read(getClass().getResource("/player/tile007.png"));
            right1= ImageIO.read(getClass().getResource("/player/tile008.png"));
            right2= ImageIO.read(getClass().getResource("/player/tile009.png"));
            right3= ImageIO.read(getClass().getResource("/player/tile010.png"));
            right4= ImageIO.read(getClass().getResource("/player/tile011.png"));
            up1= ImageIO.read(getClass().getResource("/player/tile012.png"));
            up2= ImageIO.read(getClass().getResource("/player/tile013.png"));
            up3= ImageIO.read(getClass().getResource("/player/tile014.png"));
            up4= ImageIO.read(getClass().getResource("/player/tile015.png"));



        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed){
                direction = "up";
                y -= speed;
            }
            else if(keyH.downPressed){
                direction = "down";
                y += speed;
            }
            else if(keyH.leftPressed){
                direction = "left";
                x -= speed;
            }
            else if(keyH.rightPressed){
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum ==1){
                    spriteNum=2;
                }
                else if(spriteNum ==2){
                    spriteNum=3;
                }
                else if(spriteNum ==3){
                    spriteNum=4;
                }
                else if(spriteNum ==4){
                    spriteNum=1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                if(spriteNum == 3){
                    image = up3;
                }
                if(spriteNum == 4){
                    image = up4;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                if(spriteNum == 3){
                    image = down3;
                }
                if(spriteNum == 4){
                    image = down4;
                }
                break;
            case "left":

                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                if(spriteNum == 3){
                    image = left3;
                }
                if(spriteNum == 4){
                    image = left4;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                if(spriteNum == 3){
                    image = right3;
                }
                if(spriteNum == 4){
                    image = right4;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);


    }
}
