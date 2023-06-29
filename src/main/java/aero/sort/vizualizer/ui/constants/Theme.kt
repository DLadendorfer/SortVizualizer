// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.constants

import java.awt.Color

/**
 * Contains all theme constants. These should be the only forced colors in the application. Meaning that if a
 * components foreground or background color should be changed, only colors of this class should be referenced.
 *
 *
 * In no circumstance should any invocation of new Color() be necessary in other parts of the application.
 *
 * @author Daniel Ladendorfer
 */
object Theme {
    // inspired by darcula and One Half Dark
    @JvmField
    val RED = Color(0xE06C75)

    @JvmField
    val DARK_RED = Color(0xEF1A2B)

    @JvmField
    val GREEN = Color(0x98C379)

    @JvmField
    val YELLOW = Color(0xE5C07B)

    @JvmField
    val BLUE = Color(0x61AFEF)

    @JvmField
    val DEEP_BLUE = Color(0x084777)

    @JvmField
    val DARK_GREEN = Color(0x378022)

    @JvmField
    val PURPLE = Color(0xC678DD)

    @JvmField
    val CYAN = Color(0x56B6C2)

    @JvmField
    val FRY = Color(0xF38D15)

    @JvmField
    val WHITE = Color(0xDCDFE4)

    @JvmField
    val BLACK = Color(0x282C34)

    @JvmField
    val GRAY = Color(0x5A6374)

    @JvmField
    val BACKGROUND = Color(0x282C34)

    @JvmField
    val FOREGROUND = Color(0xDCDFE4)

    @JvmField
    val UI_ACCENT = BLUE

    @JvmField
    val UI_ACCENT_2 = DEEP_BLUE
}