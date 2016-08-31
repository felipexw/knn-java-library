package com.github.felipexw.clusters;

import com.github.felipexw.types.Instance;

import java.util.List;

/**
 * Created by felipe.appio on 30/08/2016.
 */
public interface KMeansCluster {

    void cluster(List<Instance> instanceList);

}
