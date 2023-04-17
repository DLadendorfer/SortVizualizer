// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.annotation.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Justification for strange code artifacts.
 *
 * @author Daniel Ladendorfer
 */
@Retention(RetentionPolicy.SOURCE)
@Justification("Some things just need to be justified")
public @interface Justification {
    String value();
}
