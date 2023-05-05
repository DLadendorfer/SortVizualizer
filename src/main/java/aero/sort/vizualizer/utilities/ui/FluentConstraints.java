// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities.ui;

import aero.sort.vizualizer.annotation.meta.Justification;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Fluent java GridConstraint facade.
 *
 * @author Daniel Ladendorfer
 */
public class FluentConstraints {
    private final GridBagConstraints gbc;


    @Justification("Create or of(gbc) should be used")
    private FluentConstraints(GridBagConstraints gbc) {
        this.gbc = gbc;
    }

    /**
     * Creates a new instance of {@link FluentConstraints} with a new managed instance of {@link GridBagConstraints}.
     *
     * @return the new {@link FluentConstraints} instance
     */
    public static @NotNull FluentConstraints create() {
        return of(new GridBagConstraints());
    }

    /**
     * Creates a new instance of {@link FluentConstraints} with the given instance of {@link GridBagConstraints}.
     *
     * @param gbc the {@link GridBagConstraints} to manage
     * @return the new {@link FluentConstraints} instance
     */
    public static @NotNull FluentConstraints of(GridBagConstraints gbc) {
        return new FluentConstraints(gbc);
    }

    /**
     * Increments gridx by one.
     *
     * @return this
     */
    public @NotNull FluentConstraints incrementX() {
        gbc.gridx++;
        return this;
    }

    /**
     * Increments gridy by one.
     *
     * @return this
     */
    public @NotNull FluentConstraints incrementY() {
        gbc.gridy++;
        return this;
    }

    /**
     * Sets gridX to zero.
     *
     * @return this
     */
    public @NotNull FluentConstraints resetX() {
        gbc.gridx = 0;
        return this;
    }

    /**
     * Sets gridY to zero.
     *
     * @return this
     */
    public @NotNull FluentConstraints resetY() {
        gbc.gridy = 0;
        return this;
    }

    /**
     * Sets gridwith to the given width.
     *
     * @param width the new gridwith
     * @return this
     */
    public @NotNull FluentConstraints width(int width) {
        gbc.gridwidth = width;
        return this;
    }

    /**
     * Sets ipady to the given padding.
     *
     * @param pad the new padding
     * @return this
     */
    public @NotNull FluentConstraints padY(int pad) {
        gbc.ipady = pad;
        return this;
    }


    /**
     * Sets weighty to the given padding.
     *
     * @param weight the new padding
     * @return this
     */
    public @NotNull FluentConstraints weightY(float weight) {
        gbc.weighty = weight;
        return this;
    }

    /**
     * Sets weightx to the given padding.
     *
     * @param weight the new padding
     * @return this
     */
    public @NotNull FluentConstraints weightX(float weight) {
        gbc.weightx = weight;
        return this;
    }

    /**
     * Returns the managed instance of {@link GridBagConstraints}.
     *
     * @return the managed instance
     */
    public GridBagConstraints get() {
        return gbc;
    }
}
