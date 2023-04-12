// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller.sort;

import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.components.desktop.SortingFrame;
import aero.sort.vizualizer.utilities.Async;

import java.util.*;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

/**
 * Handles the sorting invocations of UI elements.
 *
 * @author Daniel Ladendorfer
 */
public class SortController {
    // holds all async sort futures (so they can be cancelled)
    private final Set<Future<?>> futures = Collections.synchronizedSet(new HashSet<>());
    private final MainFrame mainFrame;

    public SortController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Invoke the sorting process of all frames.
     */
    public void sort() {
        stopAndRemoveFutures();
        invokeSort();
    }

    /**
     * Stops the sorting process of all frames.
     */
    public void stop() {
        stopAndRemoveFutures();
    }

    private void stopAndRemoveFutures() {
        removeDoneFutures();
        cancelRunningFutures();
    }

    private void removeDoneFutures() {
        futures.stream()
                .filter(Future::isDone)
                .toList()
                .forEach(futures::remove);
    }

    private void cancelRunningFutures() {
        futures.stream()
                .filter(not(Future::isDone))
                .forEach(Async::safeCancel);
    }

    private void invokeSort() {
        Async.invoke(() -> {
            var ints = createRandomList();
            for (var frame : mainFrame.getDesktop().getAllFrames()) {
                if (frame instanceof SortingFrame sortingFrame) {
                    Runnable sortTask = () -> sortingFrame.sort(ints.toArray(new Integer[0]));
                    futures.add(Async.submit(sortTask));
                }
            }
        });
    }

    private List<Integer> createRandomList() {
        int arrayLength = 20;
        var ints = new ArrayList<>(IntStream.rangeClosed(1, arrayLength).boxed().toList());
        Collections.shuffle(ints);
        return ints;
    }
}
