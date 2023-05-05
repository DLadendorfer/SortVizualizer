// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.annotation.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to signal if the tagged element is final-worthy.
 * <p/>
 * e.g.: Sometimes it is easier unclear for UI changes to be "final". As the preference of default values
 * or certain L&F properties might change.
 *
 * @author Daniel Ladendorfer
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Approval {

    /**
     * Whether the state of the annotated element is release worthy.
     * @return true if the state of the annotated element can be released
     */
    boolean releaseWorthy();

    /**
     * Optional comment to justify the {@link #releaseWorthy()} value.
     * @return the comment
     */
    String comment() default "";
}
