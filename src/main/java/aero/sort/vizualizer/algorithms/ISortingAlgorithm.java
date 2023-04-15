// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms;

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
}
