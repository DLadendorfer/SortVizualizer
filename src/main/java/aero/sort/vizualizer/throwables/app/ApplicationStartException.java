// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.throwables.app;

/**
 * Exception indicating that the app cannot start.
 *
 * @author Daniel Ladendorfer
 */
public class ApplicationStartException extends Exception {
    public static final int APPLICATION_START_NOT_POSSIBLE_EXIT_CODE = 0xDEAD & 0xFF; // yeah, what would be life without some hex-fun?
}
