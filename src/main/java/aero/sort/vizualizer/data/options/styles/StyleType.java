// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles;

/**
 * Enumeration of all render styles types.
 *
 * @author Daniel Ladendorfer
 */
public enum StyleType {
    GRADIENT("2-Color Gradient"),
    PLAIN("Plain Color");

    private final String displayText;

    StyleType(String displayText) {
        this.displayText = displayText;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
