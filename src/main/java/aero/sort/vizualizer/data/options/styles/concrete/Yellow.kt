// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete

import aero.sort.vizualizer.data.options.styles.AbstractPlainStyle
import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Yellow style.
 *
 * @author Daniel Ladendorfer
 */
class Yellow : AbstractPlainStyle() {
    override fun getPlainColor(): Color = Theme.YELLOW
}