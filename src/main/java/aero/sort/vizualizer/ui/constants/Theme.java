// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.constants;

import java.awt.*;

/**
 * Contains all theme constants. These should be the only forced colors in the application. Meaning that if a
 * components foreground or background color should be changed, only colors of this class should be referenced.
 * <p>
 * In no circumstance should any invocation of new Color() be necessary in other parts of the application.
 *
 * @author Daniel Ladendorfer
 */
public class Theme {
    // inspired by darcula and One Half Dark
    public static final Color RED = new Color(0xE06C75);
    public static final Color DARK_RED = new Color(0xEF1A2B);
    public static final Color GREEN = new Color(0x98C379);
    public static final Color YELLOW = new Color(0xE5C07B);
    public static final Color BLUE = new Color(0x61AFEF);
    public static final Color DEEP_BLUE = new Color(0x084777);
    public static final Color DARK_GREEN = new Color(0x378022);
    public static final Color PURPLE = new Color(0xC678DD);
    public static final Color CYAN = new Color(0x56B6C2);
    public static final Color FRY = new Color(0xF38D15);
    public static final Color WHITE = new Color(0xDCDFE4);
    public static final Color BLACK = new Color(0x282C34);
    public static final Color GRAY = new Color(0x5A6374);
    public static final Color BACKGROUND = new Color(0x282C34);
    public static final Color FOREGROUND = new Color(0xDCDFE4);
    public static final Color UI_ACCENT = Theme.BLUE;
    public static final Color UI_ACCENT_2 = Theme.DEEP_BLUE;

    private Theme() {
        // static utility class - no instance needed
    }
}
