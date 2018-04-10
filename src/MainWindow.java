import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;

public class MainWindow {

    private JPanel mainPanel;
    private WorldMap worldMap;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        try {
            File f = new File(MainWindow.class.getResource("SampleMaze1.txt").toURI());
            WorldCreator wc = new WorldCreator(f);
            Point s = wc.getSize();
            MapCreator mc = new MapCreator(wc.getWorld(), 30, s.x, s.y);
            worldMap = new WorldMap(wc, mc, 30);
            worldMap.addObject(new Food(10,15));
            worldMap.addObject(new Food(15,10));
            worldMap.addObject(new Food(5,5));
            worldMap.addObject(new Food(10,10));
            worldMap.addObject(new Food(15,15));
        }
        catch (URISyntaxException e){
            e.printStackTrace();
        }
    }
}
