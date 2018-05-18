import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Traveler implements Interactable {
    public static final int KIND = 4;
    private int energy = 100;
    private Point position;
    private Cell[][] map;
    private double[][] knowledgeMap;
    private LinkedList<Point> track = new LinkedList<>();
    private Random dice = new Random();
    private boolean alive = true;

    public Traveler(Point position, Cell[][] map) {
        this.position = position;
        this.map = map;
        knowledgeMap = new double[map.length][map[0].length];
        for (int i = 0; i < knowledgeMap.length; i++) {
            for (int j = 0; j < knowledgeMap[i].length; j++) {
                knowledgeMap[i][j]=0.1;
            }
        }
    }

    public boolean isAlive(){
        return alive;
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
        if(energy>0) {
            return true;
        }
        else {
            if(alive) {
                learn(-1);
                alive = false;
            }
            return false;
        }
    }

    @Override
    public void action() {
        if(energy>0) {
            track.push(position);
            int luck = dice.nextInt(100);
            Point way;
            if (luck > 50) {
                way = exploration(position);
            } else {
                way = exploitation(position);
            }
            Interactable something = map[way.x][way.y].getObject();
            if (something != null) {
                int id = something.getKind();
                switch (id) {
                    case Traveler.KIND:
                        Traveler trav = (Traveler) something;
//                        knowledgeMap = trav.shareKnowledge(knowledgeMap);
                        track.push(way);
                        break;
                    case StrongPredator.KIND:
                        StrongPredator strPred = (StrongPredator) something;
                        energy -= strPred.attack(energy);
                        track.push(way);
                        learn(-1);
                        break;
                    case WeakPredator.KIND:
                        WeakPredator weakPred = (WeakPredator) something;
                        energy -= weakPred.attack(energy);
                        track.push(way);
                        learn(-1);
                        break;
                    case Food.KIND:
                        Food food = (Food) something;
                        energy += food.takeFood();
                        track.push(way);
                        learn(1);
                }
            } else {
                map[position.x][position.y].setObject(null);
                position = way;
                map[position.x][position.y].setObject(this);
            }
            energy--;
        }

    }

    public void refresh(){
        alive = true;
        energy = 50;
    }

    private Point exploration(Point pos){
        ArrayList<Point> possibleRoute = new ArrayList<>();
        Point way = pos;
        if(!map[pos.x][pos.y].isTop()) {
            possibleRoute.add(new Point(pos.x,pos.y-1));
        }
        if(!map[pos.x][pos.y].isRight()) {
            possibleRoute.add(new Point(pos.x+1,pos.y));
        }
        if(!map[pos.x][pos.y].isBottom()) {
            possibleRoute.add(new Point(pos.x,pos.y+1));
        }
        if(!map[pos.x][pos.y].isLeft()) {
            possibleRoute.add(new Point(pos.x-1,pos.y));
        }
        int i = dice.nextInt(possibleRoute.size());
        way = possibleRoute.get(i);
        return way;
    }

    private double[][] shareKnowledge(double[][] knowledgeMap){
        for(int i = 0; i < knowledgeMap.length; i++){
            for(int j = 0; j < knowledgeMap[i].length; j++){
                this.knowledgeMap[i][j] += knowledgeMap[i][j];
            }
        }
        return this.knowledgeMap;
    }

    private void learn(int reward){
        double val = reward;
        Point p;
        while(track.size()>0){
            p = track.pop();
            if(reward==1){
                knowledgeMap[p.x][p.y] += val;
                val -= 0.05;
            }else {
                knowledgeMap[p.x][p.y] += val;
                val += 0.05;
            }
        }
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    private Point exploitation(Point pos){
        ArrayList<Point> possibleRoute = new ArrayList<>();
        if(!map[pos.x][pos.y].isTop()) {
            possibleRoute.add(new Point(pos.x,pos.y-1));
        }
        if(!map[pos.x][pos.y].isRight()) {
            possibleRoute.add(new Point(pos.x+1,pos.y));
        }
        if(!map[pos.x][pos.y].isBottom()) {
            possibleRoute.add(new Point(pos.x,pos.y+1));
        }
        if(!map[pos.x][pos.y].isLeft()) {
            possibleRoute.add(new Point(pos.x-1,pos.y));
        }
        if(possibleRoute.size()==0){
            return pos;
        }
        if(possibleRoute.size()==1){
            return possibleRoute.get(0);
        } else {
            Point beenBefore = track.peek();
            for(Point point : possibleRoute){
                if(point.x==beenBefore.x && point.y==beenBefore.y){
                    beenBefore = point;
                    break;
                }
            }
            possibleRoute.remove(beenBefore);
            double highest = knowledgeMap[possibleRoute.get(0).x][possibleRoute.get(0).y];
            int index = 0;
            for (int i = 0; i < possibleRoute.size(); i++) {
                Point point = possibleRoute.get(i);
                if(knowledgeMap[point.x][point.y]>highest){
                    highest = knowledgeMap[point.x][point.y];
                    index = i;
                }
            }
            return possibleRoute.get(index);
        }
    }
    public void printMap(){
        for (int j = 0; j < knowledgeMap[0].length; j++) {
            for (int i = 0; i < knowledgeMap.length; i++){
                System.out.print(knowledgeMap[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
