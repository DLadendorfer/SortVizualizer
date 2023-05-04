// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options;

import org.slf4j.event.Level;

import java.io.Serializable;

/**
 * All debug options.
 *
 * @param logLevel the debug log level
 * @author Daniel Ladendorfer
 */
public record DebugOptions(Level logLevel) implements IOptions, Serializable {
}
