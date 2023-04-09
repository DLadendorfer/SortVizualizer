package aero.sort.vizualizer.algorithms.concrete;

import aero.sort.vizualizer.algorithms.AbstractSortingAlgorithm;
import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.options.SortOptions;

import java.util.ArrayList;
import java.util.LinkedList;

public class BubbleSort extends AbstractSortingAlgorithm {

    public BubbleSort(SortOptions options) {
        super(options);
    }

    @Override
    protected LinkedList<StepResult> sortInternal() {
        var stepResults = new LinkedList<StepResult>();
        int n = ints.length;
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                Integer[] marked = {j - 1, j};
                if (ints[j - 1] > ints[j]) {
                    temp = ints[j - 1];
                    ints[j - 1] = ints[j];
                    ints[j] = temp;
                    stepResults.add(new StepResult(marked, copyArr()));
                }
            }
        }
        stepResults.add(new StepResult(new Integer[0], copyArr()));
        return stepResults;
    }
}
