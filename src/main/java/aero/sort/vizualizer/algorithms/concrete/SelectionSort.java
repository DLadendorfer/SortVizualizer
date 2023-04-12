// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms.concrete;

import aero.sort.vizualizer.algorithms.AbstractSortingAlgorithm;
import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.options.SortOptions;

import java.util.LinkedList;

public class SelectionSort extends AbstractSortingAlgorithm {
    public SelectionSort(SortOptions options) {
        super(options);
    }

    @Override
    protected LinkedList<StepResult> sortInternal() {
        return null;
    }
}
