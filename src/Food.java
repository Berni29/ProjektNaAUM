import java.awt.*;

public class Food implements Displayable {

    private Point p;

    @Override
    public Point getPosition() {
        return p;
    }

    @Override
    public Color getKind() {
        return Color.YELLOW;
    }

    public Food(int x, int y){
        p = new Point();
        p.x = x;
        p.y = y;
    }
}
