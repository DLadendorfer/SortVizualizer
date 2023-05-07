// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete

import aero.sort.vizualizer.data.options.styles.concrete.plain.AbstractPlainStyle
import aero.sort.vizualizer.data.options.styles.AbstractPlainStyleTest
import aero.sort.vizualizer.data.options.styles.concrete.plain.Cyan
import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [Cyan].
 *
 * @author Daniel Ladendorfer
 */
internal class CyanTest : AbstractPlainStyleTest() {
    override fun getExpectedColor(): Color = Theme.CYAN
    override fun getPlainStyle(): AbstractPlainStyle = Cyan()
}