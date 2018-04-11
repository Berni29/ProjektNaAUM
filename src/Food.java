import java.awt.*;

public class Food implements Displayable {

    private Point position;

    public Food(int x, int y){
        position = new Point();
        position.x = x;
        position.y = y;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Color getKind() {
        return Color.YELLOW;
    }

}
