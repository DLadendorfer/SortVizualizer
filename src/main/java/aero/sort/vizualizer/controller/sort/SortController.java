// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller.sort;

import aero.sort.vizualizer.data.options.SortSetOptions;
import aero.sort.vizualizer.data.registry.DataRegistry;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.components.desktop.SortingFrame;
import aero.sort.vizualizer.utilities.Async;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.Future;
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
        futures.stream().filter(Future::isDone).toList().forEach(futures::remove);
    }

    private void cancelRunningFutures() {
        futures.stream().filter(not(Future::isDone)).toList().forEach(Async::safeCancel);
    }

    private void invokeSort() {
        Async.invoke(() -> {
            boolean useIdenticalSet = DataRegistry.fetch(SortSetOptions.class).identical();
            var ints = useIdenticalSet ? createList() : List.<Integer>of();
            if (!GraphicsEnvironment.isHeadless()) {
                for (var frame : MainFrame.getInstance().getDesktop().getAllFrames()) {
                    if (frame instanceof SortingFrame sortingFrame) {
                        var listToUse = useIdenticalSet ? ints : createList();
                        Runnable sortTask = () -> sortingFrame.sort(listToUse.toArray(new Integer[0]));
                        futures.add(Async.submit(sortTask));
                    }
                }
            }
        });
    }

    private List<Integer> createList() {
        return createInts();
    }

    private ArrayList<Integer> createInts() {
        var options = DataRegistry.fetch(SortSetOptions.class);
        int size = options.size();
        var list = new ArrayList<>(IntStream.rangeClosed(1, size).boxed().toList());

        switch (options.duplicates()) {
            case SOME -> duplicateEntries(5, list);
            case MANY -> duplicateEntries(2, list);
            case NONE -> {
            }
        }

        return switch (options.setType()) {
            case RANDOM -> {
                Collections.shuffle(list);
                yield list;
            }
            case SORTED -> {
                Collections.sort(list);
                yield list;
            }
            case ALMOST_SORTED -> {
                almostSort(list);
                yield list;
            }
        };
    }

    private void almostSort(ArrayList<Integer> list) {
        Collections.sort(list);
        var random = new Random();
        int size = DataRegistry.fetch(SortSetOptions.class).size();
        var swaps = size / 3;
        swaps = swaps == 0 ? 1 : swaps;
        IntStream.range(0, swaps).forEach(i -> {
            var index1 = random.nextInt(size);
            var index2 = random.nextInt(size);
            var val1 = list.get(index1);
            var val2 = list.get(index2);
            list.set(index1, val2);
            list.set(index2, val1);
        });
    }

    private void duplicateEntries(int divisor, ArrayList<Integer> list) {
        var random = new Random();
        int size = DataRegistry.fetch(SortSetOptions.class).size();
        var duplications = size / divisor;
        duplications = duplications == 0 ? 1 : duplications;
        IntStream.range(0, duplications).forEach(i -> {
            var index1 = random.nextInt(size);
            var index2 = random.nextInt(size);
            list.set(index2, list.get(index1));
        });
    }
}
