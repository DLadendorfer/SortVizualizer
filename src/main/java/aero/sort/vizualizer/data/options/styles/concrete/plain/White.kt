// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.plain

import aero.sort.vizualizer.data.options.styles.AbstractPlainStyle
import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * White style.
 *
 * @author Daniel Ladendorfer
 */
class White : AbstractPlainStyle() {
    override fun getPlainColor(): Color = Theme.WHITE
}