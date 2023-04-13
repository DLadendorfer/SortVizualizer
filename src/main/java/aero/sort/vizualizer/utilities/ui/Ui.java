// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities.ui;

import javax.swing.*;

/**
 * UI utility class that should simplify some java.swing code.
 *
 * @author Daniel Ladendorfer
 */
public final class Ui {

    private Ui() {
        // static utility class - no instance needed
    }

    /**
     * Creates an instance of {@link FluentUi} managing the given component.
     *
     * @param component the component to manage
     * @return the new instance of {@link FluentUi}
     * @param <T> the concrete {@link JComponent} type
     */
    public static <T extends JComponent> FluentUi<T> using(T component) {
        return new FluentUi<T> (component);
    }
}
