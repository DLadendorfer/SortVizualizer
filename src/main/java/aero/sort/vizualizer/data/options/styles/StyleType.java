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
    /**
     * Special, unique styles which usually provide colors in a unique way based on the
     * {@link StyleContext}.
     *
     * @see StyleContext
     */
    SPECIAL("Special Styles"),

    /**
     * Gradient styles provide a color based on the value of an entry in the sort set.
     * The index of the element is irrelevant.
     */
    GRADIENT("2-Color Gradient"),

    /**
     * Plain color styles always return the same color - no matter the value, index or
     * any other value in {@link StyleContext}.
     *
     * @see StyleContext
     */
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
