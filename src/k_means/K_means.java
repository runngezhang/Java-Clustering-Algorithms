package k_means;

import utils.In;
import utils.Point;

/**
 * @author huang
 * K-均值算法
 */
public class K_means {
    
    private final int num;    // 点的数量
    private Point[] points;    // 点的集合
    private int k;    // 指定聚类中心个数
    private Point[] centers;    // 聚类中心集合
    private Point[] preCenters;    // 上一次迭代的聚类中心集合

    public K_means(int num) {
        this.num = num;
        this.points = new Point[this.num+1];
    }
    
    public K_means(In in) {
        this(in.readInt());    // 点的数量
        for(int i = 1; i < num+1; i++) {
            int x = in.readInt();    // 读取 x 坐标
            int y = in.readInt();    // 读取 y 坐标
            Point point = new Point(i, x, y);
            points[i] = point;
        }
    }
    
    public void run(int k) {
        this.k = k;
        this.centers = new Point[this.k+1];
        for(int i = 0; i <= k; i++)    // 选择样本集中前 k 个样本作为初始聚类中心
            points[i].setClusteringResult(i);
    }
    
    public void showClusteringResult() {
        System.out.println("Hierarchical clustering centers number: " + k);
        for(int i = 1; i < num+1; i++) {
            System.out.println("id: " + points[i].getId() + ", clusteringResult: " + points[i].getClusteringResult());
        }
    }
}
