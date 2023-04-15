// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.algorithms;

/**
 * Record that contains the marks of a step and the result of the array after the step.
 *
 * @param marked indices to mark
 * @param ints   the new array
 */
public record StepResult(Integer[] marked, Integer[] ints) {
}
