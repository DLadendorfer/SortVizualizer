// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu.concrete;

import aero.sort.vizualizer.controller.Controllers;
import aero.sort.vizualizer.controller.management.FrameController;
import aero.sort.vizualizer.data.options.DebugOptions;
import aero.sort.vizualizer.ui.components.desktop.LogFrame;
import aero.sort.vizualizer.ui.components.menu.AbstractMenuPanel;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import aero.sort.vizualizer.utilities.ui.UiFactory;
import org.slf4j.event.Level;

import javax.swing.*;

/**
 * Debug options panel.
 *
 * @author Daniel Ladendorfer
 */
public class DebugPanel extends AbstractMenuPanel<DebugOptions> {
    private JComboBox<Level> logLevelComboBox;

    public DebugPanel(FluentConstraints constraints) {
        super(constraints);
    }

    @Override
    protected String getTitle() {
        return "Debugging ";
    }

    @Override
    protected DebugOptions getData() {
        var logLevel = (Level) logLevelComboBox.getSelectedItem();
        return new DebugOptions(logLevel);
    }

    @Override
    protected void createUiComponents() {
        logLevelComboBox = new JComboBox<>(Level.values());
    }

    @Override
    protected void initializePanel() {
        add(new JLabel("Log Level: "), constraints.width(1).incrementY().resetX().get());
        add(logLevelComboBox, constraints.incrementX().get());
        logLevelComboBox.setSelectedItem(Level.DEBUG);
        add(UiFactory.createButton("Add Log Frame", () -> {
            var logFrame = new LogFrame(getData());
            var desktop = Controllers.fetch(FrameController.class).getDesktop();
            desktop.add(logFrame);
            logFrame.toFront();
        }), constraints.resetX().incrementY().width(2).get());
    }
}
