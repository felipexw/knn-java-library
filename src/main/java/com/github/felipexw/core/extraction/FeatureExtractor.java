package com.github.felipexw.core.extraction;

import java.util.List;

/**
 * Created by felipe.appio on 31/08/2016.
 */
public interface FeatureExtractor<T> {
    List<T> extract(List<Object> source);
}
