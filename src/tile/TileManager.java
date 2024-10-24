package tile;

import main.GamePanel;
import main.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager extends Tile{
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[GameScreen.maxWorldRow][GameScreen.maxWorldCol];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void loadMap(String map){
        try {
            InputStream is = getClass().getResourceAsStream(map);
            assert is != null;
            BufferedReader br = new BufferedReader((new InputStreamReader(is)));

            for (int row = 0; row < GameScreen.maxWorldRow; row++ ){
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int col = 0; col < GameScreen.maxWorldCol; col++){
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                }
                System.out.println();
            }
            br.close();
        }catch(Exception e){
            System.out.println("Map error");
        }
    }
    //improve getTile image implementation
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g){
        for (int worldRow = 0; worldRow < GameScreen.maxWorldRow; worldRow++){
            for (int worldCol = 0; worldCol < GameScreen.maxWorldCol; worldCol++){
                int worldX = worldCol * GameScreen.tileSize;
                int worldY = worldRow * GameScreen.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.getScreenX();
                int screenY = worldY - gp.player.worldY + gp.player.getScreenY();
                // change the implementation of the camera
                if (worldX + GameScreen.tileSize > gp.player.worldX - gp.player.getScreenX() &&
                    worldX - GameScreen.tileSize < gp.player.worldX + gp.player.getScreenX() &&
                    worldY + GameScreen.tileSize > gp.player.worldY - gp.player.getScreenY() &&
                    worldY - GameScreen.tileSize < gp.player.worldY + gp.player.getScreenY()){
                    g.drawImage(tile[mapTileNum[worldRow][worldCol]].image, screenX, screenY, GameScreen.tileSize, GameScreen.tileSize, null);
                }
            }
        }
    }
}
