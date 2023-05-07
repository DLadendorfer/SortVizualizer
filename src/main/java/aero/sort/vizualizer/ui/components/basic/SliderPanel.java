// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.basic;

import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.Ui;

import javax.swing.*;

/**
 * {@link JSlider} and {@link JLabel} combo component.
 *
 * @author Daniel Ladendorfer
 */
public class SliderPanel extends JPanel {
    private static final String TEXT_FORMAT = "%03d";
    private final JSlider slider;
    private final JLabel label;

    public SliderPanel(int min, int max, int value) {
        slider = new JSlider(min, max, value);
        label = new JLabel(toText());

        initialize();
    }

    private void initialize() {
        slider.addChangeListener(e -> SwingUtilities.invokeLater(this::onSliderChange));
        label.setForeground(Theme.UI_ACCENT);
        Ui.using(this)
          .add(slider, label);
    }

    private void onSliderChange() {
        label.setText(toText());
    }

    public int getValue() {
        return slider.getValue();
    }

    private String toText() {
        int value = getValue();
        return TEXT_FORMAT.formatted(value);
    }
}
