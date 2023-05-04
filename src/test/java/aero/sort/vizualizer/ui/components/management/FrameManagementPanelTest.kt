// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.management

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * Tests [FrameManagementPanel].
 *
 * @author Daniel Ladendorfer
 */
internal class FrameManagementPanelTest {
    // because this is a UI panel only the instantiation is tested
    @Test
    fun `Tests if the FrameManagementPanel can be instantiated`() {
        assertDoesNotThrow({ "No exception should be thrown" }, { FrameManagementPanel() })
    }
}