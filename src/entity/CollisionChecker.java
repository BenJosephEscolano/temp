package entity;

import Items.GameObjects;
import main.GamePanel;
import main.GameScreen;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.hitBox.x;
        int entityRightWorldX = entityLeftWorldX + entity.hitBox.width;
        int entityTopWorldY = entity.worldY + entity.hitBox.y;
        int entityBottomWorldY = entityTopWorldY + entity.hitBox.height;

        int entityLeftCol = entityLeftWorldX/ GameScreen.tileSize;
        int entityRightCol = entityRightWorldX/GameScreen.tileSize;
        int entityTopRow = entityTopWorldY / GameScreen.tileSize;
        int entityBottomRow = entityBottomWorldY/GameScreen.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/GameScreen.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/GameScreen.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/GameScreen.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/GameScreen.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity){
        int index = -1;

        for (int i = 0; i < gp.obj.length; i++){
            GameObjects o = gp.obj[i];
            if (o != null){
                //I hate how world x and world y in entity and object aren't connected at all;
                o.collisionArea.x += o.worldX;
                o.collisionArea.y += o.worldY;
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;
                switch (entity.direction){
                    case "up":
                        entity.hitBox.y -= entity.speed;
                        if (entity.hitBox.intersects(o.collisionArea)){
                            if(o.collision){
                                entity.collisionOn = true;
                            }
                            if (entity instanceof Player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitBox.y += entity.speed;
                        if (entity.hitBox.intersects(o.collisionArea)){
                            if(o.collision){
                                entity.collisionOn = true;
                            }
                            if (entity instanceof Player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.hitBox.x -= entity.speed;
                        if (entity.hitBox.intersects(o.collisionArea)){
                            if(o.collision){
                                entity.collisionOn = true;
                            }
                            if (entity instanceof Player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.hitBox.x += entity.speed;
                        if (entity.hitBox.intersects(o.collisionArea)){
                            if(o.collision){
                                entity.collisionOn = true;
                            }
                            if (entity instanceof Player){
                                index = i;
                            }
                        }
                        break;
                }
                o.collisionArea.x = o.collisionBox.x;
                o.collisionArea.y = o.collisionBox.y;
                entity.hitBox.x = entity.SolidAreaDefault.x;
                entity.hitBox.y = entity.SolidAreaDefault.y;
            }
        }

        return index;
    }

}
