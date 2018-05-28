import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class WorldMap extends JComponent {

    private BufferedImage map;
    private Cell[][] world;
    private ArrayList<Interactable> objects = new ArrayList<>();
    private ArrayList<Traveler> travelers = new ArrayList<>();
    private int cellSize;
    private Deployer dp;
    private BufferedImage food;
    private BufferedImage weak;
    private BufferedImage strong;
    private BufferedImage traveler;
    private Thread simulation = null;

    public WorldMap(WorldCreator wc, MapCreator mp, int cellSize) {
        world = wc.getWorld();
        map = mp.getMap();
        this.cellSize = cellSize;
        try{
            food = ImageIO.read(WorldMap.class.getResource("food.png"));
            weak = ImageIO.read(WorldMap.class.getResource("weak.png"));
            strong = ImageIO.read(WorldMap.class.getResource("strong.png"));
            traveler = ImageIO.read(WorldMap.class.getResource("traveler.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        Dimension dim = this.getSize();
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(map,0,0,null);
        if(!anyAlive()){
            objects.clear();
            dp.reset();
        }
        removeCorpses();
        for(Interactable obj : objects){
            obj.action();
            switch(obj.getKind()){
                case Food.KIND:
                    g2d.drawImage(food,obj.getPosition().x*cellSize,obj.getPosition().y*cellSize,cellSize,cellSize,null);
                    g2d.drawString(String.valueOf(obj.getEnergy()),obj.getPosition().x*cellSize,obj.getPosition().y*cellSize);
                    break;
                case WeakPredator.KIND:
                    g2d.drawImage(weak,obj.getPosition().x*cellSize,obj.getPosition().y*cellSize,cellSize,cellSize,null);
                    break;
                case StrongPredator.KIND:
                    g2d.drawImage(strong,obj.getPosition().x*cellSize,obj.getPosition().y*cellSize,cellSize,cellSize,null);
                    break;
                case Traveler.KIND:
                    g2d.drawImage(traveler,obj.getPosition().x*cellSize,obj.getPosition().y*cellSize,cellSize,cellSize,null);
                    g2d.drawString(String.valueOf(obj.getEnergy()),obj.getPosition().x*cellSize,obj.getPosition().y*cellSize);
                    break;
            }
        }
    }

    public void addObject(Interactable obj) {
        objects.add(obj);
        int x = obj.getPosition().x;
        int y = obj.getPosition().y;
        world[x][y].setObject(obj);
    }
    public void addTravelers(ArrayList<Traveler> oldTravelers) {
        travelers = oldTravelers;
    }

    public void setConfig(int maxFood, int maxStrongPreds, int maxWeakPreds, int travelers){
        dp = new Deployer(this,world,maxStrongPreds,maxWeakPreds,maxFood,travelers);
        dp.deploy();
    }

    public Cell[][] getWorld() {
        return world;
    }

    public void action(){
        if(simulation == null){
        simulation = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    repaint();
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        simulation.start();
        }
    }
    private boolean anyAlive(){
        for(Traveler t : travelers){
            if(t.isAlive()){
                return true;
            }
        }
        return false;
    }

    private void removeCorpses(){
        LinkedList<Interactable> corpses = new LinkedList<>();
        for(Interactable obj : objects){
            if(!obj.check()){
                corpses.add(obj);
            }
        }
        for(Interactable obj : corpses){
            objects.remove(obj);
        }
    }
}
