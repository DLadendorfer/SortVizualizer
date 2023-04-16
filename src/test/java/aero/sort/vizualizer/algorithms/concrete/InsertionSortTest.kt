// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms.concrete

import aero.sort.vizualizer.algorithms.AbstractSortingAlgorithmTest
import aero.sort.vizualizer.algorithms.ISortingAlgorithm

/**
 * Tests [InsertionSort].
 *
 * @author Daniel Ladendorfer
 */
internal class InsertionSortTest : AbstractSortingAlgorithmTest() {
    override fun getAlgorithm(): ISortingAlgorithm = InsertionSort()
}