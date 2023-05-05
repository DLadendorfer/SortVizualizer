// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.management;

import aero.sort.vizualizer.controller.Controllers;
import aero.sort.vizualizer.controller.management.FrameController;
import aero.sort.vizualizer.utilities.ui.Ui;
import aero.sort.vizualizer.utilities.ui.UiFactory;

import javax.swing.*;
import java.awt.*;

/**
 * This panel manages the internal frames of the application.
 */
public class FrameManagementPanel extends JPanel {

    public FrameManagementPanel() {
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        var checkBox = new JCheckBox("Smart Arrange automatically", true);
        checkBox.addChangeListener(e -> fetchController().autoSmartArrange(checkBox.isSelected()));
        Ui.using(this)
          .add(checkBox, UiFactory.createButton("Smart Arrange", () -> fetchController().smartArrange()), UiFactory.createButton("Arrange Stacking", () -> fetchController().arrangeStacking()), UiFactory.createButton("Arrange Side-by-Side", () -> fetchController().arrangeSideBySide()), UiFactory.createButton("Close All", () -> fetchController().closeAll()));
    }

    private static FrameController fetchController() {
        return Controllers.fetch(FrameController.class);
    }
}
