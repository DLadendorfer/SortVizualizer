// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.app

import aero.sort.vizualizer.ui.laf.UIBindings
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

/**
 * Tests [Application].
 *
 * @author Daniel Ladendorfer
 */
@ExtendWith(MockitoExtension::class)
internal class ApplicationTest {

    @Test
    fun `Launch does not throw any exception`() {
        val uiBindings = Mockito.mockStatic(UIBindings::class.java)
        uiBindings.use {
            uiBindings.`when`<UIBindings> { UIBindings.setupLookAndFeel() }.then { }
            assertDoesNotThrow(
                { "No exception should occur" },
                { Application.launch() }
            )
        }
    }
}