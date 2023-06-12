// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.shared;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

/**
 * A shared token that states if, how and when to invoke the processing of the next step.
 * Shared means, that an instance of this class is passed to all current sort frames on sort-invoke.
 * <p/>
 * The state is stored as an {@link AtomicReference} to {@link StepInstruction}, meaning that each and every
 * thread can access and set the instruction in a thread-safe manner.
 *
 * @author Daniel Ladendorfer
 */
public class SharedStepToken {

    final @NotNull AtomicReference<StepInstruction> stepInstruction;

    public SharedStepToken(StepInstruction stepInstruction) {
        this.stepInstruction = new AtomicReference<>(stepInstruction);
    }

    public StepInstruction getStepInstruction() {
        return stepInstruction.get();
    }

    public void setStepInstruction(StepInstruction stepInstruction) {
        this.stepInstruction.set(stepInstruction);
    }
}
