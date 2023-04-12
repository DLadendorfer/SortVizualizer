// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller;

import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.components.desktop.SortingFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * UI-Controller of the application. Fetchable via MainFrame instance.
 *
 * @author Daniel Ladendorfer
 */
public class Controller {
    private final MainFrame mainFrame;

    public Controller(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Invoke the sorting process of all frames.
     */
    public void sort() {
        new Thread(() -> {
            var desktop = mainFrame.getDesktop();
            int arrayLength = 20;
            var ints = new ArrayList<>(IntStream.rangeClosed(1, arrayLength).boxed().toList());
            Collections.shuffle(ints);
            for (var frame : desktop.getAllFrames()) {
                if (frame instanceof SortingFrame sortingFrame) {
                    new Thread(() ->
                            sortingFrame.sort(ints.toArray(new Integer[arrayLength]))).start();
                }

                // else ? maybe there will be additional frame types in the future so we do not throw an exception
            }
        }).start();
    }
}
