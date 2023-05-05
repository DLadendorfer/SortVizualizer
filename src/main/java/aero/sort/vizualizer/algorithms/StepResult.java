// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Record that contains the marks of a step and the result of the array after the step.
 * The metadata of a specific step is bundled into this "StepResult". It is a vital part of the application.
 * Whilst the concrete algorithms are responsible to instantiate the result, other threads might evaluate
 * based on the data of a step result. This means that immutability of the data must be ensured by
 * the whole application, by contract.
 *
 * @param marked indices to mark
 * @param ints   the new array
 * @author Daniel Ladendorfer
 */
public record StepResult(
        Integer[] marked,
        Integer[] ints
) {
    // manual equals and hashcode override due to arrays
    // SL$ S6218
    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StepResult that = (StepResult) o;
        return Arrays.equals(marked, that.marked) && Arrays.equals(ints, that.ints);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(marked);
        result = 31 * result + Arrays.hashCode(ints);
        return result;
    }

    @Override
    public @NotNull String toString() {
        return "StepResult{marked=%s, ints=%s}".formatted(Arrays.toString(marked), Arrays.toString(ints));
    }
}
