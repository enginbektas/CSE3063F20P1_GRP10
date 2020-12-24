import java.util.ArrayList;

public class PerformanceMetrics { //this class is for holding all the metrics in one place
    //and making the writing operation proper
    private ArrayList<DatasetPerformanceMetric> datasetPerformanceMetrics;
    private ArrayList<UserPerformanceMetric> userPerformanceMetrics;
    private ArrayList<ArrayList<InstancePerformanceMetric>> instancePerformanceMetrics;

    public PerformanceMetrics (ArrayList<DatasetPerformanceMetric> datasetPerformanceMetrics,
                               ArrayList<UserPerformanceMetric> userPerformanceMetrics,
                               ArrayList<ArrayList<InstancePerformanceMetric>> instancePerformanceMetrics) {

        this.datasetPerformanceMetrics = datasetPerformanceMetrics;
        this.userPerformanceMetrics = userPerformanceMetrics;
        this.instancePerformanceMetrics = instancePerformanceMetrics;
    }

}


