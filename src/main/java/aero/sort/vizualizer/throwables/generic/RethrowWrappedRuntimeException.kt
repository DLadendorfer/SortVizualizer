// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.throwables.generic

/**
 * Exception that explicitly holds/wraps another exception.
 *
 * @author Daniel Ladendorfer
 */
class RethrowWrappedRuntimeException(message: String?, t: Throwable) : RuntimeException(message, t)