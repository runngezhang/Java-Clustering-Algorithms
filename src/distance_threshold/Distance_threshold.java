package distance_threshold;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.In;
import utils.Point;

/**
 * @author huang
 * 基于距离阈值的聚类算法
 */
public class Distance_threshold {
    private final int num;    // 点的数量
    private Point[] points;    // 点的集合
    List<Integer> centers;    // 作为中心的点的 id 列表
    
    public Distance_threshold(int num) {
        this.num = num;
        this.points = new Point[this.num+1];
        this.centers = new ArrayList<Integer>();
    }
    
    public Distance_threshold(In in) {
        this(in.readInt());    // 点的数量
        for(int i = 1; i <= num; i++) {
            int x = in.readInt();    // 读取 x 坐标
            int y = in.readInt();    // 读取 y 坐标
            Point point = new Point(i, x, y);
            points[i] = point;
        }
    }
    
    /**
     * 运行算法
     * @param threshold 阈值
     */
    public void run(int threshold) {
        // int startId = new Random().nextInt(num) + 1;   // [1, num] 中的随机数
        int startId = 1;
        centers.add(startId);
        points[startId].setClusteringResult(startId);
        for(int i = 1; i <= num; i++) {
            if(i == startId)
                continue;
            double minDistance = Double.MAX_VALUE;
            int nearestCenter = startId;
            for(int id : centers) {
                double distance = points[i].getDistance(points[id]);
                if(distance < minDistance) {
                    minDistance = distance;
                    nearestCenter = id;
                }
            }
            if(minDistance <= threshold) {
                points[i].setClusteringResult(points[nearestCenter].getClusteringResult());
            } else {
                points[i].setClusteringResult(i);
                centers.add(i);
            }
        }
    }
    
    public void showClusteringResult() {
        System.out.println("Distance_threshold clustering centers number: " + centers.size());
        for(int i = 1; i <= num; i++) {
            System.out.println("id: " + points[i].getId() + ", clusteringResult: " + points[i].getClusteringResult());
        }
//        String centersResult = "";
//        for(int id : centers) {
//            centersResult += id + ", ";
//        }
//        System.out.println("Clustering centers are points " + centersResult);
    }
}
