// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities.ui

import aero.sort.vizualizer.ui.constants.Theme
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.concurrent.CountDownLatch
import javax.swing.JButton

/**
 * Tests [UiFactory].
 *
 * @author Daniel Ladendorfer
 */
internal class UiFactoryTest {

    @ParameterizedTest(name = "Button-label: {0}")
    @NullSource
    @ValueSource(strings = ["", "abc", "~~~"])
    fun `Tests if a button is created with a working action listener`(label: String?) {
        val latch = CountDownLatch(1)
        val button = UiFactory.createButton(label, latch::countDown)

        assertInstanceOf(JButton::class.java, button) {
            "A button instance should have been created"
        }
        assertEquals(1, latch.count) {
            "Latch count should be at 1"
        }

        button.doClick()

        assertEquals(0, latch.count) {
            "Latch count should be at 0"
        }
    }


    @Test
    fun `Tests if a color button can be created`() {
        val button = UiFactory.createColorButton({ Theme.BLACK }, { })
        assertNotNull(button) {
            "Button must not be null"
        }
    }
}