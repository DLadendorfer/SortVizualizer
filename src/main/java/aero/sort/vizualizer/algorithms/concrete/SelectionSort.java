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
 * Implementation of Selection-Sort.
 * Selection-Sort can be described like this:
 * <p>
 * <code>
 * The algorithm divides the input list into two parts:
 * a sorted sublist of items which is built up from left to right at the front (left) of the list
 * and a sublist of the remaining unsorted items that occupy the rest of the list.
 * <p>
 * Initially, the sorted sublist is empty and the unsorted sublist is the entire input list.
 * The algorithm proceeds by finding the smallest (or largest, depending on sorting order) element
 * in the unsorted sublist, exchanging (swapping) it with the leftmost unsorted element
 * (putting it in sorted order), and moving the sublist boundaries one element to the right.
 * </code>
 *
 * @author Daniel Ladendorfer
 */
public class SelectionSort extends AbstractSortingAlgorithm {

    @Override
    protected @NotNull LinkedList<StepResult> sortInternal() {
        var stepResults = new LinkedList<StepResult>();
        for (int i = 0; i < ints.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < ints.length; j++) {
                stepResults.add(createStep(new Integer[]{i, index, j}));
                if (ints[j] < ints[index]) {
                    index = j; //searching for lowest index
                }
            }
            int smallerNumber = ints[index];
            ints[index] = ints[i];
            ints[i] = smallerNumber;
            stepResults.add(createEmptyStep());
        }

        return stepResults;
    }

    @Override
    public boolean isStable() {
        return false;
    }

    @Override
    public @NotNull Performance getPerformance() {
        return new Performance(Notation.N_SQUARE, Notation.N_SQUARE, Notation.N_SQUARE, Notation.ONE);
    }

    @Override
    public @NotNull Method getMethod() {
        return Method.SELECTION;
    }
}
