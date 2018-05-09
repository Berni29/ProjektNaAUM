import java.awt.*;

public class WeakPredator implements Predator, Interactable {

    public static final int KIND = 2;

    private Point position;
    private boolean alive = true;
    private Cell[][] map;

    public WeakPredator(Point position, Cell[][] map){
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
        return 50;
    }

    @Override
    public int attack(int power) {
        if(power>50){
            alive = false;
            return 25;
        }
        else {
            return 50;
        }
    }
    public boolean check(){
        return alive;
    }

    @Override
    public int getEnergy() {
        return 50;
    }

    @Override
    public void action() {

    }
}
