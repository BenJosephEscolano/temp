package main;

import Items.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        //wouldn't it be better if you calculated tile size in the function and not in the parameters
        gp.obj[0] = new Obj_Key(23 * GameScreen.tileSize, 7 * GameScreen.tileSize);
        gp.obj[1] = new Obj_Key(23 * GameScreen.tileSize, 40 * GameScreen.tileSize);
        gp.obj[2] = new Obj_Door(10 * GameScreen.tileSize, 11 * GameScreen.tileSize);
        gp.obj[3] = new Obj_Chest(10 * GameScreen.tileSize, 7 * GameScreen.tileSize);
        gp.obj[4] = new Obj_Key(38 * GameScreen.tileSize, 8 * GameScreen.tileSize);
        gp.obj[5] = new Obj_Door( 8 * GameScreen.tileSize, 28 * GameScreen.tileSize);
        gp.obj[6] = new Obj_Boots(37 * GameScreen.tileSize, 42 * GameScreen.tileSize);
    }
}
