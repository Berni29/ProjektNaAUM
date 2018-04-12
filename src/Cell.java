public class Cell {

    public static final int FOREST = 1; // very high amount of food and dangers
    public static final int DESERT = 2; // very low amount of food and dangers
    public static final int FIELD = 3; // high amount of food and dangers
    public static final int MOUNTAIN = 4; // low amount of food and dangers
    public static final int SWAMP = 5; // low amount of food and high amount of dangers

    private boolean top = false;
    private boolean right = false;
    private boolean bottom = false;
    private boolean left = false;
    private boolean food = false;
    private Predator predator = null;
    private int biom = 0;
    private static int cellSize = 4;
    private int a = 0;

    public static int getCellSize() {
        return cellSize;
    }

    public static void setCellSize(int cellSize) {
        Cell.cellSize = cellSize;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public int getBiom() {
        return biom;
    }

    public void setBiom(int biom) {
        this.biom = biom;
    }

    public Predator getPredator() {
        return predator;
    }

    public void setPredator(Predator predator) {
        this.predator = predator;
    }
}
