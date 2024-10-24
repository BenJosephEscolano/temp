package Items;

import main.Coords;
import main.GamePanel;
import main.GameScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObjects {
    public BufferedImage image;
    public String name;
    public boolean collision;
    public int worldX, worldY;
    public Rectangle collisionArea;
    public Coords collisionBox;
    public GameObjects(String name){
        this.name = name;
        collision = false;
        collisionArea = new Rectangle(0,0, GameScreen.tileSize, GameScreen.tileSize);
        collisionBox = new Coords(0,0);
        worldX = 0;
        worldY = 0;
    }
    public GameObjects(String name, int worldX, int worldY){
        this.name = name;
        collision = false;
        collisionArea = new Rectangle(0,0, GameScreen.tileSize, GameScreen.tileSize);
        collisionBox = new Coords(0,0);
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void draw(Graphics2D g, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.getScreenX();
        int screenY = worldY - gp.player.worldY + gp.player.getScreenY();
        // change the implementation of the camera
        if (worldX + GameScreen.tileSize > gp.player.worldX - gp.player.getScreenX() &&
            worldX - GameScreen.tileSize < gp.player.worldX + gp.player.getScreenX() &&
            worldY + GameScreen.tileSize > gp.player.worldY - gp.player.getScreenY() &&
            worldY - GameScreen.tileSize < gp.player.worldY + gp.player.getScreenY()){
            g.drawImage(image, screenX, screenY, GameScreen.tileSize, GameScreen.tileSize, null);
        }

    }
}
