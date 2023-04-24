// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

/**
 * Tests [MenuPanel].
 *
 * @author Daniel Ladendorfer
 */
internal class MenuPanelTest {
    // because this is a UI panel only the instantiation is tested

    @Test
    fun `Tests if the ActionPanel can be instantiated`() {
        assertDoesNotThrow({ "No exception should be thrown" }, { MenuPanel() })
    }
}