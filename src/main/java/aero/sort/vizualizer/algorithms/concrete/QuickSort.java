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
import java.util.List;

/**
 * Implementation of Quick-Sort.
 * Quick-Sort can be described like this:
 * <p>
 * <code>
 * Quicksort is a divide-and-conquer algorithm. It works by selecting a 'pivot' element from
 * the array and partitioning the other elements into two sub-arrays, according to whether
 * they are less than or greater than the pivot. For this reason, it is sometimes called
 * partition-exchange sort. The sub-arrays are then sorted recursively. This can be done
 * in-place, requiring small additional amounts of memory to perform the sorting.
 * <p>
 * Quicksort is a comparison sort, meaning that it can sort items of any type for which a "less-than"
 * relation (formally, a total order) is defined. Most implementations of quicksort are not stable,
 * meaning that the relative order of equal sort items is not preserved.
 * </code>
 *
 * @author Daniel Ladendorfer
 */
public class QuickSort extends AbstractSortingAlgorithm {

    /**
     * Function that considers last element as pivot, places the pivot at its exact position and
     * place smaller elements to left of pivot and greater elements to right of pivot.
     *
     * @param stepResults stepResult list
     * @param arr array
     * @param start start index
     * @param end end index
     * @return the partition index
     */
    private int partition(List<StepResult> stepResults, Integer[] arr, int start, int end) {
        int pivotElement = arr[end];
        int i = (start - 1);

        for (int j = start; j <= end - 1; j++) {
            stepResults.add(createStep(new Integer[] {pivotElement, i + 1, j}));
            if (arr[j] < pivotElement) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            stepResults.add(createStep(new Integer[] {pivotElement}));
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;
        stepResults.add(createEmptyStep());
        return (i + 1);
    }

    private void quickSort(List<StepResult> stepResults, Integer[] arr, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(stepResults, arr, start, end);
            quickSort(stepResults, arr, start, partitionIndex - 1);
            quickSort(stepResults, arr, partitionIndex + 1, end);
        }
    }

    @Override
    protected LinkedList<StepResult> sortInternal() {
        var stepResults = new LinkedList<StepResult>();
        quickSort(stepResults, ints, 0, ints.length - 1);
        return stepResults;
    }

    @Override
    public boolean isStable() {
        return false;
    }

    @Override
    public Performance getPerformance() {
        return new Performance(Notation.N_LOG_N, Notation.N_LOG_N, Notation.N_SQUARE, Notation.LOG_N);
    }

    @Override
    public Method getMethod() {
        return Method.PARTITIONING;
    }
}
