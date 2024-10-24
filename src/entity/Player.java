package entity;

import Items.Obj_Boots;
import Items.Obj_Chest;
import Items.Obj_Door;
import Items.Obj_Key;
import main.Coords;
import main.GamePanel;
import main.GameScreen;
import main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public int hasKey = 0;
    private int screenY;
    private int screenX;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        hitBox = new Rectangle(10, 16, 28, 32);
        SolidAreaDefault = new Coords(hitBox.x, hitBox.y);
        setDefaultValue();
    }

    public int getScreenX() {
        return screenX;
    }
    public int getScreenY(){
        return  screenY;
    }

    public void setDefaultValue(){
        worldX = GameScreen.tileSize * 23;
        worldY = GameScreen.tileSize * 21;
        speed = 4;
        direction = "down";
        screenY = GameScreen.screenHeight / 2 - (GameScreen.tileSize / 2);
        screenX = GameScreen.screenWidth / 2 - (GameScreen.tileSize / 2);
        getPlayerImage();
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        }catch (IOException e){
            System.out.println("missing image");
        }
    }
    public void update(){
        if (keyH.rightPressed){
            direction = "right";

        } else if (keyH.leftPressed){
            direction = "left";

        } else if(keyH.upPressed){
            direction = "up";

        } else if (keyH.downPressed){
            direction = "down";

        }
        if (keyH.leftPressed || keyH.rightPressed || keyH.upPressed || keyH.downPressed){
            collisionOn = false;
            gp.cChecker.checkTile(this);
            int op = gp.cChecker.checkObject(this);
            objectEvent(op);
            if (!collisionOn){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 10){
                spriteNum *= -1;
                spriteCounter = 0;
            }
        }
    }
    public void objectEvent(int i){
        if (i >= 0){
            if (gp.obj[i] instanceof Obj_Key){
                gp.playSE(1);
                gp.ui.showMessage("You got a key");
                hasKey++;
            }
            if (gp.obj[i] instanceof Obj_Door || gp.obj[i] instanceof Obj_Chest){
                if (hasKey <= 0){
                    gp.ui.showMessage("You need a key");
                    return;
                }
                if (gp.obj[i] instanceof  Obj_Door){
                    gp.ui.showMessage("You opened the door!");
                    gp.playSE(3);
                } else {
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                }

                hasKey--;
            }
            if (gp.obj[i] instanceof Obj_Boots){
                gp.ui.showMessage("Speed up!");
                gp.playSE(2);
                speed += 4;
            }
            gp.obj[i] = null;
        }

    }

    public void draw(Graphics2D g){
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1){
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                } else {
                    image = right2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                } else {
                    image = left2;
                }
                break;
        }
        g.drawImage(image, screenX, screenY, GameScreen.tileSize, GameScreen.tileSize, null);
    }


}
