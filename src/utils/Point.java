package utils;

public class Point {
    private final double x;
    private final double y;
    private final int id;
    private int clusteringResult;
    
    public Point(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public Point(int id, double x, double y, int clusteringResult) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.clusteringResult = clusteringResult;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getId() {
        return id;
    }
    public int getClusteringResult() {
        return clusteringResult;
    }
    public void setClusteringResult(int clusteringResult) {
        this.clusteringResult = clusteringResult;
    }
    public double getDistance(Point another) {
        double xDistance = Math.abs(x - another.getX());
        double yDistance = Math.abs(y - another.getY());
        return Math.sqrt(Math.pow(xDistance,2) + Math.pow(yDistance,2));
    }
}
