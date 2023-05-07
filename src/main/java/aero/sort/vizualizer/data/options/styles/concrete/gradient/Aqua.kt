// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.gradient

import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Aqua gradient style.
 *
 * @author Daniel Ladendorfer
 */
class Aqua : AbstractGradientStyle() {
    override fun getPrimaryColor(): Color = Theme.BLUE
    override fun getSecondaryColor(): Color = Theme.DEEP_BLUE
}