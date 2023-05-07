// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.characteristics;

/**
 * The types of sort algorithms. The value is used to display the enumeration value
 * in the UI.
 *
 * @author Daniel Ladendorfer
 */
public enum Method {

    PARTITIONING("Partitioning"),
    PARTITIONING_SELECTION("Partitioning & Selection"),
    MERGING("Merging"),
    INSERTION("Insertion"),
    INSERTION_MERGING("Insertion & Merging"),
    EXCHANGING("Exchanging"),
    SELECTION("Selection");

    private final String text;

    Method(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
