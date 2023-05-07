// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.basic;

import aero.sort.vizualizer.data.options.Style;
import aero.sort.vizualizer.data.options.styles.StyleType;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Set;

/**
 * Special implementation of {@link JComboBox} that manages the other ui components
 * that interact with the selected value.
 */
public class StyleTypeComboBox extends JComboBox<StyleType> {
    private final JComboBox<Style> styleComboBox;
    private static final Map<StyleType, Set<Style>> styleTypeToStyle = //
            Map.of( //
                    StyleType.PLAIN, Set.of(Style.CUSTOM_PLAIN, Style.CYAN, Style.GREEN, Style.PURPLE, Style.WHITE, Style.YELLOW), //
                    StyleType.SPECIAL, Set.of(Style.RAINBOW, Style.RELATIVE_TO_POSITION), //
                    StyleType.GRADIENT, Set.of(Style.CUSTOM_GRADIENT, Style.APP, Style.AQUA, Style.GRAY, Style.SUNRISE, Style.SUNSET));

    public StyleTypeComboBox(JComboBox<Style> styleComboBox) {
        super(StyleType.values());
        this.styleComboBox = styleComboBox;
        addActionListener(this::onComponentAction);
        SwingUtilities.invokeLater(() -> onComponentAction(null));
    }

    private void onComponentAction(@Nullable ActionEvent e) {
        styleComboBox.removeAllItems();
        var styleType = (StyleType) getSelectedItem();
        styleTypeToStyle.get(styleType)
                        .forEach(styleComboBox::addItem);
    }
}
