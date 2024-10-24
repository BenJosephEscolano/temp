package Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Key extends GameObjects{
    public Obj_Key(){
        super("Key");
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        } catch(IOException e){
            System.out.println("image does not exist");
        }
    }
    public Obj_Key(int worldX, int worldY){
        super("Key", worldX, worldY);
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        } catch(IOException e){
            System.out.println("image does not exist");
        }
    }

}
