import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MapCreator {

    private final BufferedImage map;
    private BufferedImage forest = null;
    private BufferedImage mountains = null;
    private BufferedImage desert = null;
    private BufferedImage swamp = null;

    public MapCreator(Cell[][] maze, int cellSize, int x, int y) {
        try {
            forest = ImageIO.read(MapCreator.class.getResource("forest.jpg"));
            mountains = ImageIO.read(MapCreator.class.getResource("mountains.jpg"));
            desert = ImageIO.read(MapCreator.class.getResource("desert.jpg"));
            swamp = ImageIO.read(MapCreator.class.getResource("swamp.jpg"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        map = new BufferedImage((x+1)*cellSize+1,(y+1)*cellSize+1,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) map.getGraphics();
        BiomSetter.setBioms(maze);
        for(int j = 0; j<=y; j++){
            for(int i = 0; i<=x; i++){
                switch (maze[i][j].getBiom()){
                    case Cell.FOREST:
                        g.drawImage(forest,i*cellSize,j*cellSize,cellSize,cellSize,null);
                        break;
                    case Cell.DESERT:
                        g.drawImage(desert,i*cellSize,j*cellSize,cellSize,cellSize,null);
                        break;
                    case Cell.MOUNTAIN:
                        g.drawImage(mountains,i*cellSize,j*cellSize,cellSize,cellSize,null);
                        break;
                    case Cell.SWAMP:
                        g.drawImage(swamp,i*cellSize,j*cellSize,cellSize,cellSize,null);
                        break;
                }
                g.setColor(Color.BLACK);
                if(maze[i][j].isTop())
                    g.drawLine(i*cellSize,j*cellSize,i*cellSize+cellSize,j*cellSize);
                if(maze[i][j].isLeft())
                    g.drawLine(i*cellSize,j*cellSize,i*cellSize,j*cellSize+cellSize);
                if(maze[i][j].isBottom())
                    g.drawLine(i*cellSize,j*cellSize+cellSize,i*cellSize+cellSize,j*cellSize+cellSize);
                if(maze[i][j].isRight())
                    g.drawLine(i*cellSize+cellSize,j*cellSize,i*cellSize+cellSize,j*cellSize+cellSize);
            }
        }
    }

    public BufferedImage getMap() {
        return map;
    }

}
