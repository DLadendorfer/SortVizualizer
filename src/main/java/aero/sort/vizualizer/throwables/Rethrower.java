package aero.sort.vizualizer.throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Allows easy rethrowing of throwables.
 *
 * @author Daniel Ladendorfer
 */
public class Rethrower {

    private static final Logger logger = LoggerFactory.getLogger(Rethrower.class);

    private Rethrower() {
        // static utility class - no instance needed
    }

    /**
     * Wraps the given throwable into a new instance of {@link RuntimeException} and re-throws it.
     *
     * @param message the new exception message; must not be null
     * @param t       any throwable instance; must not be null
     */
    public static void wrapAndRethrow(String message, Throwable t) {
        Objects.requireNonNull(message, "Message must not be null");
        Objects.requireNonNull(t, "Cannot wrap null throwable");
        logger.trace("Wrapping throwable '{}' with message '{}'.", t.getClass(), t.getMessage());
        logger.error(message);
        throw new RuntimeException(message, t);
    }
}
