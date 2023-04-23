// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu.concrete;

import aero.sort.vizualizer.data.options.Duplicates;
import aero.sort.vizualizer.data.options.SetType;
import aero.sort.vizualizer.data.options.SortSetOptions;
import aero.sort.vizualizer.ui.components.menu.AbstractMenuPanel;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Menu panel with all sort set options.
 *
 * @author Daniel Ladendorfer
 */
public class SortSetPanel extends AbstractMenuPanel<SortSetOptions> {
    private JCheckBox identical;
    private JSlider elements;
    private JRadioButton none;
    private JRadioButton some;
    private JRadioButton many;
    private JRadioButton random;
    private JRadioButton sorted;
    private JRadioButton almostSorted;
    private JRadioButton completelyUnsorted;

    public SortSetPanel(FluentConstraints constraints) {
        super(constraints);
    }

    @Override
    protected String getTitle() {
        return "Sort Set Options ";
    }

    @Override
    protected SortSetOptions getData() {
        return new SortSetOptions(identical.isSelected(), elements.getValue(), getDuplicates(), getSetType());
    }

    @Override
    protected void createUiComponents() {
        identical = new JCheckBox("", true);
        elements = new JSlider(0, 100, 20);
        ButtonGroup duplicates = new ButtonGroup();
        none = new JRadioButton("None", true);
        some = new JRadioButton("Some");
        many = new JRadioButton("Many");
        duplicates.add(none);
        duplicates.add(some);
        duplicates.add(many);
        ButtonGroup setType = new ButtonGroup();
        random = new JRadioButton("Random", true);
        sorted = new JRadioButton("Sorted");
        almostSorted = new JRadioButton("Almost sorted");
        completelyUnsorted = new JRadioButton("Completely unsorted");
        setType.add(random);
        setType.add(sorted);
        setType.add(almostSorted);
        setType.add(completelyUnsorted);
        elements.setMajorTickSpacing(10);
        elements.setPaintTicks(true);
        elements.setPaintLabels(true);
    }

    @Override
    protected void initializePanel() {
        add(new JLabel("Identical set: "), constraints.resetX().incrementY().get());
        add(identical, constraints.incrementX().get());
        add(new JLabel("Number of elements: "), constraints.resetX().incrementY().get());
        add(elements, constraints.incrementX().get());
        add(new JLabel("Duplicates: "), constraints.resetX().incrementY().get());
        add(none, constraints.padY(-10).incrementX().get());
        add(some, constraints.incrementY().get());
        add(many, constraints.incrementY().get());
        add(new JLabel("Set Type: "), constraints.padY(0).resetX().incrementY().get());
        add(random, constraints.incrementX().padY(-10).get());
        add(sorted, constraints.incrementY().get());
        add(almostSorted, constraints.incrementY().get());
        add(completelyUnsorted, constraints.incrementY().get());

    }

    private SetType getSetType() {
        if (random.isSelected()) {
            return SetType.RANDOM;
        } else if (sorted.isSelected()) {
            return SetType.SORTED;
        } else if (almostSorted.isSelected()) {
            return SetType.ALMOST_SORTED;
        }
        return SetType.COMPLETELY_SORTED;
    }

    private Duplicates getDuplicates() {
        if (none.isSelected()) {
            return Duplicates.NONE;
        } else if (some.isSelected()) {
            return Duplicates.SOME;
        }

        return Duplicates.MANY;
    }
}
