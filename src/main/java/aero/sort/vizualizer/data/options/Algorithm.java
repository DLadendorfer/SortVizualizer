// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options;

/**
 * Enumeration of all sorting algorithms.
 *
 * @author Daniel Ladendorfer
 */
public enum Algorithm {
    BUBBLE("Bubble-Sort"),
    INSERTION("Insertion-Sort"),
    SELECTION("Selection-Sort"),
    MERGE("Merge-Sort"),
    QUICK("Quick-Sort");

    private final String displayText;

    Algorithm(String displayText) {
        this.displayText = displayText;
    }

    @Override
    public String toString() {
        return displayText;
    }
}
