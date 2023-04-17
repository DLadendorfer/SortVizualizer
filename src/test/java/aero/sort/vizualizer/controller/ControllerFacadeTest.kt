// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller

import aero.sort.vizualizer.annotation.meta.Justification
import aero.sort.vizualizer.controller.management.FrameManagementController
import aero.sort.vizualizer.controller.sort.SortController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import javax.swing.JDesktopPane

/**
 * Tests [ControllerFacade].
 *
 * @author Daniel Ladendorfer
 */
@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
internal class ControllerFacadeTest {
    // ! this class should not test the functionality of the concrete controllers
    // ! only their invocation should be tested

    @Justification("Suppressing 'unused' due to mock injection")
    @Suppress("unused")
    @Mock
    lateinit var sortController: SortController

    @Justification("Suppressing 'unused' due to mock injection")
    @Suppress("unused")
    @Mock
    lateinit var frameManagementController: FrameManagementController

    @InjectMocks
    private lateinit var controller: ControllerFacade

    @BeforeEach
    fun setup() {
        FrameManagementController.injectDesktop(JDesktopPane())
    }

    @Test
    fun `SortController sort invocation does not throw any exceptions`() {
        assertDoesNotThrow(
            { "No Exception should be thrown " }, controller::sort
        )
    }

    @Test
    fun `SortController stop invocation does not throw any exceptions`() {
        assertDoesNotThrow(
            { "No Exception should be thrown " }, controller::stopSort
        )
    }

    @Test
    fun `ManagementController closeAll invocation does not throw any exceptions`() {
        assertDoesNotThrow(
            { "No Exception should be thrown " }, controller::closeAll
        )
    }

    @Test
    fun `ManagementController arrange stacking invocation does not throw any exceptions`() {
        assertDoesNotThrow(
            { "No Exception should be thrown " }, controller::arrangeStacking
        )
    }

    @Test
    fun `ManagementController smart arrange invocation does not throw any exceptions`() {
        assertDoesNotThrow(
            { "No Exception should be thrown " }, controller::smartArrange
        )
    }

    @Test
    fun `ManagementController arrange side-by-side invocation does not throw any exceptions`() {
        assertDoesNotThrow(
            { "No Exception should be thrown " }, controller::arrangeSideBySide
        )
    }
}