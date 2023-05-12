// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.shared;

/**
 * The step instruction states, what a sort thread has to do after evaluating, processing and rendering
 * a step.
 *
 * @author Daniel Ladendorfer
 */
public enum StepInstruction {
    /**
     * Continue processing the next steps until done.
     */
    CONTINUE,
    /**
     * Enter a paused state, meaning that the next step should not be processed.
     */
    PAUSE,
    /**
     * Process the next and only the next step and enter the paused state afterwards.
     */
    STEP
}
