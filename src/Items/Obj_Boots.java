package Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;


public class Obj_Boots extends GameObjects{
    public Obj_Boots(int worldX, int worldY){
        super("Boots", worldX, worldY);
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
        } catch(IOException e){
            System.out.println("image does not exist");
        }
    }
}
