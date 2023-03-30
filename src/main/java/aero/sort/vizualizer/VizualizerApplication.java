package aero.sort.vizualizer;

import aero.sort.vizualizer.app.Application;
import aero.sort.vizualizer.app.PreLaunchHook;

/**
 * Entry point of the application.
 * This class should only contain the main method and handles argument parsing.
 */
public class VizualizerApplication {

    public static void main(String[] args) {
        PreLaunchHook.validateArguments(args);
        Application.launch();
    }
}
