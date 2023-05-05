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
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

/**
 * Implementation of Bubble-Sort.
 * Bubble-Sort can be described like this:
 * <p>
 * <code>
 * In bubble sort algorithm, array is traversed from first element to last element.
 * The current element is compared with the next element.
 * If current element is greater than the next element, it is swapped.
 * </code>
 *
 * @author Daniel Ladendorfer
 */
public class BubbleSort extends AbstractSortingAlgorithm {

    @Override
    protected @NotNull LinkedList<StepResult> sortInternal() {
        var stepResults = new LinkedList<StepResult>();
        bubbleSort(stepResults);
        stepResults.add(createEmptyStep());
        return stepResults;
    }

    private void bubbleSort(LinkedList<StepResult> stepResults) {
         /*
            Classic implementation of this well-known algorithm.
            Hence, the cryptic variable names because they are declared like this in
            any available (web-) literature
         */
        int n = ints.length;
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                Integer[] marked = {j - 1, j};
                if (ints[j - 1] > ints[j]) {
                    temp = ints[j - 1];
                    ints[j - 1] = ints[j];
                    ints[j] = temp;
                }
                stepResults.add(createStep(marked));
            }
        }
    }

    @Override
    public boolean isStable() {
        return true;
    }

    @Override
    public @NotNull Performance getPerformance() {
        return new Performance(Notation.N, Notation.N_SQUARE, Notation.N_SQUARE, Notation.ONE);
    }

    @Override
    public @NotNull Method getMethod() {
        return Method.EXCHANGING;
    }
}
