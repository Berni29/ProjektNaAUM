import java.awt.*;

public interface Interactable {

    int getKind();
    Point getPosition();
    boolean check();
    void action();
    int getEnergy();

}
