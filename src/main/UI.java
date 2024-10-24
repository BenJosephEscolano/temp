package main;

import Items.Obj_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    public boolean gameFinished = false;
    Font arial_40;
    BufferedImage keyImage;
    GamePanel gp;
    String message;
    boolean messageOn = false;
    int messageCounter = 0;

    public UI(GamePanel gp){
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        Obj_Key key = new Obj_Key();
        keyImage = key.image;
        this.gp = gp;

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, GameScreen.tileSize/2, GameScreen.tileSize/2, GameScreen.tileSize, GameScreen.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 74, 65);

        if (messageOn){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, GameScreen.tileSize/2, GameScreen.tileSize*5);

            messageCounter++;
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
