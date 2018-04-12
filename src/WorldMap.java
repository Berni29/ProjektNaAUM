import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public class WorldMap extends JComponent {

    private BufferedImage map;
    public Cell[][] world;
    private HashSet<Displayable> objects = new HashSet<>();
    private int cellSize;
    private NewDeployer dp;

    public WorldMap(WorldCreator wc, MapCreator mp, int cellSize) {
        world = wc.getWorld();
        map = mp.getMap();
        this.cellSize = cellSize;
    }

    public void paintComponent(Graphics g){
        Dimension dim = this.getSize();
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(map,0,0,null);
        int space = cellSize*2/10;
        for(Displayable obj : objects){
            g2d.setColor(obj.getKind());
            g2d.fillOval(obj.getPosition().x*cellSize+space,obj.getPosition().y*cellSize+space,cellSize-2*space,cellSize-2*space);
        }
    }

    public void addObject(Displayable obj) {
        objects.add(obj);
        int x = obj.getPosition().x;
        int y = obj.getPosition().y;
        world[x][y].setObject(obj);
    }

    public void setConfig(int maxFood, int maxStrongPreds, int maxWeakPreds){
        dp = new NewDeployer(this,world,maxStrongPreds,maxWeakPreds,maxFood);
        dp.deploy();
    }

}
