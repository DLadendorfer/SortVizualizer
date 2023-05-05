// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer;

import aero.sort.vizualizer.app.Application;
import aero.sort.vizualizer.app.PreLaunchHook;
import org.jetbrains.annotations.NotNull;

/**
 * Entry point of the application.
 * This class should only contain the main method and handles argument parsing.
 *
 * @author Daniel Ladendorfer
 */
public class VizualizerApplication {

    public static void main(String @NotNull [] args) {
        PreLaunchHook.validateArguments(args);
        Application.launch();
    }
}
