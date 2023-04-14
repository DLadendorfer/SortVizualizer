// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.app

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * Tests [Application].
 *
 * @author Daniel Ladendorfer
 */
internal class ApplicationTest {

    @Test
    fun `Launch does not throw any exception`() {
        assertDoesNotThrow(
                { "No exception should occur" },
                { Application.launch() }
        )
    }
}