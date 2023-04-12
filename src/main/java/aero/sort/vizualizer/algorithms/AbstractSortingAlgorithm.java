// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms;

import aero.sort.vizualizer.data.options.SortOptions;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Abstract implementation for all sorting algorithms.
 *
 * @author Daniel Ladendorfer
 */
public abstract class AbstractSortingAlgorithm implements ISortingAlgorithm {
    private final SortOptions options;
    private final LinkedList<StepResult> stepResults;
    protected Integer[] ints;

    public AbstractSortingAlgorithm(SortOptions options) {
        this.options = options;
        stepResults = new LinkedList<>();
    }

    @Override
    public LinkedList<StepResult> sort(Integer[] ints) {
        this.ints = ints;
        stepResults.add(new StepResult(new Integer[0], copyArr()));
        stepResults.addAll(sortInternal());
        return stepResults;
    }

    protected Integer[] copyArr() {
        return Arrays.copyOf(ints, ints.length);
    }

    protected abstract LinkedList<StepResult> sortInternal();
}
