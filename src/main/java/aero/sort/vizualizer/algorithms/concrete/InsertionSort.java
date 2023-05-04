// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms.concrete;

import aero.sort.vizualizer.algorithms.AbstractSortingAlgorithm;
import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.characteristics.Method;
import aero.sort.vizualizer.data.characteristics.Performance;
import aero.sort.vizualizer.data.maths.Notation;

import java.util.LinkedList;

/**
 * Implementation of Insertion-Sort.
 * Insertion-Sort can be described like this:
 * <p>
 * <code>
 * Insertion sort iterates, consuming one input element each repetition, and grows a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs
 * within the sorted list, and inserts it there. It repeats until no input elements remain.
 * </code>
 *
 * @author Daniel Ladendorfer
 */
public class InsertionSort extends AbstractSortingAlgorithm {
    @Override
    protected LinkedList<StepResult> sortInternal() {
        var stepResults = new LinkedList<StepResult>();

        int n = ints.length;
        for (int j = 1; j < n; j++) {
            int key = ints[j];
            int i = j - 1;
            stepResults.add(createStep(new Integer[]{i, j}));
            while ((i > -1) && (ints[i] > key)) {
                ints[i + 1] = ints[i];
                i--;
                stepResults.add(createStep(new Integer[]{i}));
            }
            ints[i + 1] = key;
            stepResults.add(createEmptyStep());
        }

        return stepResults;
    }

    @Override
    public boolean isStable() {
        return true;
    }

    @Override
    public Performance getPerformance() {
        return new Performance(Notation.N, Notation.N_SQUARE, Notation.N_SQUARE, Notation.ONE);
    }

    @Override
    public Method getMethod() {
        return Method.INSERTION;
    }
}
