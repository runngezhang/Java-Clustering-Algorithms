package main;

import distance_threshold.Distance_threshold;
import hierarchical.Hierarchical;
import k_means.K_means;
import utils.In;

public class Main {

    public static void main(String[] args) {
        String filepath = "/Users/huang/Desktop/java learning/ClusteringAlgorithm/data/test.txt";
//        Distance_threshold distance_threshold_model = new Distance_threshold(new In(filepath));
//        distance_threshold_model.run(args[1]);
//        distance_threshold_model.run(3);
//        distance_threshold_model.showClusteringResult();
        
//      Hierarchical hierarchical_model = new Hierarchical(new In(filepath));
//      hierarchical_model.run(3);
//      hierarchical_model.showClusteringResult();
      
        K_means k_means_model = new K_means(new In(filepath));
        k_means_model.run(2);
        k_means_model.showClusteringResult();
    }

//    private void runDistanceThreshold() {}
    
}
