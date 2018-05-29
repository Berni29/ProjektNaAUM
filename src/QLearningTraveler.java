import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class QLearningTraveler implements Interactable {

    private int energy = 50;
    private Point position;
    private Cell[][] map;
    private double r[][];
    private double q[][];
    private double gamma = 0.8;
    private Random rng = new Random();
    private boolean alive = true;
    private double confidence = 0;
    private LinkedList<Point> track = new LinkedList<>();
    private LinkedList<Point> visited = new LinkedList<>();

    public QLearningTraveler(Point position, Cell[][] map) {
        this.position = position;
        track.push(position);
        visited.push(position);
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
        if(rng.nextDouble()<confidence){
            exploitation(possibleWays());
        } else {
            exploration(possibleWays());
        }

    }

    private void exploitation(ArrayList<Point> ways){
        int state = position.x + (position.y*20);
        Point way = ways.get(0);
        double max = q[way.x + (way.y*20)][state];
        for(Point p : ways){
            if(max < q[p.x + (p.y*20)][state]){
                max = q[p.x + (p.y*20)][state];
                way = p;
            }
        }
        goTo(way);
    }

    private void move(Point way){
        if(!checkVisited(position)) {
            visited.push(position);
        }
        map[position.x][position.y].setObject(null);
        map[way.x][way.y].setObject(this);
        int state = position.x + (position.y*20);
        int action = way.x + (way.y*20);
        computeQ(state,action);
        position = way;
        track.push(position);
        energy--;
    }

    private void goTo(Point way){
        Interactable someObject = map[way.x][way.y].getObject();
        if(someObject!=null){
            switch (someObject.getKind()) {
                case Food.KIND:
                    Food food = (Food)someObject;
                    energy += food.takeFood();
                    move(way);
                    break;
                case WeakPredator.KIND:
                    WeakPredator weak = (WeakPredator)someObject;
                    energy -= weak.attack(energy);
                    if(alive){
                        move(way);
                    }
                    break;
                case StrongPredator.KIND:
                    StrongPredator strong = (StrongPredator)someObject;
                    energy -= strong.attack(energy);
                    if(alive){
                        move(way);
                    }
                    break;
                default:
                    move(track.pop());
            }
        } else {
            move(way);
        }
    }

    private void exploration(ArrayList<Point> ways){
        int i = rng.nextInt(ways.size());
        Point way = ways.get(i);
        goTo(way);
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
        track.push(position);
        visited.push(position);
    }

    private ArrayList<Point> possibleWays(){
        int x = position.x;
        int y = position.y;
        ArrayList<Point> ways = new ArrayList<>();
        if(!map[x][y].isTop()){
            ways.add(new Point(x,y-1));
        }
        if(!map[x][y].isRight()){
            ways.add(new Point(x+1,y));
        }
        if(!map[x][y].isBottom()){
            ways.add(new Point(x,y+1));
        }
        if(!map[x][y].isLeft()){
            ways.add(new Point(x-1,y));
        }
        deleteVisited(ways);
        if(ways.isEmpty()){
            ways.add(track.pop());
        }
        return ways;
    }

    private void computeQ(int state,int action){
        q[action][state] = r[action][state] + gamma * maxQ(action);
    }

    private double maxQ(int state){
        double max = q[state][0];
        for (int i = 0; i < q[state].length; i++) {
            if(max<q[i][state]){
                max = q[i][state];
            }
        }
        return max;
    }

    public void refresh(){
        alive = true;
        energy = 50;
        confidence += 0.01;
        track.clear();
        visited.clear();
    }

    private boolean checkVisited(Point point){
        for(Point p : visited){
            if(p.x==point.x && p.y==point.y){
                return true;
            }
        }
        return false;
    }

    private void deleteVisited(ArrayList<Point> ways){
        LinkedList<Point> beenBefore = new LinkedList<>();
        for(Point way : ways){
            if(checkVisited(way)){
                beenBefore.add(way);
            }
        }
        for(Point p : beenBefore){
            ways.remove(p);
        }
    }

    public double getConfidence(){
        return confidence;
    }
}
