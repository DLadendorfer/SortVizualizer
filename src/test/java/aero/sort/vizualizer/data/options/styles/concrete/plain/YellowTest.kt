// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.plain

import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [Yellow].
 *
 * @author Daniel Ladendorfer
 */
internal class YellowTest : AbstractPlainStyleTest() {
    override fun getExpectedColor(): Color = Theme.YELLOW
    override fun getPlainStyle(): AbstractPlainStyle = Yellow()
}