// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete

import aero.sort.vizualizer.data.options.styles.AbstractPlainStyle
import aero.sort.vizualizer.data.options.styles.AbstractPlainStyleTest
import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [Green].
 *
 * @author Daniel Ladendorfer
 */
internal class GreenTest : AbstractPlainStyleTest() {
    override fun getExpectedColor(): Color = Theme.GREEN
    override fun getPlainStyle(): AbstractPlainStyle = Green()
}