// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller.management

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import javax.swing.JDesktopPane
import javax.swing.JInternalFrame

/**
 * Tests [FrameController].
 *
 * @author Daniel Ladendorfer
 */
@ExtendWith(MockitoExtension::class)
@MockitoSettings(strictness = Strictness.LENIENT)
internal class FrameManagementControllerTest {

    private lateinit var desktop: JDesktopPane
    private lateinit var controller: FrameController

    @BeforeEach
    fun setup() {
        desktop = JDesktopPane()
        controller = FrameController()
        FrameController.injectDesktop(desktop)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun closeAll(frameCount: Int) {
        for (i in 1..frameCount) {
            desktop.add(JInternalFrame())
        }
        controller.closeAll()
        assertEquals(0, desktop.allFrames.size) {
            "No frames should be present"
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun arrangeSideBySide(frameCount: Int) {
        for (i in 1..frameCount) {
            desktop.add(JInternalFrame())
        }
        assertDoesNotThrow({ "No exception should be thrown" }, { controller.arrangeSideBySide() })
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun arrangeStacking(frameCount: Int) {
        for (i in 1..frameCount) {
            desktop.add(JInternalFrame())
        }
        assertDoesNotThrow({ "No exception should be thrown" }, { controller.arrangeStacking() })
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9])
    fun smartArrange(frameCount: Int) {
        for (i in 1..frameCount) {
            desktop.add(JInternalFrame())
        }
        assertDoesNotThrow({ "No exception should be thrown" }, { controller.smartArrange() })
    }
}