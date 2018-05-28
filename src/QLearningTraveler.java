import java.awt.*;

public class QLearningTraveler implements Interactable {

    private int energy = 50;
    private Point position;
    private Cell[][] map;
    private double r[][];
    private double q[][];
    private double gamma = 0.8;
    private boolean alive = true;

    public QLearningTraveler(Point position, Cell[][] map) {
        this.position = position;
        this.map = map;
        int states = map.length * map[0].length;
        r = new double[states][states];
        q = new double[states][states];
        for (int i = 0; i < states ; i++) {
            for (int j = 0; j < states; j++) {
                q[i][j] = 0;
                r[i][j] = -1;
            }
        }
        int length = map.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(map[i][j].getObject()!=null && map[i][j].getObject().getKind()==Food.KIND){
                    possibleActions(i,j,100);
                } else if (map[i][j].getObject()!=null && map[i][j].getObject().getKind()==StrongPredator.KIND) {
                    possibleActions(i,j,-100);
                } else if (map[i][j].getObject()!=null && map[i][j].getObject().getKind()==WeakPredator.KIND) {
                    possibleActions(i,j,-50);
                }
                else {
                    possibleActions(i,j,0);
                }
            }
        }
    }

    private void possibleActions(int x, int y, int value) {
        r[(20*y)+x][(20*y)+x] = value;
        if (!map[x][y].isTop()) {
            r[(20 * y) + x][(20 * (y - 1)) + x] = value;
        }
        if (!map[x][y].isRight()) {
            r[(20 * y) + x][(20 * y) + (x + 1)] = value;
        }
        if (!map[x][y].isBottom()) {
            r[(20 * y) + x][(20 * (y + 1)) + x] = value;
        }
        if (!map[x][y].isLeft()) {
            r[(20 * y) + x][(20 * y) + (x - 1)] = value;
        }
    }

    @Override
    public int getKind() {
        return 4;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public boolean check() {
        if(energy>0) {
            return true;
        }
        else {
            if(alive) {
                alive = false;
            }
            return false;
        }
    }

    @Override
    public void action() {
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    public boolean isAlive(){
        return alive;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
