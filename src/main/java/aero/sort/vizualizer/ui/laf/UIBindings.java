// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.laf;

import aero.sort.vizualizer.throwables.Rethrower;
import aero.sort.vizualizer.ui.constants.Theme;
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
    private static final Logger logger = LoggerFactory.getLogger(UIBindings.class);
    private static boolean lafInitialized;

    static {
        UIManager.put("Button.darcula.selection.color1", Theme.UI_ACCENT_2);
        UIManager.put("Button.darcula.selection.color2", Theme.UI_ACCENT_2);
        UIManager.put("CheckBox.darcula.checkSignColor", Theme.UI_ACCENT);
        UIManager.put("Slider.thumbBorderColor", Theme.UI_ACCENT);
        UIManager.put("Slider.selectedTrackColor", Theme.UI_ACCENT);
        UIManager.put("RadioButton.darcula.selectionEnabledColor", Theme.UI_ACCENT);
        UIManager.put("InternalFrameTitlePane.darcula.selected.backgroundColor", Theme.UI_ACCENT_2);
        UIManager.put("InternalFrameTitlePane.darcula.backgroundColor", Theme.UI_ACCENT_2);
        UIManager.put("ProgressBar.selectionForeground", Theme.UI_ACCENT_2);
    }

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
