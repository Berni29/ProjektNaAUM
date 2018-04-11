import java.awt.*;

public class WeakPredator implements Predator,Displayable {

    private Point position;
    private boolean alive = true;

    public WeakPredator(int x, int y){
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
        return Color.ORANGE;
    }

    @Override
    public int getPower() {
        return 10;
    }

    @Override
    public void attack() {

    }
}
