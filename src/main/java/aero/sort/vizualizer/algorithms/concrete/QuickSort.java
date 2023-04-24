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

public class QuickSort extends AbstractSortingAlgorithm {
    @Override
    protected LinkedList<StepResult> sortInternal() {
        return null;
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
