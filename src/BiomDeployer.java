import java.util.Random;

public class BiomDeployer {

    private static Cell[][] world = WorldCreator.getWorld();

    private static Random random = new Random();

    private static int areaNr1 = random.nextInt(5) + 1;
    private static int areaNr2 = random.nextInt(5) + 1;
    private static int areaNr3 = random.nextInt(5) + 1;
    private static int areaNr4 = random.nextInt(5) + 1;

    public static void createBiomWorld(){

        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                if(world[i][j].getArea() == 1)
                    world[i][j].setBiom(areaNr1);
                if(world[i][j].getArea() == 2)
                    world[i][j].setBiom(areaNr2);
                if(world[i][j].getArea() == 3)
                    world[i][j].setBiom(areaNr3);
                if(world[i][j].getArea() == 4)
                    world[i][j].setBiom(areaNr4);
            }
        }
    }

    public static int getAreaNr1() {
        return areaNr1;
    }

    public static int getAreaNr2() {
        return areaNr2;
    }

    public static int getAreaNr3() {
        return areaNr3;
    }

    public static int getAreaNr4() {
        return areaNr4;
    }
}
