import java.awt.*;
import java.util.Random;

public class Deployer {

    private static Point point = null;
    private static Random random = new Random();

    public static Point getFoodPosition() {

        point = new Point();

        switch(TablesGenerator.getFoodTab()[random.nextInt(TablesGenerator.getBiomsFoodTabSize())]){
            case 1:
                point.x = random.nextInt(10);
                point.y = random.nextInt(10);
                break;
            case 2:
                point.x = random.nextInt(10) + 10;
                point.y = random.nextInt(10);
                break;
            case 3:
                point.x = random.nextInt(10);
                point.y = random.nextInt(10) + 10;
                break;
            case 4:
                point.x = random.nextInt(10) + 10;
                point.y = random.nextInt(10) + 10;
                break;
        }
        return point;
    }

    public static Point getPredatorPosition() {

        point = new Point();

        switch(TablesGenerator.getPredatorTab()[random.nextInt(TablesGenerator.getBiomsPredatorTabSize())]){
            case 1:
                point.x = random.nextInt(10);
                point.y = random.nextInt(10);
                break;
            case 2:
                point.x = random.nextInt(10) + 10;
                point.y = random.nextInt(10);
                break;
            case 3:
                point.x = random.nextInt(10);
                point.y = random.nextInt(10) + 10;
                break;
            case 4:
                point.x = random.nextInt(10) + 10;
                point.y = random.nextInt(10) + 10;
                break;
        }
        return point;
    }



//    public static Point getFoodPosition(){
//        point = new Point();
//        point.x = random.nextInt(20);
//        point.y = random.nextInt(20);
//        return point;
//    }
//
//    public static Point getPredatorPosition(){
//        point = new Point();
//        point.x = random.nextInt(20);
//        point.y = random.nextInt(20);
//        return point;
//    }

}
