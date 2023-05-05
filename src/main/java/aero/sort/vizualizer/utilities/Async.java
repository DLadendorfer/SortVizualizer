// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Utility class to invoke code asynchronously.
 *
 * @author Daniel Ladendorfer
 */
public class Async {
    private static final Logger logger = LoggerFactory.getLogger(Async.class);
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    private Async() {
        // static utility class - no instance needed
    }

    /**
     * Async invocation of the given runnable. Fire and forget principle.
     *
     * @param runnable the runnable to invoke asynchronously
     */
    public static void invoke(@NotNull Runnable runnable) {
        Objects.requireNonNull(runnable, "Async::invoke does not except null as the runnable argument");
        logger.trace("Async::invoke of runnable {}", runnable);

        executor.execute(runnable);
    }

    /**
     * Async invocation of the given runnable. This method returns a {@link Future} instance to interact with.
     *
     * @param runnable the runnable to invoke asynchronously
     * @return the {@link Future}
     */
    public static @NotNull Future<Void> submit(@NotNull Runnable runnable) {
        Objects.requireNonNull(runnable, "Async::submit does not except null as the runnable argument");
        logger.trace("Async::submit of runnable {}", runnable);

        return executor.submit(runnable, null);
    }

    /**
     * Safely cancels the given future by catching the {@link InterruptedException}.
     *
     * @param future the future to cancel
     */
    public static void safeCancel(@Nullable Future<?> future) {
        if (future == null) {
            return;
        }

        try {
            logger.trace("Async::safeCancel invoked for future {}", future);
            future.cancel(true);
        } catch (Exception e) {
            logger.trace("Caught exception when interrupting Future-Thread: {}", e.getMessage());
        }
    }

    /**
     * Sleeps the current thread and catches and rethrows interrupt exceptions.
     *
     * @param millis the millis to wait
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread()
                  .interrupt();
        }
    }
}
