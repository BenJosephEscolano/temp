package Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Door extends GameObjects{
    public Obj_Door(int worldX, int worldY){
        super("Door", worldX, worldY);
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        } catch(IOException e){
            System.out.println("image does not exist");
        }
        collision = true;
    }
}
