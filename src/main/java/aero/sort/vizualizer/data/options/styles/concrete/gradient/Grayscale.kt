// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.gradient

import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * BlackWhite gradient style.
 *
 * @author Daniel Ladendorfer
 */
class Grayscale : AbstractGradientStyle() {
    override fun getPrimaryColor(): Color = Theme.GRAY
    override fun getSecondaryColor(): Color = Theme.WHITE
}