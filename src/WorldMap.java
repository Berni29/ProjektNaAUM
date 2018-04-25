import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;

public class WorldMap extends JComponent {

    private BufferedImage map;
    private Cell[][] world;
    private HashSet<Interactable> objects = new HashSet<>();
    private int cellSize;
    private Deployer dp;
    private BufferedImage food;
    private BufferedImage weak;
    private BufferedImage strong;
    private BufferedImage traveler;

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
        for(Interactable obj : objects){
            switch(obj.getKind()){
                case Food.KIND:
                    g2d.drawImage(food,obj.getPosition().x*cellSize,obj.getPosition().y*cellSize,cellSize,cellSize,null);
                    break;
                case WeakPredator.KIND:
                    g2d.drawImage(weak,obj.getPosition().x*cellSize,obj.getPosition().y*cellSize,cellSize,cellSize,null);
                    break;
                case StrongPredator.KIND:
                    g2d.drawImage(strong,obj.getPosition().x*cellSize,obj.getPosition().y*cellSize,cellSize,cellSize,null);
                    break;
                case Traveler.KIND:
                    g2d.drawImage(traveler,obj.getPosition().x*cellSize,obj.getPosition().y*cellSize,cellSize,cellSize,null);
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

    public void setConfig(int maxFood, int maxStrongPreds, int maxWeakPreds, int travelers){
        dp = new Deployer(this,world,maxStrongPreds,maxWeakPreds,maxFood,travelers);
        dp.deploy();
    }

    public Cell[][] getWorld() {
        return world;
    }

    public void action(){
        objects.clear();
        dp.reset();
    }
}
