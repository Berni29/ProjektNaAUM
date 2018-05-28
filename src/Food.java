import java.awt.*;

public class Food implements Interactable {

    public final static int KIND = 1;
    private Point position;
    private int energy = 10;
    private Cell[][] map;

    public Food(Point position, Cell[][] map){
        this.position = position;
        this.map = map;
    }

    public int takeFood(){
        if(energy > 0){
            energy -= 10;
            return 10;
        }
        else {
            return 0;
        }
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
    public boolean check() {
        if(energy==0) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void action() {

    }

    @Override
    public int getEnergy() {
        return energy;
    }
}
