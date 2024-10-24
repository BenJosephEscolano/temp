package Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Chest extends GameObjects{
    public Obj_Chest(int worldX, int worldY){
        super("Chest", worldX, worldY);
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest (OLD).png")));
        } catch(IOException e){
            System.out.println("image does not exist");
        }
        collision = true;
    }
}
