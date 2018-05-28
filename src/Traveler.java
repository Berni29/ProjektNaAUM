import java.awt.*;
import java.util.*;

public class Traveler implements Interactable {
    public static final int KIND = 4;
    private int energy = 100;
    private Point position;
    private Cell[][] map;
    private int[][] oldKnowledgeMap;
    private int[][] knowledgeMap;
    private HashMap<Integer,Integer> track = new HashMap<>();
    private Random dice = new Random();
    private boolean alive = true;
    private int knowledgeRatio = 0;
    private int timeAlive = 0;
    private Point wentFrom;

    public Traveler(Point position, Cell[][] map) {
        this.position = position;
        wentFrom = position;
        this.map = map;
        knowledgeMap = new int[map.length][map[0].length];
        oldKnowledgeMap = new int[map.length][map[0].length];
        for (int i = 0; i < knowledgeMap.length; i++) {
            for (int j = 0; j < knowledgeMap[i].length; j++) {
                knowledgeMap[i][j]=0;
                oldKnowledgeMap[i][j]=0;
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
                learn(timeAlive);
                alive = false;
            }
            return false;
        }
    }

    @Override
    public void action() {
        timeAlive++;
        if(energy>0) {
            track.put(position.x,position.y);
            int luck = dice.nextInt(100);
            Point way;
            if (luck > knowledgeRatio) {
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
                        track.put(way.x,way.y);
                        break;
                    case StrongPredator.KIND:
                        StrongPredator strPred = (StrongPredator) something;
                        energy -= strPred.attack(energy);
                        track.put(way.x,way.y);
                        knowledgeMap[way.x][way.y] -= 2;
                        break;
                    case WeakPredator.KIND:
                        WeakPredator weakPred = (WeakPredator) something;
                        energy -= weakPred.attack(energy);
                        track.put(way.x,way.y);
                        knowledgeMap[way.x][way.y] -= 1;
                        break;
                    case Food.KIND:
                        Food food = (Food) something;
                        energy += food.takeFood();
                        track.put(way.x,way.y);
                        knowledgeMap[way.x][way.y] += 1;
                }
            } else {
                wentFrom = position;
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
        timeAlive = 0;
    }

    private Point exploration(Point pos){
        ArrayList<Point> possibleRoute = new ArrayList<>();
        Point way;
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
        Point point = null;
        if(!possibleRoute.isEmpty()) {
            for (Point p : possibleRoute) {
                if (p.x == wentFrom.x && p.y == wentFrom.y) {
                    point = p;
                }
            }
        }
        if(point != null) {
            possibleRoute.remove(point);
        }
        if(possibleRoute.isEmpty()){
            return pos;
        }
        int i = dice.nextInt(possibleRoute.size());
        way = possibleRoute.get(i);
        return way;
    }

    private int[][] shareKnowledge(int[][] knowledgeMap){
        for(int i = 0; i < knowledgeMap.length; i++){
            for(int j = 0; j < knowledgeMap[i].length; j++){
                this.oldKnowledgeMap[i][j] += knowledgeMap[i][j];
            }
        }
        return this.oldKnowledgeMap;
    }

    private void learn(int reward){
        int val = reward;
        Point p;
        Set entrySet = track.entrySet();
        Iterator<Map.Entry<Integer,Integer>> iterator = entrySet.iterator();
        LinkedList<Point> points = new LinkedList<>();
        while(iterator.hasNext()){
            Map.Entry<Integer,Integer> entry = iterator.next();
            points.push(new Point(entry.getKey(),entry.getValue()));
        }
        entrySet.clear();
        while(points.size()>0){
            p = points.pop();
            knowledgeMap[p.x][p.y] += val;
            val -= 1;
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
        Point point = null;
        if(!possibleRoute.isEmpty()) {
            for (Point p : possibleRoute) {
                if (p.x == wentFrom.x && p.y == wentFrom.y) {
                    point = p;
                }
            }
        }
        if(point != null) {
            possibleRoute.remove(point);
        }
        if(possibleRoute.isEmpty()) {
            return pos;
        }
        if(possibleRoute.size()==1){
            return possibleRoute.get(0);
        }
        double highest = oldKnowledgeMap[possibleRoute.get(0).x][possibleRoute.get(0).y];
        int index = 0;
        for (int i = 0; i < possibleRoute.size(); i++) {
            Point p = possibleRoute.get(i);
            if(oldKnowledgeMap[p.x][p.y]>highest){
                highest = oldKnowledgeMap[p.x][p.y];
                index = i;
            }
        }
            return possibleRoute.get(index);
    }
    public void updateMap(){
        for (int j = 0; j < knowledgeMap[0].length; j++) {
            for (int i = 0; i < knowledgeMap.length; i++){
                System.out.print(knowledgeMap[i][j]+"\t");
                oldKnowledgeMap[i][j]=knowledgeMap[i][j];
            }
            System.out.println();
        }
        System.out.println(knowledgeRatio);
        knowledgeRatio++;
    }
}
