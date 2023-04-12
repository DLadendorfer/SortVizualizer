// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.laf;

import aero.sort.vizualizer.throwables.Rethrower;
import com.bulenkov.darcula.DarculaLaf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;

/**
 * Static utility class that setups all UI Manager mappings, LaF, etc.
 *
 * @author Daniel Ladendorfer
 */
public class UIBindings {

    private static Logger logger = LoggerFactory.getLogger(UIBindings.class);
    private static boolean lafInitialized;

    private UIBindings() {
        // static class - no instance needed
    }

    /**
     * Setups the look and feel.
     */
    public static synchronized void setupLookAndFeel() {
        if (lafInitialized) {
            return;
        }

        try {
            BasicLookAndFeel laf = new DarculaLaf();
            logger.info("Setting up Look-and-Feel. Using '{}'", laf.getName());
            UIManager.setLookAndFeel(new DarculaLaf());
            lafInitialized = true;
        } catch (UnsupportedLookAndFeelException e) {
            Rethrower.wrapAndRethrow("Failed to setup LaF: %s".formatted(e.getMessage()), e);
        }
    }
}
