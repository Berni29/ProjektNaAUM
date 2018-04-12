public class Cell {

    public static final int FOREST = 1; // very high amount of food and moderate danger
    public static final int DESERT = 2; // very low amount of food and low danger
    public static final int MOUNTAIN = 3; // low amount of food and moderate danger
    public static final int SWAMP = 4; // moderate amount of food and high danger

    private boolean top = false;
    private boolean right = false;
    private boolean bottom = false;
    private boolean left = false;
    private Displayable object = null;
    private int biom = 0;
    private int area = 0;
    private static int cellSize = 4;

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

    public int getBiom() {
        return biom;
    }

    public void setBiom(int biom) {
        this.biom = biom;
    }

    public int getArea() { return area; }

    public void setArea(int area) { this.area = area; }

    public Displayable getObject() {
        return object;
    }

    public void setObject(Displayable object) {
        this.object = object;
    }
}
