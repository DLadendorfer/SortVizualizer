// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.maths;

import aero.sort.vizualizer.annotation.meta.Justification;

/**
 * Enumeration values of characteristics notations.
 *
 * @author Daniel Ladendorfer
 */
@SuppressWarnings("UnnecessaryUnicodeEscape")
@Justification("We can't use the ² character (or other unicode literals) because swing won't render it correctly")
public enum Notation {

    ONE("1"),
    LOG_N("log n"),
    N("n"),
    N_SQUARE("n\u00B2"),
    N_LOG_N("n log n");

    private final String text;

    Notation(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
