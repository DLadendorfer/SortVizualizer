// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller

import aero.sort.vizualizer.controller.sort.SortController
import aero.sort.vizualizer.ui.MainFrame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness

/**
 * Tests [Controller].
 *
 * @author Daniel Ladendorfer
 */
@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
internal class ControllerTest {
    // ! this class should not test the functionality of the concrete controllers
    // ! only their invocation should be tested

    @Suppress("unused") // mock injection
    @Mock
    lateinit var sortController: SortController

    @InjectMocks
    private lateinit var controller: Controller

    @Test
    fun `SortController sort invocation does not throw any exceptions`() {
        assertDoesNotThrow(
            { "No Exception should be thrown " },
            controller::sort
        )
    }

    @Test
    fun `SortController stop invocation does not throw any exceptions`() {
        assertDoesNotThrow(
            { "No Exception should be thrown " },
            controller::stopSort
        )
    }
}