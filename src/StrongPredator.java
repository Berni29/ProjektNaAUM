import java.awt.*;

public class StrongPredator implements Predator,Displayable {

    private Point position;
    private boolean alive = true;

    public StrongPredator(){ position = Deployer.getPredatorPosition(); }
    public StrongPredator(Point position){ this.position = position; }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Color getKind() {
        return Color.RED;
    }

    @Override
    public int getPower() {
        return 20;
    }

    @Override
    public void attack() {

    }
}
