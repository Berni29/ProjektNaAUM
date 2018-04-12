public class BiomSetter {

    public static void setBioms(Cell[][] world) {
        int x = world.length;
        int y = world[0].length;
        int halfX = x/2;
        int halfY = y/2;
        for(int i = 0; i < halfX; i++) {
            for(int j = 0; j < halfY; j++) {
                world[i][j].setBiom(Cell.FOREST);
            }
        }
        for(int i = halfX; i < x; i++) {
            for(int j = 0; j < halfY; j++) {
                world[i][j].setBiom(Cell.DESERT);
            }
        }
        for(int i = 0; i < halfX; i++) {
            for(int j = halfY; j < y; j++) {
                world[i][j].setBiom(Cell.MOUNTAIN);
            }
        }
        for(int i = halfX; i < x; i++) {
            for(int j = halfY; j < y; j++) {
                world[i][j].setBiom(Cell.SWAMP);
            }
        }

    }

}
