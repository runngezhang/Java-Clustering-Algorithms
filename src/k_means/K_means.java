package k_means;

import java.util.ArrayList;
import java.util.List;

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
    private List<Integer>[] clusters;    // 类集合（每个 list 里存储该类中的点的 id）
    private Point[] curCenters;    // 聚类中心集合
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
    
    @SuppressWarnings("unchecked")
    public int run(int k) {
        this.k = k;
        this.curCenters = new Point[this.k+1];
        this.preCenters = new Point[this.k+1];
        this.clusters = new List[this.k+1];
        int times = 0;    // 迭代次数
        for(int i = 1; i <= k; i++) {   // 选择样本集中前 k 个样本作为初始聚类中心
            points[i].setClusteringResult(i);
            clusters[i] = new ArrayList<Integer>();
            clusters[i].add(i);
            curCenters[i] = points[i];
        }
        for(int j = k+1; j <= num; j++) {    // 剩下的每个样本按照最短距离原则分配
            int nearestCenter = 0;
            double minDistance = Double.MAX_VALUE;
            for(int i = 1; i <= k; i++) {
                if(points[j].getDistance(curCenters[i]) < minDistance) {
                    nearestCenter = i;
                    minDistance = points[j].getDistance(curCenters[i]);
                }
            }
            points[j].setClusteringResult(nearestCenter);
            clusters[nearestCenter].add(j);
        }
        while(true) {
            // 开始换聚类中心
            for(int i = 1; i <= k; i++) {
                preCenters[i] = curCenters[i];
                double newCenterX = 0, newCenterY = 0;
                for(int num: clusters[i]) {
                    newCenterX += points[num].getX();
                    newCenterY += points[num].getY();
                }
                newCenterX /= clusters[i].size();
                newCenterY /= clusters[i].size();
                Point newCenter = new Point(newCenterX, newCenterY);
                curCenters[i] = newCenter;
            }
            times++;
            // 比较聚类中心
            boolean canStop = true;
            for(int i = 1; i <= k; i++) {
                canStop = canStop && (preCenters[i].compare(curCenters[i]));
            }
            if(canStop)
                break;
            else {   // 开始迭代
                for(int i = 1; i <= k; i++) {
                    clusters[i] = new ArrayList<Integer>();
                }
                for(int j = 1; j <= num; j++) {    // 剩下的每个样本按照最短距离原则分配
                    int nearestCenter = 0;
                    double minDistance = Double.MAX_VALUE;
                    for(int i = 1; i <= k; i++) {
                        if(points[j].getDistance(curCenters[i]) < minDistance) {
                            nearestCenter = i;
                            minDistance = points[j].getDistance(curCenters[i]);
                        }
                    }
                    points[j].setClusteringResult(nearestCenter);
                    clusters[nearestCenter].add(j);
                }
            }
        }
        
        return times;
    }
    
    public void showClusteringResult() {
        System.out.println("K_means clustering centers number: " + k);
        System.out.println("K_means clustering centers: ");
        for(int i = 1; i <= k; i++) {
            System.out.println("Center " + i + " -> (" + curCenters[i].getX() + ", " +curCenters[i].getY() + ")");
        }
        
        for(int i = 1; i <= num; i++) {
            System.out.println("id: " + points[i].getId() + ", clusteringResult: " + points[i].getClusteringResult());
        }
    }
}
