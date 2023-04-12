// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms.concrete;

import aero.sort.vizualizer.algorithms.AbstractSortingAlgorithm;
import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.options.SortOptions;

import java.util.LinkedList;

public class InsertionSort extends AbstractSortingAlgorithm {
    public InsertionSort(SortOptions options) {
        super(options);
    }

    @Override
    protected LinkedList<StepResult> sortInternal() {
        var stepResults = new LinkedList<StepResult>();

        int n = ints.length;
        for (int j = 1; j < n; j++) {
            int key = ints[j];
            int i = j - 1;
            stepResults.add(new StepResult(new Integer[]{i, j}, copyArr()));
            while ((i > -1) && (ints[i] > key)) {
                ints[i + 1] = ints[i];
                i--;
                stepResults.add(new StepResult(new Integer[]{i}, copyArr()));
            }
            ints[i + 1] = key;
            stepResults.add(new StepResult(new Integer[0], copyArr()));
        }

        return stepResults;
    }
}
