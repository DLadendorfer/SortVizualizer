// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * Tests [VizualizerApplication].
 *
 * @author Daniel Ladendorfer
 */
internal class VizualizerApplicationTest {

    @Test
    fun `Invokes main and simply checks that no exception is thrown`() {
        assertDoesNotThrow {
            val args = arrayOf<String>()
            VizualizerApplication.main(args)
        }
    }
}