// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities.ui

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

/**
 * Tests [FluentUi].
 *
 * @author Daniel Ladendorfer
 */
internal class FluentUiTest {

    @Test
    fun `Tests if null components are not allowed to be added`() {
        assertThrows<NullPointerException>({ "NullPointerExceptoin should be thrown" },
            { FluentUi(JPanel()).add(null) })
    }

    @Test
    fun `Tests if a single component can be added`() {
        val panel = JPanel()
        val label = JLabel()
        FluentUi(panel).add(label)
        assertSame(label, panel.getComponent(0)) {
            "Label should have been added"
        }
    }

    @Test
    fun `Tests if a multiple components can be added`() {
        val panel = JPanel()
        val label = JLabel()
        val button = JButton()
        FluentUi(panel).add(label, button)
        assertSame(label, panel.getComponent(0)) {
            "Label should have been added"
        }
        assertSame(button, panel.getComponent(1)) {
            "Button should have been added"
        }
    }

    @Test
    fun `Tests if the managed component can be retrieved with get`() {
        val panel = JPanel()
        assertSame(panel, FluentUi(panel).get()) {
            "Panel should be the same"
        }
    }

    @Test
    fun `Tests that the execute consumer can not be null`() {
        assertThrows<NullPointerException>({ "NullPointerException should be thrown " },
            { FluentUi(JPanel()).execute(null) })
    }

    @Test
    fun `Tests if the execute method is functional`() {
        val label = JLabel("<3")
        FluentUi(label).execute { it.text = "</3" }
        assertEquals("</3", label.text) {
            "Label text should have changed"
        }
    }
}