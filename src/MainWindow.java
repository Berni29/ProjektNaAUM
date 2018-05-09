import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URISyntaxException;

public class MainWindow {

    private JPanel mainPanel;
    private WorldMap worldMap;
    private static JFrame frame;

    public MainWindow() {
        worldMap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                worldMap.action();
                worldMap.repaint();
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void createUIComponents() {
        try {
            File f = new File(MainWindow.class.getClassLoader().getResource("SampleMaze1.txt").toURI());
            WorldCreator wc = new WorldCreator(f);
            Point s = wc.getSize();
            MapCreator mc = new MapCreator(wc.getWorld(), 45, s.x, s.y);
            worldMap = new WorldMap(wc, mc, 45);
            Configuration dialog = new Configuration(frame,worldMap);
            dialog.pack();
            dialog.setVisible(true);
        }
        catch (URISyntaxException e){
            e.printStackTrace();
        }
    }
}
