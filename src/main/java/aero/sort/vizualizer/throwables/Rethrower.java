// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.throwables;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Allows easy rethrowing a throwable.
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


    /**
     * Wraps the given throwable into a new instance of {@link RuntimeException} and re-throws it.
     * <p>
     * This is useful to not display error messages to the end user in case of non-erroneous exceptions.
     * For example: Thread-Interrupt exceptions may be an exception to the program logic but should be handled
     * silently.
     *
     * @param t any throwable instance; must not be null
     */
    public static void wrapAndRethrowSilently(Throwable t) {
        Objects.requireNonNull(t, "Cannot wrap null throwable");
        logger.trace("Wrapping throwable '{}' with message '{}'.", t.getClass(), t.getMessage());
        throw new RuntimeException(t);
    }
}
