import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Deployer {

    private final int maxStrongPredators;
    private final int maxWeakPredators;
    private final int maxFood;
    private final int maxTravelers;
    private final Random rng = new Random();
    private final int width;
    private final int heigth;
    private final WorldMap wm;
    private final Cell[][] world;
    private int strongPredators = 0;
    private int weakPredators = 0;
    private int food = 0;
    private int travelers = 0;
    private ArrayList<Point> strongXY = new ArrayList<>();
    private ArrayList<Point> weakXY = new ArrayList<>();
    private ArrayList<Point> foodXY = new ArrayList<>();
    private ArrayList<QLearningTraveler> oldTravelers = new ArrayList<>();

    public Deployer(WorldMap wm, Cell[][] world, int maxStrongPredators, int maxWeakPredators, int maxFood, int maxTravelers) {
        this.wm = wm;
        width = world.length;
        heigth = world[0].length;
        this.world = world;
        this.maxStrongPredators = maxStrongPredators;
        this.maxWeakPredators = maxWeakPredators;
        this.maxFood = maxFood;
        this.maxTravelers = maxTravelers;
    }

    public void reset() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                world[i][j].setObject(null);
            }
        }
        for(Point XY : strongXY) {
            wm.addObject(new StrongPredator(XY,wm.getWorld()));
        }
        for(Point XY : weakXY) {
            wm.addObject(new WeakPredator(XY,wm.getWorld()));
        }
        for(Point XY : foodXY) {
            wm.addObject(new Food(XY,wm.getWorld()));
        }
        int x,y,i=0;
        while(i<oldTravelers.size()){
            x = rng.nextInt(width);
            y = rng.nextInt(heigth);
            if(world[x][y].getObject()==null){
                QLearningTraveler trav = oldTravelers.get(i);
//                trav.updateMap();
                trav.refresh();
                trav.setPosition(new Point(x,y));
                wm.addObject(trav);
                i++;
            }

        }

    }

    public void deploy() {
        int x, y, b;
        while(strongPredators<maxStrongPredators) {
            x = rng.nextInt(width);
            y = rng.nextInt(heigth);
            b = world[x][y].getBiom();
            if(world[x][y].getObject()==null) {
                switch (b) {
                    case Cell.FOREST:
                        if (rng.nextInt(100) > 30) {
                            Point XY = new Point(x,y);
                            wm.addObject(new StrongPredator(XY,wm.getWorld()));
                            strongXY.add(XY);
                            strongPredators++;
                        }
                        break;
                    case Cell.DESERT:
                        if (rng.nextInt(100) > 80) {
                            Point XY = new Point(x,y);
                            wm.addObject(new StrongPredator(XY,wm.getWorld()));
                            strongXY.add(XY);
                            strongPredators++;
                        }
                        break;
                    case Cell.MOUNTAIN:
                        if (rng.nextInt(100) > 70) {
                            Point XY = new Point(x,y);
                            wm.addObject(new StrongPredator(XY,wm.getWorld()));
                            strongXY.add(XY);
                            strongPredators++;
                        }
                        break;
                    case Cell.SWAMP:
                        if (rng.nextInt(100) > 20) {
                            Point XY = new Point(x,y);
                            wm.addObject(new StrongPredator(XY,wm.getWorld()));
                            strongXY.add(XY);
                            strongPredators++;
                        }
                        break;
                }
            }
        }
        while(weakPredators<maxWeakPredators) {
            x = rng.nextInt(width);
            y = rng.nextInt(heigth);
            b = world[x][y].getBiom();
            if(world[x][y].getObject()==null) {
                switch (b) {
                    case Cell.FOREST:
                        if (rng.nextInt(100) > 30) {
                            Point XY = new Point(x,y);
                            wm.addObject(new WeakPredator(XY,wm.getWorld()));
                            weakXY.add(XY);
                            weakPredators++;
                        }
                        break;
                    case Cell.DESERT:
                        if (rng.nextInt(100) > 80) {
                            Point XY = new Point(x,y);
                            wm.addObject(new WeakPredator(XY,wm.getWorld()));
                            weakXY.add(XY);
                            weakPredators++;
                        }
                        break;
                    case Cell.MOUNTAIN:
                        if (rng.nextInt(100) > 70) {
                            Point XY = new Point(x,y);
                            wm.addObject(new WeakPredator(XY,wm.getWorld()));
                            weakXY.add(XY);
                            weakPredators++;
                        }
                        break;
                    case Cell.SWAMP:
                        if (rng.nextInt(100) > 20) {
                            Point XY = new Point(x,y);
                            wm.addObject(new WeakPredator(XY,wm.getWorld()));
                            weakXY.add(XY);
                            weakPredators++;
                        }
                        break;
                }
            }
        }
        while(food<maxFood) {
            x = rng.nextInt(width);
            y = rng.nextInt(heigth);
            b = world[x][y].getBiom();
            if (world[x][y].getObject()==null) {
                switch (b) {
                    case Cell.FOREST:
                        if (rng.nextInt(100) > 30) {
                            Point XY = new Point(x,y);
                            wm.addObject(new Food(XY,wm.getWorld()));
                            foodXY.add(XY);
                            food++;
                        }
                        break;
                    case Cell.DESERT:
                        if (rng.nextInt(100) > 80) {
                            Point XY = new Point(x,y);
                            wm.addObject(new Food(XY,wm.getWorld()));
                            foodXY.add(XY);
                            food++;
                        }
                        break;
                    case Cell.MOUNTAIN:
                        if (rng.nextInt(100) > 70) {
                            Point XY = new Point(x,y);
                            wm.addObject(new Food(XY,wm.getWorld()));
                            foodXY.add(XY);
                            food++;
                        }
                        break;
                    case Cell.SWAMP:
                        if (rng.nextInt(100) > 70) {
                            Point XY = new Point(x,y);
                            wm.addObject(new Food(XY,wm.getWorld()));
                            foodXY.add(XY);
                            food++;
                        }
                        break;
                }
            }
        }
        while(travelers<maxTravelers) {
            x = rng.nextInt(width);
            y = rng.nextInt(heigth);
            if (world[x][y].getObject()==null){
                Point XY = new Point(x,y);
                QLearningTraveler trav = new QLearningTraveler(XY,wm.getWorld());
                wm.addObject(trav);
                oldTravelers.add(trav);
                travelers++;
            }
        }
        wm.addTravelers(oldTravelers);
    }

}
