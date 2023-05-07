// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete

import aero.sort.vizualizer.data.options.styles.AbstractPlainStyle
import aero.sort.vizualizer.data.options.styles.AbstractPlainStyleTest
import aero.sort.vizualizer.data.options.styles.concrete.plain.White
import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [White].
 *
 * @author Daniel Ladendorfer
 */
internal class WhiteTest : AbstractPlainStyleTest() {
    override fun getExpectedColor(): Color = Theme.WHITE
    override fun getPlainStyle(): AbstractPlainStyle = White()
}