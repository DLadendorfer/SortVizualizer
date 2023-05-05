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
 * The sort call is required to return each "step" as a linked list. The algorithm itself is
 * responsible to populate the list.
 * This list will be used to visualize the sorting step-by-step.
 *
 * @author Daniel Ladendorfer
 */
public interface ISortingAlgorithm {

    // most methods are simply descriptive methods of the concrete implementation
    // the ::sort(ints) method is the concrete sorting interface method

    /**
     * Sorts the given array of {@link Integer} and populates the given list.
     *
     * @param ints the array to sort
     * @return each step required to achieve a fully sorted array
     */
    LinkedList<StepResult> sort(Integer[] ints);

    /**
     * Whether the algorithm is a stable sorting algorithm.
     * This information should only ever be needed by the UI.
     *
     * @return the stability
     */
    boolean isStable();

    /**
     * Whether the algorithm is a comparison sort algorithm.
     * This information should only ever be needed by the UI.
     *
     * @return the comparison sort flag
     */
    default boolean isComparisonSort() {
        return true;
    }

    /**
     * The performance characteristics of the algorithm.
     * This information should only ever be needed by the UI.
     *
     * @return the performance characteristics
     */
    Performance getPerformance();

    /**
     * The type of sorting of the algorithm.
     * This information should only ever be needed by the UI.
     *
     * @return the type of sorting
     */
    Method getMethod();
}
