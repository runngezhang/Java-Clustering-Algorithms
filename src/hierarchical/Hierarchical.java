package hierarchical;

import utils.In;
import utils.Point;

/**
 * @author huang
 * 层次聚类算法（最短距离）
 */
public class Hierarchical {
    private final int num;    // 点的数量
    private Point[] points;    // 点的集合
    private int centerNum;    // 类中心点数量
    private double[][] distanceArray;    // 距离矩阵
    
    public Hierarchical(int num) {
        this.num = num;
        this.centerNum = num;
        this.points = new Point[this.num+1];
        this.distanceArray = new double[this.num+1][this.num+1];
    }
    
    public Hierarchical(In in) {
        this(in.readInt());    // 点的数量
        for(int i = 1; i < num+1; i++) {
            int x = in.readInt();    // 读取 x 坐标
            int y = in.readInt();    // 读取 y 坐标
            Point point = new Point(i, x, y, i);
            points[i] = point;
        }
    }
    
    /**
     * 运行算法
     * @param threshold 阈值
     */
    public void run(int threshold) {
        while(true) {
            double minDistance = Double.MAX_VALUE;
            int nearestI = 0, nearestJ = 0;
            for(int i = 1; i < num+1; i++) {
                for(int j = i+1; j < num+1; j++) {
                    
                    if(points[i].getClusteringResult() == points[j].getClusteringResult())    // 如果两个点已经在同一类，则将其距离设为最大，避免影响后面的计算（好像也不影响，跳过了）
                        distanceArray[i][j] = Double.MAX_VALUE;
                    else {
                        distanceArray[i][j] = points[i].getDistance(points[j]);
                        if(minDistance > distanceArray[i][j]) {
                            minDistance = distanceArray[i][j];
                            nearestI = i;
                            nearestJ = j;
                        }
                    }
                }
            }
            
            if(minDistance > threshold)
                break;
            else {
                int center = points[nearestJ].getClusteringResult();
                for(int i = 1; i < num+1; i++) {    // nearestI 和 nearestJ 合并（要查找和 nearestI 一类的所有点，改变其聚类结果）
                    if(points[i].getClusteringResult() == center) {
                        points[i].setClusteringResult(points[nearestI].getClusteringResult());
                    }
                }
                centerNum--;
            }
        }
    }
    
    public void showClusteringResult() {
        System.out.println("Hierarchical clustering centers number: " + centerNum);
        for(int i = 1; i < num+1; i++) {
            System.out.println("id: " + points[i].getId() + ", clusteringResult: " + points[i].getClusteringResult());
        }
    }
    
}
