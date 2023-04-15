// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.action

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * Tests [ActionPanel].
 *
 * @author Daniel Ladendorfer
 */
internal class ActionPanelTest {
    // because this is a UI panel only the instantiation is tested

    @Test
    fun `Tests if the ActionPanel can be instantiated`() {
        assertDoesNotThrow({ "No exception should be thrown" }, { ActionPanel() })
    }
}