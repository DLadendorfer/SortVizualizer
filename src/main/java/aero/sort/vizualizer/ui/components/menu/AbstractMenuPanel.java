// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu;

import aero.sort.vizualizer.data.options.IOptions;
import aero.sort.vizualizer.data.registry.DataRegistry;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Abstract implementation of a menu panel.
 *
 * @param <T> the configuration option
 *
 * @author Daniel Ladendorfer
 */
public abstract class AbstractMenuPanel<T extends IOptions> extends JPanel {

    protected FluentConstraints constraints;

    public AbstractMenuPanel(FluentConstraints constraints) {
        this.constraints = constraints;
        initialize();
    }

    private void initialize() {
        setLayout(new GridBagLayout());
        createHeader();
        createUiComponents();
        initializePanel();
        registerOptions();
    }

    private void registerOptions() {
        DataRegistry.registerOptionsSupplier(getData().getClass(), this::getData);
    }

    private void createHeader() {
        var matteBorder = new MatteBorder(1, 0, 0, 0, Theme.UI_ACCENT);
        var border = new TitledBorder(matteBorder, getTitle(), TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
        border.setTitleFont(border.getTitleFont().deriveFont(15.0f));
        border.setTitleColor(Theme.UI_ACCENT);
        setBorder(border);
    }

    /**
     * Returns the title of the panel.
     * @return the title
     */
    protected abstract String getTitle();

    /**
     * Returns the current data representation of the panel.
     * @return the data of the panel
     */
    protected abstract T getData();

    /**
     * Instantiates all UI component fields.
     */
    protected abstract void createUiComponents();

    /**
     * Initializes the panel.
     */
    protected abstract void initializePanel();
}
