package Interface;
import java.io.Serializable;
public class Path implements Serializable {
    public String path;
    public int id;
    public Path(int id,String path) {
        this.id = id;
        this.path = path;
    }
}