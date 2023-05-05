// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities.ui;

import aero.sort.vizualizer.ui.constants.Theme;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.function.Supplier;

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
    public static @NotNull JButton createButton(String label, @NotNull Runnable runnable) {
        Objects.requireNonNull(runnable, "Runnable must not be null");
        var button = new JButton(label);
        button.setForeground(Theme.UI_ACCENT);
        button.addActionListener(e -> runnable.run());
        return button;
    }

    /**
     * Creates a colored rectangle shaped Button. The color will be fetched by the color supplier. The given runnable
     * is invoked on click.
     *
     * @param colorSupplier the color supplier
     * @param runnable      the button action to invoke
     * @return the created button
     */
    public static @NotNull JButton createColorButton(@NotNull Supplier<Color> colorSupplier,
                                                     @NotNull Runnable runnable) {
        Objects.requireNonNull(colorSupplier, "Color supplier must not be null");
        Objects.requireNonNull(runnable, "Runnable must not be null");
        var button = new JButton(" ") {
            @Override
            public void paintComponent(Graphics g) {
                setBorder(null);
                super.paintComponent(g);
                if (g instanceof Graphics2D g2) {
                    g2.setColor(colorSupplier.get());
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        button.addActionListener(e -> runnable.run());
        return button;
    }
}
