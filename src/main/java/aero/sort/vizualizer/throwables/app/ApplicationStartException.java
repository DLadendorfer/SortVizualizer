package aero.sort.vizualizer.throwables.app;

/**
 * Exception indicating that the app cannot start.
 *
 * @author Daniel Ladendorfer
 */
public class ApplicationStartException extends Exception {
    public static final int APPLICATION_START_NOT_POSSIBLE_EXIT_CODE = 0xDEAD;
}
