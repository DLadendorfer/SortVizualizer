// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms;

import aero.sort.vizualizer.data.characteristics.Method;
import aero.sort.vizualizer.data.characteristics.Performance;

import java.util.LinkedList;

/**
 * Basic interface for all sorting algorithms.
 * The sort call is required to return each "step" as a linked list.
 * This list will be used to visualize the sorting step-by-step.
 *
 * @author Daniel Ladendorfer
 */
public interface ISortingAlgorithm {

    /**
     * Sorts the given array of {@link Integer}.
     *
     * @param ints the array to sort
     * @return each step required to achieve a fully sorted array
     */
    LinkedList<StepResult> sort(Integer[] ints);

    /**
     * Whether the algorithm is a stable sorting algorithm.
     *
     * @return the stability
     */
    boolean isStable();

    /**
     * Whether the algorithm is a comparison sort algorithm.
     *
     * @return the comparistion sort flag
     */
    default boolean isComparisonSort() {
        return true;
    }

    /**
     * The performance characteristics of the algorithm.
     *
     * @return the performance characteristics
     */
    Performance getPerformance();

    /**
     * The type of sorting of the algorithm.
     *
     * @return the type of sorting
     */
    Method getMethod();
}
