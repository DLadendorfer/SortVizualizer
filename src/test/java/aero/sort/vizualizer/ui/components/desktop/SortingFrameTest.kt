// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.action

import aero.sort.vizualizer.data.options.SortOptions
import aero.sort.vizualizer.ui.components.desktop.SortingFrame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

/**
 * Tests [SortingFrame].
 *
 * @author Daniel Ladendorfer
 */
@ExtendWith(MockitoExtension::class)
internal class SortingFrameTest {
    // because this is a UI panel only the instantiation is tested

    @Mock
    lateinit var options: SortOptions

    @Test
    fun `Tests if the ActionPanel can be instantiated`() {
        assertDoesNotThrow({ "No exception should be thrown" }, { SortingFrame(options) })
    }
}