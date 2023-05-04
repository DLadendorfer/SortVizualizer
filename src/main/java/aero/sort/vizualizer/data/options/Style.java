// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options;

/**
 * Enumeration of all render styles.
 *
 * @author Daniel Ladendorfer
 */
public enum Style {
    APP("Application Theme"),
    RAINBOW("Rainbow"),
    CUSTOM_GRADIENT("Custom 2-Color Gradient"),
    GRAY("Grayscale"),
    SUNSET("Sunset-Red"),
    SUNRISE("Sunrise-Blue"),
    AQUA("Aqua"),
    CUSTOM_PLAIN("Custom Plain Color"),
    WHITE("White"),
    CYAN("Cyan"),
    GREEN("Green"),
    PURPLE("Purple"),
    YELLOW("Yellow");

    private final String displayText;

    Style(String displayText) {
        this.displayText = displayText;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
