// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms.concrete

import aero.sort.vizualizer.algorithms.AbstractSortingAlgorithmTest
import aero.sort.vizualizer.algorithms.ISortingAlgorithm
import org.junit.jupiter.api.Disabled

/**
 * Tests [SelectionSort].
 *
 * @author Daniel Ladendorfer
 */
@Disabled
internal class SelectionSortTest : AbstractSortingAlgorithmTest() {
    override fun getAlgorithm(): ISortingAlgorithm = SelectionSort()
}