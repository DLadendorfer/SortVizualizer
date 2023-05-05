// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.annotation.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Justification for strange code artifacts which may look nonsensical at first glance.
 * e.g.: This is a Java-SWING application. It has many unit tests but some of them won't run
 * on GitHub's CI toolchain without injecting or mocking certain instances.
 *
 * @author Daniel Ladendorfer
 */
@Retention(RetentionPolicy.SOURCE)
@Justification("Some things just need to be justified")
public @interface Justification {
    String value();
}
