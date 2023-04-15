// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


/**
 * Abstract test class for all concrete Test implementations.
 *
 * @author Daniel Ladendorfer
 */
internal abstract class AbstractSortingAlgorithmTest {

    private val sortedArr = arrayOf(0, 1, 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, Integer.MAX_VALUE)
    private val unsortedArr = arrayOf(1, 1, 0, 2, 9, 8, 2, 3, 4, 5, Integer.MAX_VALUE, 7, 6, 10)

    protected abstract fun getAlgorithm(): ISortingAlgorithm

    @Test
    fun `Tests the sort function`() {
        val list = getAlgorithm().sort(unsortedArr.copyOf(unsortedArr.size))
        assertNotNull(list) {
            "List should not be null"
        }
        assertFalse(list.isEmpty()) {
            "List should not be empty"
        }
        assertTrue(sortedArr.contentEquals(list.pollLast().ints)) {
            "Arrays should be equal (sorted <> unsorted.sort)"
        }
    }
}