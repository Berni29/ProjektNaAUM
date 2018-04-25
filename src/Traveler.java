import java.awt.*;

public class Traveler implements Interactable {
    public static final int KIND = 4;
    private int energy = 50;
    private Point position;
    private Cell[][] map;

    public Traveler(Point position, Cell[][] map) {
        this.position = position;
        this.map = map;
    }

    @Override
    public int getKind() {
        return KIND;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public boolean check() {
        return false;
    }

    @Override
    public void action() {

    }

    public void refresh(){
        energy = 50;
    }

}
