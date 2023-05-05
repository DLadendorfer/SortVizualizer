// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Abstract implementation for all sorting algorithms. Does a lot of heavy lifting which reoccur in
 * every algorithm. Mostly it should abstract the population of the step-result-list.
 *
 * @author Daniel Ladendorfer
 */
public abstract class AbstractSortingAlgorithm implements ISortingAlgorithm {
    private static final Integer[] EMPTY_ARRAY = {};
    private final @NotNull LinkedList<StepResult> stepResults;
    private final Logger logger;

    // the concrete array to sort. This is a mutating array!
    protected Integer[] ints;

    protected AbstractSortingAlgorithm() {
        stepResults = new LinkedList<>();
        logger = LoggerFactory.getLogger("Algorithm-%s".formatted(getClass().getSimpleName()));
    }

    @Override
    public @NotNull LinkedList<StepResult> sort(Integer @NotNull [] ints) {
        this.ints = ints;

        logger.debug("Starting to sort {}", Arrays.stream(ints)
                                                  .toList());
        stepResults.add(createEmptyStep());
        stepResults.addAll(sortInternal());
        return stepResults;
    }

    /**
     * Creates an empty (no marked) step with a copy of the array.
     *
     * @return a new empty step
     */
    protected final @NotNull StepResult createEmptyStep() {
        return createStep(EMPTY_ARRAY);
    }

    /**
     * Creates a step with the given marked indices and a copy of the array.
     *
     * @param marked the marked indices
     * @return a new step
     */
    protected final @NotNull StepResult createStep(final Integer @Nullable [] marked) {
        // null safety just to be sure
        var markedIndices = marked == null ? EMPTY_ARRAY : marked;
        return new StepResult(markedIndices, copyInternalArray());
    }

    /**
     * Creates a copy of the current array state.
     *
     * @return a copy of the current array state
     */
    protected Integer @NotNull [] copyInternalArray() {
        return Arrays.copyOf(ints, ints.length);
    }

    /**
     * The concrete implementation of the sort. This is the method that the concrete implementations
     * should implement (without the boilerplate code of this abstract class).
     *
     * @return the steps to sort the initial input
     */
    protected abstract LinkedList<StepResult> sortInternal();
}
