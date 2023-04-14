// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller.sort

import aero.sort.vizualizer.app.Application
import aero.sort.vizualizer.ui.MainFrame
import aero.sort.vizualizer.ui.components.desktop.SortingFrame
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import javax.swing.JDesktopPane

/**
 * Tests [SortController].
 *
 * @author Daniel Ladendorfer
 */
@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
internal class SortControllerTest {
    @Mock
    lateinit var mainFrame: MainFrame
    @Mock
    lateinit var desktop: JDesktopPane
    @Mock
    lateinit var sortingFrame: SortingFrame

    @InjectMocks
    lateinit var controller: SortController

    @BeforeEach
    fun setup() {
        `when`(mainFrame.desktop).thenReturn(desktop)
        `when`(desktop.allFrames).thenReturn(arrayOf(sortingFrame))
    }

    @ParameterizedTest(name = "Sort invocation is not throwing; {0} times")
    @ValueSource(ints = intArrayOf(1, 5))
    fun `Sort invocation is not throwing`(times: Int) {
        repeat (times) {
            assertDoesNotThrow (
                    { "No exception should be thrown " },
                    controller::sort
            )
        }
    }

    @ParameterizedTest(name = "Stopping invocation is not throwing; {0} times")
    @ValueSource(ints = intArrayOf(1, 5))
    fun `Stopping invocation is not throwing`(times: Int) {
        repeat (times) {
            assertDoesNotThrow (
                    { "No exception should be thrown " },
                    controller::sort
            )
            assertDoesNotThrow (
                    { "No exception should be thrown " },
                    controller::stop
            )
        }
    }
}