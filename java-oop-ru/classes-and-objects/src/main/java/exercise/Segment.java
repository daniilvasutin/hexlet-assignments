package exercise;

// BEGIN
public class Segment {
    private Point PointStart;
    private Point PointEnd;

    public Segment(Point pointStart, Point pointEnd) {
        PointStart = pointStart;
        PointEnd = pointEnd;
    }

    public Point getPointStart() {
        return PointStart;
    }

    public void setPointStart(Point pointStart) {
        PointStart = pointStart;
    }

    public Point getPointEnd() {
        return PointEnd;
    }

    public void setPointEnd(Point pointEnd) {
        PointEnd = pointEnd;
    }

    public Point getBeginPoint() {
        return PointStart;
    }

    public Point getEndPoint() {
        return PointEnd;
    }

    public Point getMidPoint() {
        var midX = (PointStart.getX() + PointEnd.getX()) / 2;
        var midY = (PointStart.getY() + PointEnd.getY()) / 2;


        return new Point(midX, midY);
    }
}
// END
