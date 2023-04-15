// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer

import aero.sort.vizualizer.ui.laf.UIBindings
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

/**
 * Tests [VizualizerApplication].
 *
 * @author Daniel Ladendorfer
 */
@ExtendWith(MockitoExtension::class)
internal class VizualizerApplicationTest {

    @Test
    fun `Invokes main and simply checks that no exception is thrown`() {
        val uiBindings = Mockito.mockStatic(UIBindings::class.java)
        uiBindings.use {
            uiBindings.`when`<UIBindings> { UIBindings.setupLookAndFeel() }.then { }
            assertDoesNotThrow {
                VizualizerApplication.main(emptyArray())
            }
        }
    }
}