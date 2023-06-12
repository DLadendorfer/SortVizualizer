// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.visualizations.concrete

import aero.sort.vizualizer.algorithms.StepResult
import aero.sort.vizualizer.data.options.styles.concrete.plain.White
import aero.sort.vizualizer.ui.visualizations.AbstractVisualizerTest
import aero.sort.vizualizer.ui.visualizations.IVisualizer
import java.util.*
import javax.swing.JPanel


/**
 * Tests [Fries].
 *
 * @author Daniel Ladendorfer
 */
internal class FriesTest : AbstractVisualizerTest() {
    override fun getVisualizer(steps: LinkedList<StepResult>): IVisualizer = Fries(JPanel(), White(), steps)
}