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
import java.util.List;

/**
 * Implementation of Merge-Sort.
 * Merge-Sort can be described like this:
 * <p>
 * <code>
 * Divide the unsorted list into n sub-lists, each containing one element
 * (a list of one element is considered sorted).
 * Repeatedly merge sub-lists to produce new sorted sub-lists until there is only one sublist remaining.
 * This will be the sorted list.
 * </code>
 *
 * @author Daniel Ladendorfer
 */
public class MergeSort extends AbstractSortingAlgorithm {

    @Override
    protected @NotNull LinkedList<StepResult> sortInternal() {
        var stepResults = new LinkedList<StepResult>();
        mergeSort(stepResults, ints, ints.length);
        stepResults.add(createEmptyStep());
        return stepResults;
    }

    private void mergeSort(@NotNull List<StepResult> stepResults, Integer @NotNull [] arr, int n) {
        /*
            Classic implementation of this well-known algorithm.
            Hence, the cryptic variable names because they are declared like this in
            any available (web-) literature
         */
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        var l = new Integer[mid];
        var r = new Integer[n - mid];

        System.arraycopy(arr, 0, l, 0, mid);
        if (n - mid >= 0) {
            System.arraycopy(arr, mid, r, 0, n - mid);
        }
        mergeSort(stepResults, l, mid);
        mergeSort(stepResults, r, n - mid);

        merge(stepResults, arr, l, r, mid, n - mid);
    }

    private void merge(@NotNull List<StepResult> stepResults,
                       Integer[] arr, Integer[] lArr, Integer[] rArr, int left, int right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left && j < right) {
            if (lArr[i] <= rArr[j]) {
                arr[k++] = lArr[i++];
                stepResults.add(createStep(new Integer[]{left, k}));
            } else {
                arr[k++] = rArr[j++];
                stepResults.add(createStep(new Integer[]{right, k}));
            }
        }
        while (i < left) {
            arr[k++] = lArr[i++];
            stepResults.add(createStep(new Integer[]{left, k}));
        }
        while (j < right) {
            arr[k++] = rArr[j++];
            stepResults.add(createStep(new Integer[]{right, k}));
        }

        stepResults.add(createStep(new Integer[]{left, right}));
    }

    @Override
    public boolean isStable() {
        return true;
    }

    @Override
    public @NotNull Performance getPerformance() {
        return new Performance(Notation.N_LOG_N, Notation.N_LOG_N, Notation.N_LOG_N, Notation.N);
    }

    @Override
    public @NotNull Method getMethod() {
        return Method.MERGING;
    }
}
