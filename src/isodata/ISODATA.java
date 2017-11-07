package isodata;

import java.util.ArrayList;
import java.util.List;

import utils.In;
import utils.Point;

/**
 * @author huang
 * 迭代自组织的数据分析算法
 */
public class ISODATA {

    private final int num;    // 点的数量
    private Point[] points;    // 点的集合
//    private int k;    // 指定聚类中心个数
    private List<Integer>[] clusters;    // 类集合（每个 list 里存储该类中的点的 id）
    private List<Point> curCenters;    // 聚类中心集合
    private double[] averageDToCenter;    // 样本到该类聚类中心的平均距离
//    private Point[] preCenters;    // 上一次迭代的聚类中心集合

    public ISODATA(int num) {
        this.num = num;
        this.points = new Point[this.num+1];
    }
    
    public ISODATA(In in) {
        this(in.readInt());    // 点的数量
        for(int i = 1; i <= num; i++) {
            int x = in.readInt();    // 读取 x 坐标
            int y = in.readInt();    // 读取 y 坐标
            Point point = new Point(i, x, y);
            points[i] = point;
        }
        curCenters = new ArrayList<>();
    }
    
    /**
     * 运行算法
     * @param Nc 预选聚类中心个数
     * @param K 希望的聚类中心的数目
     * @param thetaN 每个聚类中最少的样本数
     * @param thetaS 聚类域中样本的标准差阈值
     * @param thetaC 两聚类中心之间的最短距离
     * @param L 在一次迭代中允许合并的聚类中心的最大对数
     * @param I 允许迭代的次数
     */
    @SuppressWarnings("unchecked")
    public void run(int Nc, int K, int thetaN, double thetaS, double thetaC, int L, int I) {
        // 第一步：预选聚类中心，确定预选指标
        clusters = new List[Nc+1];
        for(int i = 1; i <= Nc; i++)
            clusters[i] = new ArrayList<Integer>();
        // 选择前 Nc 个点作为预选的聚类中心
        for(int i = 1; i <= Nc; i++) {
            points[i].setClusteringResult(i);
            clusters[i].add(i);
            curCenters.add(points[i]);
        }
        // 第二步：把 N 个样本按最近邻规则分配到 Nc 个聚类中
        for(int j = Nc+1; j <= num; j++) {
            int nearestCenter = 0;
            double minDistance = Double.MAX_VALUE;
            for(int i = 1; i <= Nc; i++) {
                if(points[j].getDistance(curCenters.get(i-1)) < minDistance) {
                    nearestCenter = i;
                    minDistance = points[j].getDistance(curCenters.get(i-1));
                }
            }
            points[j].setClusteringResult(nearestCenter);
            clusters[nearestCenter].add(j);
        }
        // 第三步：若某一类中的样本数小于 thetaN，则取消该类，并且 Nc 减去 1
        for(int i = 1; i <= Nc; i++) {
            if(clusters[i].size() < thetaN) {
                /** 这里的取消该类怎么弄？不懂意思，可能不是按以下方法（因为这些失去类的点算什么呢？自成一类？） */
                for(int number : clusters[i]) {
                    points[number].setClusteringResult(0);
                }
                curCenters.remove(i-1);
                /** 是否要将去掉的类中的所有点算作聚类中心？ */
                clusters[i] = null;
                Nc--;
            }
        }
        // 第四步：修正各聚类中心值
        for(int i = 1; i <= Nc; i++) {
            if(clusters[i] == null) {
                curCenters.set(i-1, null);
            } else {
                double newCenterX = 0, newCenterY = 0;
                for(int number: clusters[i]) {
                    newCenterX += points[number].getX();
                    newCenterY += points[number].getY();
                }
                newCenterX /= clusters[i].size();
                newCenterY /= clusters[i].size();
                Point newCenter = new Point(newCenterX, newCenterY);
                curCenters.set(i-1, newCenter);
            }
        }
        // 第五类：计算聚类域 Sj 中各样本到该类聚类中心的平均距离
        averageDToCenter = new double[Nc+1];
        for(int i = 1; i <= Nc; i++) {
            
        }
    }
    
    public void showClusteringResult() {
//        System.out.println("ISODATA clustering centers number: " + k);
//        System.out.println("ISODATA clustering centers: ");
//        for(int i = 1; i <= k; i++) {
//            System.out.println("Center " + i + " -> (" + curCenters[i].getX() + ", " +curCenters[i].getY() + ")");
//        }
        
        for(int i = 1; i <= num; i++) {
            System.out.println("id: " + points[i].getId() + ", clusteringResult: " + points[i].getClusteringResult());
        }
    }
}
