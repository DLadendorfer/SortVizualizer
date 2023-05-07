// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.gradient

import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * App gradient style.
 *
 * @author Daniel Ladendorfer
 */
class App : AbstractGradientStyle() {
    override fun getPrimaryColor(): Color = Theme.UI_ACCENT
    override fun getSecondaryColor(): Color = Theme.UI_ACCENT_2
}