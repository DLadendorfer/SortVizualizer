// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities.ui;

import javax.swing.*;

/**
 * Factory class that creates UI components.
 *
 * @author Daniel Ladendorfer
 */
public final class UiFactory {
    private UiFactory() {
        // static utility class - no instance needed
    }

    /**
     * Creates a button with the specified label that invokes the given runnable on click.
     *
     * @param label    the button's label
     * @param runnable the button action to invoke
     * @return the created button
     */
    public static JButton createButton(String label, Runnable runnable) {
        var button = new JButton(label);
        button.addActionListener(e -> runnable.run());
        return button;
    }
}
