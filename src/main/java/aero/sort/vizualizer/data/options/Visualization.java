// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options;

/**
 * Enumeration of all visualization types.
 *
 * @author Daniel Ladendorfer
 */
public enum Visualization {
    BARS("Bars");

    private final String displayText;

    Visualization(String displayText) {
        this.displayText = displayText;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
