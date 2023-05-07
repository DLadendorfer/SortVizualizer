// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.gradient

import aero.sort.vizualizer.data.options.styles.AbstractGradientStyle
import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Sunrise gradient style.
 *
 * @author Daniel Ladendorfer
 */
class Sunrise : AbstractGradientStyle() {
    override fun getPrimaryColor(): Color = Theme.CYAN
    override fun getSecondaryColor(): Color = Theme.YELLOW
}