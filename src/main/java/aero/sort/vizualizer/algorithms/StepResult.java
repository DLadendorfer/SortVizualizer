package aero.sort.vizualizer.algorithms;

/**
 * Record that contains the marks of a step and the result of the array after the step.
 * @param marked indices to mark
 * @param ints the new array
 */
public record StepResult(Integer[] marked, Integer[] ints) {
}
