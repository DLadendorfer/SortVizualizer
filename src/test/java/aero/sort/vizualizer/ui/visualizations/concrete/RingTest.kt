// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.visualizations.concrete

import aero.sort.vizualizer.algorithms.StepResult
import aero.sort.vizualizer.data.options.styles.concrete.White
import aero.sort.vizualizer.ui.visualizations.AbstractVisualizerTest
import aero.sort.vizualizer.ui.visualizations.IVisualizer
import java.util.*
import javax.swing.JPanel


/**
 * Tests [Ring].
 *
 * @author Daniel Ladendorfer
 */
internal class RingTest : AbstractVisualizerTest() {
    override fun getVisualizer(steps: LinkedList<StepResult>): IVisualizer = Ring(JPanel(), White(), steps)
}