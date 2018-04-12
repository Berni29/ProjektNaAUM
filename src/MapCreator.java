import sun.plugin.dom.css.RGBColor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapCreator {

    private final BufferedImage map;

    public MapCreator(Cell[][] maze, int cellSize, int x, int y) {
        map = new BufferedImage((x+1)*cellSize+1,(y+1)*cellSize+1,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) map.getGraphics();
        BiomSetter.setBioms(maze);
        for(int j = 0; j<=y; j++){
            for(int i = 0; i<=x; i++){
                switch (maze[i][j].getBiom()){
                    case Cell.FOREST:
                        g.setColor(Color.GREEN);
                        g.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
                        break;
                    case Cell.DESERT:
                        g.setColor(Color.YELLOW);
                        g.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
                        break;
                    case Cell.MOUNTAIN:
                        g.setColor(Color.GRAY);
                        g.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
                        break;
                    case Cell.SWAMP:
                        g.setColor(Color.BLUE);
                        g.fillRect(i*cellSize,j*cellSize,cellSize,cellSize);
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
