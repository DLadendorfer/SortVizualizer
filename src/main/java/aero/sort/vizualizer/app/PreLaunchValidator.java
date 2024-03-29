// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.app;

import aero.sort.vizualizer.annotation.meta.Justification;
import aero.sort.vizualizer.throwables.Rethrower;
import aero.sort.vizualizer.throwables.app.ApplicationStartException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static aero.sort.vizualizer.throwables.app.ApplicationStartException.APPLICATION_START_NOT_POSSIBLE_EXIT_CODE;

/**
 * Parses passed command arguments.
 *
 * @author Daniel Ladendorfer
 */
public final class PreLaunchValidator {
    private static final Logger logger = LoggerFactory.getLogger(PreLaunchValidator.class);

    private PreLaunchValidator() {
        // utility class - no instance needed
    }

    /**
     * This method will be called prior to the start of the application. <br>
     * Command line arguments have to be handled in this method!
     * <p>
     * Throws an unhandled exception if the arguments are invalid with exit-code:
     * {@link ApplicationStartException#APPLICATION_START_NOT_POSSIBLE_EXIT_CODE}
     *
     * @param args the command line arguments
     */
    public static void validateArguments(String @NotNull [] args) {
        try {
            logger.trace("Validating arguments.");
            validate(args);
            logger.debug("Arguments valid");
        } catch (ApplicationStartException e) {
            logger.error("Unable to start the application. Aborting with exit-code: {}",
                    APPLICATION_START_NOT_POSSIBLE_EXIT_CODE);
            Rethrower.wrapAndRethrow("Cannot start app: %d".formatted(APPLICATION_START_NOT_POSSIBLE_EXIT_CODE), e);
        }
    }

    @Justification("Fail fast if the user expects arguments to be valid")
    private static void validate(String @NotNull [] args) throws ApplicationStartException {
        // this approach might be hardcore, but it is better to instantly squash false expectations
        if (args.length != 0) {
            throw new ApplicationStartException();
        }
    }
}
