// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
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
