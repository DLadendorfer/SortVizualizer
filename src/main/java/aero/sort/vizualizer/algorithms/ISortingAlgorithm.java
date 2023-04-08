package aero.sort.vizualizer.algorithms;

import java.util.LinkedList;

/**
 * Basic interface for all sorting algorithms.
 *
 * @author Daniel Ladendorfer
 */
public interface ISortingAlgorithm {

    LinkedList<StepResult> sort(Integer[] ints);
}
