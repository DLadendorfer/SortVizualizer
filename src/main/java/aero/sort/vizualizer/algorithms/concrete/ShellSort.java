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
 * Implementation of Shell-Sort.
 * Shell-Sort can be described like this:
 * <p>
 * <code>
 * Shell sort is an in-place comparison sort that generalizes insertion sort to allow
 * the exchange of items that are far apart. The idea is to arrange the list of elements
 * so that, starting anywhere, taking every hth element produces a sorted list. Such a list
 * is said to be h-sorted. It can also be thought of as h interleaved lists, each individually sorted.
 * Beginning with large values of h allows elements to move long distances in the original list,
 * reducing large amounts of disorder quickly, and leaving less work for smaller h-sort steps to do.
 * If the list is then k-sorted for some smaller integer k, then the list remains h-sorted.
 * Following this idea for a decreasing sequence of h values ending in 1 is guaranteed to leave
 * a sorted list in the end.
 * </code>
 *
 * @author Daniel Ladendorfer
 */
public class ShellSort extends AbstractSortingAlgorithm {

    @Override
    protected @NotNull LinkedList<StepResult> sortInternal() {
        var stepResults = new LinkedList<StepResult>();
        shellSort(stepResults);
        stepResults.add(createEmptyStep());
        return stepResults;
    }

    private void shellSort(@NotNull LinkedList<StepResult> stepResults) {
        /*
         * Classic implementation using Knuth's gap sequence: h = 3*h + 1
         * The sequence is: 1, 4, 13, 40, 121, 364, 1093, ...
         * This provides good performance in practice.
         */
        int n = ints.length;

        // Start with a large gap, then reduce the gap
        // Using Knuth's sequence: h = (3^k - 1) / 2
        int gap = 1;
        while (gap < n / 3) {
            gap = 3 * gap + 1;
        }

        // Perform gapped insertion sort for each gap size
        while (gap >= 1) {
            // Do a gapped insertion sort for this gap size
            // The first gap elements ints[0..gap-1] are already in gapped order
            // Keep adding one more element until the entire array is gap sorted
            for (int i = gap; i < n; i++) {
                int temp = ints[i];
                int j = i;

                // Shift earlier gap-sorted elements up until the correct location for ints[i] is found
                while (j >= gap && ints[j - gap] > temp) {
                    stepResults.add(createStep(new Integer[]{j - gap, j}));
                    ints[j] = ints[j - gap];
                    j -= gap;
                }

                ints[j] = temp;
                stepResults.add(createStep(new Integer[]{j, i}));
            }

            // Mark completion of this gap pass
            stepResults.add(createEmptyStep());

            // Reduce gap for next iteration
            gap = gap / 3;
        }
    }

    @Override
    public boolean isStable() {
        return false;
    }

    @Override
    public @NotNull Performance getPerformance() {
        // Performance depends on gap sequence
        // Using Knuth's sequence: O(N^(3/2)) worst case
        // Best case is O(N log N), average is approximately O(N^(4/3))
        return new Performance(Notation.N_LOG_N, Notation.N_SQUARE, Notation.N_SQUARE, Notation.ONE);
    }

    @Override
    public @NotNull Method getMethod() {
        return Method.INSERTION;
    }
}