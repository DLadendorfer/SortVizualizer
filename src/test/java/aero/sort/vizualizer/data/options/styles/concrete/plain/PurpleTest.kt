// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.plain

import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [Purple].
 *
 * @author Daniel Ladendorfer
 */
internal class PurpleTest : AbstractPlainStyleTest() {
    override fun getExpectedColor(): Color = Theme.PURPLE
    override fun getPlainStyle(): AbstractPlainStyle = Purple()
}