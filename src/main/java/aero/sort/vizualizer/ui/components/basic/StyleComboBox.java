// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.basic;

import aero.sort.vizualizer.data.options.Style;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Special implementation of {@link JComboBox} that manages the other ui components
 * that interact with the selected value.
 */
public class StyleComboBox extends JComboBox<Style> {

    private final JButton primaryColor;
    private final JButton secondaryColor;
    private final JLabel primaryLabel;
    private final JLabel secondaryLabel;

    public StyleComboBox(JButton primaryColor, JButton secondaryColor, JLabel primaryLabel,
                         JLabel secondaryLabel) {
        super(Style.values());
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.primaryLabel = primaryLabel;
        this.secondaryLabel = secondaryLabel;
        addActionListener(this::onComponentAction);
        SwingUtilities.invokeLater(() -> onComponentAction(null));
    }

    private void onComponentAction(@Nullable ActionEvent e) {
        var style = (Style) getSelectedItem();
        boolean showPrimary = style == Style.CUSTOM_GRADIENT || style == Style.CUSTOM_PLAIN;
        boolean showSecondary = style == Style.CUSTOM_GRADIENT;

        primaryColor.setVisible(showPrimary);
        primaryLabel.setVisible(showPrimary);
        secondaryColor.setVisible(showSecondary);
        secondaryLabel.setVisible(showSecondary);
    }
}
