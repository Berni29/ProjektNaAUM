import java.awt.*;

public class Food implements Displayable {

    private Point position;

    public Food(){ position = Deployer.getFoodPosition(); }
    public Food(Point position){ this.position = position; }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Color getKind() {
        return Color.ORANGE;
    }

}
