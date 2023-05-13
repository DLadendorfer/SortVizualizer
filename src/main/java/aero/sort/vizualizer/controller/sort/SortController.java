// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller.sort;

import aero.sort.vizualizer.annotation.meta.Approval;
import aero.sort.vizualizer.controller.Controllers;
import aero.sort.vizualizer.controller.IController;
import aero.sort.vizualizer.controller.management.FrameController;
import aero.sort.vizualizer.data.options.Duplicates;
import aero.sort.vizualizer.data.options.SortSetOptions;
import aero.sort.vizualizer.data.registry.DataRegistry;
import aero.sort.vizualizer.data.shared.SharedStepToken;
import aero.sort.vizualizer.data.shared.StepInstruction;
import aero.sort.vizualizer.ui.components.desktop.SortingFrame;
import aero.sort.vizualizer.utilities.Async;
import aero.sort.vizualizer.utilities.CollectionFactory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class SortController implements IController {
    private static final Logger logger = LoggerFactory.getLogger(SortController.class);
    // holds all async sort futures (so they can be cancelled)
    private final Set<Future<?>> futures = Collections.synchronizedSet(new HashSet<>());
    private final Random random = new Random();
    private final SharedStepToken sharedStepToken = new SharedStepToken(StepInstruction.CONTINUE);

    /**
     * Invoke the sorting process of all frames.
     */
    public void sort() {
        logger.info("Starting the sorting procedure.");
        sharedStepToken.setStepInstruction(StepInstruction.CONTINUE);
        stopAndRemoveFutures();
        invokeSort();
    }

    /**
     * Stops the sorting process of all frames.
     */
    public void stop() {
        logger.info("Stopping the sorting procedure.");
        stopAndRemoveFutures();
    }

    public void pause() {
        logger.info("Pausing the sorting procedure.");
        sharedStepToken.setStepInstruction(StepInstruction.PAUSE);
    }

    public void resume() {
        logger.info("Resuming the sorting procedure.");
        sharedStepToken.setStepInstruction(StepInstruction.CONTINUE);
    }

    public void step() {
        logger.info("Stepping the sorting procedure.");
        sharedStepToken.setStepInstruction(StepInstruction.STEP);
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
               .toList()
               .forEach(Async::safeCancel);
    }

    private void invokeSort() {
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }

        Async.invoke(() -> {
            var options = DataRegistry.fetch(SortSetOptions.class);
            var ints = options.identical() ? createList() : List.<Integer>of();
            var allFrames = Controllers.fetch(FrameController.class)
                                       .getDesktop()
                                       .getAllFrames();

            for (var frame : allFrames) {
                if (frame instanceof SortingFrame sortingFrame) {
                    var listToUse = options.identical() ? ints : createList();
                    futures.add(
                            Async.submit(() -> sortingFrame.sort(sharedStepToken, listToUse.toArray(new Integer[0]))));
                }
            }
        });
    }

    private @NotNull List<Integer> createList() {
        return createInts();
    }

    private @NotNull List<Integer> createInts() {
        var options = DataRegistry.fetch(SortSetOptions.class);
        var list = CollectionFactory.createFilledList(1, options.size());

        applyDuplicates(options, list);
        return applyRandomness(options, list);
    }

    private void applyDuplicates(@NotNull SortSetOptions options, @NotNull List<Integer> list) {
        Duplicates duplicates = options.duplicates();
        if (duplicates == Duplicates.SOME) {
            duplicateEntries(5, list);
        } else if (duplicates == Duplicates.MANY) {
            duplicateEntries(2, list);
        }
    }

    @NotNull
    private List<Integer> applyRandomness(@NotNull SortSetOptions options, @NotNull List<Integer> list) {
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
            case REVERSED -> {
                list.sort(Collections.reverseOrder());
                yield list;
            }
        };
    }

    @Approval(releaseWorthy = false)
    private void almostSort(@NotNull List<Integer> list) {
        Collections.sort(list);
        int size = DataRegistry.fetch(SortSetOptions.class)
                               .size();
        var swaps = Math.min(1, size / 3);
        IntStream.range(0, swaps)
                 .forEach(i -> {
                     var index1 = random.nextInt(size);
                     var index2 = random.nextInt(size);
                     var val1 = list.get(index1);
                     var val2 = list.get(index2);
                     list.set(index1, val2);
                     list.set(index2, val1);
                 });
    }

    private void duplicateEntries(int divisor, @NotNull List<Integer> list) {
        int size = DataRegistry.fetch(SortSetOptions.class)
                               .size();
        var duplications = Math.min(1, size / divisor);
        IntStream.range(0, duplications)
                 .forEach(i -> {
                     var index1 = random.nextInt(size);
                     var index2 = random.nextInt(size);
                     list.set(index2, list.get(index1));
                 });
    }
}
