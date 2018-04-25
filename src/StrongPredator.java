import java.awt.*;

public class StrongPredator implements Predator, Interactable {

    public static final int KIND = 3;

    private Point position;
    private boolean alive = true;
    private Cell[][] map;

    public StrongPredator(Point position, Cell[][] map) {
        this.position = position;
        this.map = map;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public int getKind() {
        return KIND;
    }

    @Override
    public int getPower() {
        return 100;
    }

    @Override
    public int attack(int power) {
        if(power>100){
            return 50;
        }
        else {
            return 100;
        }
    }
    public boolean check(){
        return alive;
    }

    @Override
    public void action() {

    }
}
