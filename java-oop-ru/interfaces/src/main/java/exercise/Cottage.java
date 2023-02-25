package exercise;

// BEGIN
public class Cottage implements Home {

    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public int getFloor() {
        return floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    public String toString() {
        return getFloor() + " этажный коттедж площадью " + getArea() + " метров";
    }
}
// END
