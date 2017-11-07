package main;

import isodata.ISODATA;
import utils.In;

public class Main {

    public static void main(String[] args) {
        String filepath = "/Users/huang/Desktop/java learning/ClusteringAlgorithm/data/test2.txt";
//        Distance_threshold distance_threshold_model = new Distance_threshold(new In(filepath));
////        distance_threshold_model.run(args[1]);
//        distance_threshold_model.run(3);
//        distance_threshold_model.showClusteringResult();
        
//      Hierarchical hierarchical_model = new Hierarchical(new In(filepath));
//      hierarchical_model.run(3);
//      hierarchical_model.showClusteringResult();
      
//        K_means k_means_model = new K_means(new In(filepath));
//        int times = k_means_model.run(2);
//        System.out.println("迭代次数: " + times);
//        k_means_model.showClusteringResult();
        
        ISODATA isodata_model = new ISODATA(new In(filepath));
        isodata_model.run(1, 2, 1, 1, 4, 0, 4);
        isodata_model.showClusteringResult();
    }

//    private void runDistanceThreshold() {}
    
}
