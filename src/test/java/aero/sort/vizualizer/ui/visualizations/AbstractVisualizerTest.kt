// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------

package aero.sort.vizualizer.ui.visualizations

import aero.sort.vizualizer.algorithms.StepResult
import aero.sort.vizualizer.data.options.MarkType
import aero.sort.vizualizer.data.options.VisualizationOptions
import aero.sort.vizualizer.data.registry.DataRegistry
import aero.sort.vizualizer.data.shared.SharedStepToken
import aero.sort.vizualizer.data.shared.StepInstruction
import aero.sort.vizualizer.ui.constants.Theme
import aero.sort.vizualizer.utilities.Async
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

/**
 * Tests [AbstractVisualizer].
 *
 * @author Daniel Ladendorfer
 */
@ExtendWith(MockitoExtension::class)
internal abstract class AbstractVisualizerTest {

    protected abstract fun getVisualizer(steps: LinkedList<StepResult>): IVisualizer

    @Test
    fun `Tests if the rendering does not throw any exception`() {
        val list = LinkedList<StepResult>()
        val arr = arrayOf(1, 2, 3)
        val options = VisualizationOptions(
            true, true, 0L, VisualizationOptions.Marker(MarkType.OFF, Theme.WHITE)
        )

        list.add(StepResult(emptyArray(), arr))
        list.add(StepResult(arrayOf(1, 2), arr))
        list.add(StepResult(emptyArray(), arr))

        val async = Mockito.mockStatic(Async::class.java)
        async.use {
            async.`when`<Async> { Async.sleep(anyLong()) }.then { }
        }

        DataRegistry.registerOptionsSupplier(VisualizationOptions::class.java) { options }

        val visualizer = getVisualizer(list)
        assertDoesNotThrow { visualizer.render(SharedStepToken(StepInstruction.CONTINUE)) }
    }
}