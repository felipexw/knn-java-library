package com.github.felipexw.clusters;

import java.util.List;

/**
 * Created by felipe.appio on 30/08/2016.
 */
public interface KMeansCluster {

    void cluster(List<UnlabeledInstance> instanceList);

}
