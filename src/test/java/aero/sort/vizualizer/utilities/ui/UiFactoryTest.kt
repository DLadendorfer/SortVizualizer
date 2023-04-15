// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities.ui

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
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
    fun `Tests that the runnable is not allowed to be passed as null`() {
        assertThrows<NullPointerException>({ "NullPointerException should be thrown" },
            { UiFactory.createButton("any", null) })
    }
}