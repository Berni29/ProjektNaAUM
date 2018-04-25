import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WorldCreator {
    private static Cell[][] world;
    private int x = 0 , y = 0;

    public WorldCreator(File path) {
        String buffer;
        checkSize(path);
        String[] info = {"","",""};
        world = new Cell[x+1][y+1];
        for(int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                world[i][j] = new Cell(i,j);
                if (i < 10 && j < 10)
                    world[i][j].setArea(1);
                if (i >= 10 && i < 20 && j < 10)
                    world[i][j].setArea(2);
                if (i < 10 && j >= 10 && j < 20)
                    world[i][j].setArea(3);
                if (i >= 10 && i < 20 && j >= 10 && j < 20)
                    world[i][j].setArea(4);
            }
        }
        BiomSetter.setBioms(world);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            for(int i = 0 ; i < (x+1)*(y+1) ; i++) {
                buffer = reader.readLine();
                info[0] = buffer.split(" ")[0];
                info[1] = buffer.split(" ")[1];
                try {
                    info[2] = buffer.split(" ")[2];
                }
                catch(ArrayIndexOutOfBoundsException e) {
                    info[2] = "";
                }
                int pX = Integer.parseInt(info[0]);
                int pY = Integer.parseInt(info[1]);
                switch(info[2]) {
                    case "PD":
                        world[pX][pY].setTop(true);
                        world[pX][pY].setLeft(true);
                        if(pX==x) {
                            world[pX][pY].setRight(true);
                        }
                        if(pY==y) {
                            world[pX][pY].setBottom(true);
                        }
                        if(pY>0) {
                            world[pX][pY-1].setBottom(true);
                        }
                        if(pX>0) {
                            world[pX-1][pY].setRight(true);
                        }
                        break;
                    case "P":
                        world[pX][pY].setTop(true);
                        if(pX==x) {
                            world[pX][pY].setRight(true);
                        }
                        if(pY==y) {
                            world[pX][pY].setBottom(true);
                        }
                        if(pY>0) {
                            world[pX][pY-1].setBottom(true);
                        }
                        break;
                    case "D":
                        world[pX][pY].setLeft(true);
                        if(pX==x) {
                            world[pX][pY].setRight(true);
                        }
                        if(pY==y) {
                            world[pX][pY].setBottom(true);
                        }
                        if(pX>0) {
                            world[pX-1][pY].setRight(true);
                        }
                        break;
                    default:
                        if(pX==x) {
                            world[pX][pY].setRight(true);
                        }
                        if(pY==y) {
                            world[pX][pY].setBottom(true);
                        }
                        break;
                }
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void checkSize(File path){
        String stringX = "0", stringY = "0";
        String buffer;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            do {
                buffer = reader.readLine();
                if(buffer!=null) {
                    stringX = buffer.split(" ")[0];
                    stringY = buffer.split(" ")[1];
                }
            } while (buffer!=null);
            reader.close();
            x = Integer.parseInt(stringX);
            y = Integer.parseInt(stringY);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Cell[][] getWorld() { return world; }

    public Point getSize(){
        Point p = new Point();
        p.x = x;
        p.y = y;
        return p;
    }


}
