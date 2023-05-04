// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.characteristics;

import aero.sort.vizualizer.data.maths.Notation;

/**
 * The performance of a sorting algorithm.
 *
 * @param best    the best case scenario
 * @param average the average case scenario
 * @param worst   the worst case scenario
 * @param memory  the memory usage of the algorithm
 * @author Daniel Ladendorfer
 */
public record Performance(Notation best, Notation average, Notation worst, Notation memory) {
}
