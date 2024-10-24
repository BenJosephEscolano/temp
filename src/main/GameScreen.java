package main;

public interface GameScreen {
    int originalTileSize = 16;
    int scale = 3;
    int tileSize = originalTileSize * scale;
    int maxScreenCol = 16;
    int maxScreenRow = 12;
    int screenWidth = tileSize * maxScreenCol;
    int screenHeight = tileSize * maxScreenRow;
    int maxWorldCol = 50;
    int maxWorldRow = 50;
}
