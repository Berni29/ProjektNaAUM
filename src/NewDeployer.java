import java.awt.*;
import java.util.Random;

public class NewDeployer {

    private final int maxStrongPredators;
    private final int maxWeakPredators;
    private final int maxFood;
    private final Random rng = new Random();
    private final int width;
    private final int heigth;
    private final WorldMap wm;
    private final Cell[][] world;
    private int strongPredators = 0;
    private int weakPredators = 0;
    private int food = 0;

    public NewDeployer(WorldMap wm, Cell[][] world, int maxStrongPredators, int maxWeakPredators, int maxFood) {
        this.wm = wm;
        width = world.length;
        heigth = world[0].length;
        this.world = world;
        this.maxStrongPredators = maxStrongPredators;
        this.maxWeakPredators = maxWeakPredators;
        this.maxFood = maxFood;
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
                            wm.addObject(new StrongPredator(new Point(x,y)));
                            strongPredators++;
                        }
                        break;
                    case Cell.DESERT:
                        if (rng.nextInt(100) > 80) {
                            wm.addObject(new StrongPredator(new Point(x,y)));
                            strongPredators++;
                        }
                        break;
                    case Cell.MOUNTAIN:
                        if (rng.nextInt(100) > 70) {
                            wm.addObject(new StrongPredator(new Point(x,y)));
                            strongPredators++;
                        }
                        break;
                    case Cell.SWAMP:
                        if (rng.nextInt(100) > 20) {
                            wm.addObject(new StrongPredator(new Point(x,y)));
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
                            wm.addObject(new WeakPredator(new Point(x,y)));
                            weakPredators++;
                        }
                        break;
                    case Cell.DESERT:
                        if (rng.nextInt(100) > 80) {
                            wm.addObject(new WeakPredator(new Point(x,y)));
                            weakPredators++;
                        }
                        break;
                    case Cell.MOUNTAIN:
                        if (rng.nextInt(100) > 70) {
                            wm.addObject(new WeakPredator(new Point(x,y)));
                            weakPredators++;
                        }
                        break;
                    case Cell.SWAMP:
                        if (rng.nextInt(100) > 20) {
                            wm.addObject(new WeakPredator(new Point(x,y)));
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
                            wm.addObject(new Food(new Point(x,y)));
                            food++;
                        }
                        break;
                    case Cell.DESERT:
                        if (rng.nextInt(100) > 80) {
                            wm.addObject(new Food(new Point(x,y)));
                            food++;
                        }
                        break;
                    case Cell.MOUNTAIN:
                        if (rng.nextInt(100) > 70) {
                            wm.addObject(new Food(new Point(x,y)));
                            food++;
                        }
                        break;
                    case Cell.SWAMP:
                        if (rng.nextInt(100) > 70) {
                            wm.addObject(new Food(new Point(x,y)));
                            food++;
                        }
                        break;
                }
            }
        }
    }

}
