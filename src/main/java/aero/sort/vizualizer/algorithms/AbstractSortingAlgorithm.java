// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Abstract implementation for all sorting algorithms.
 *
 * @author Daniel Ladendorfer
 */
public abstract class AbstractSortingAlgorithm implements ISortingAlgorithm {
    private static final Integer[] EMPTY_ARRAY = {};
    private final LinkedList<StepResult> stepResults;
    private final Logger logger;
    protected Integer[] ints;

    protected AbstractSortingAlgorithm() {
        stepResults = new LinkedList<>();
        logger = LoggerFactory.getLogger("Algorithm-%s".formatted(getClass().getSimpleName()));
    }

    @Override
    public LinkedList<StepResult> sort(Integer[] ints) {
        logger.debug("Starting to sort {}", Arrays.stream(ints).toList());
        this.ints = ints;
        stepResults.add(createEmptyStep());
        stepResults.addAll(sortInternal());
        return stepResults;
    }

    /**
     * Creates an empty (no marked) step with a copy of the array.
     *
     * @return a new empty step
     */
    protected final StepResult createEmptyStep() {
        return createStep(EMPTY_ARRAY);
    }

    /**
     * Creates a step with the given marked indices and a copy of the array.
     *
     * @param marked the marked indices
     * @return a new step
     */
    protected final StepResult createStep(final Integer[] marked) {
        // null safety just to be sure
        var markedIndices = marked == null ? EMPTY_ARRAY : marked;

        logger.trace("Creating step result with marked {}", Arrays.stream(markedIndices).toList());

        return new StepResult(markedIndices, copyArr());
    }

    /**
     * Creates a copy of the current array state.
     *
     * @return a copy of the current array state
     */
    protected Integer[] copyArr() {
        return Arrays.copyOf(ints, ints.length);
    }

    /**
     * The concrete implementation of the sort.
     *
     * @return the steps to sort the initial input
     */
    protected abstract LinkedList<StepResult> sortInternal();
}
