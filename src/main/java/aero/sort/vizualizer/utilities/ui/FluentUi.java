// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities.ui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Fluent java.swing facade.
 *
 * @param <T> the concrete type of the managed {@link JComponent}
 * @author Daniel Ladendorfer
 */
public class FluentUi<T extends JComponent> {
    private final T component;

    public FluentUi(T component) {

        this.component = component;
    }

    /**
     * Adds every given component to the component of this instance.
     *
     * @param components the components to add
     * @return this
     */
    public @NotNull FluentUi<T> add(JComponent @NotNull ... components) {
        Objects.requireNonNull(components, "Components must not be null");
        Arrays.stream(components).forEach(component::add);
        return this;
    }

    /**
     * Calls {@link Consumer#accept(Object)} with the managed component.
     *
     * @param consumer the component consumer to accept the managed component
     * @return this
     */
    public @NotNull FluentUi<T> execute(@NotNull Consumer<T> consumer) {
        Objects.requireNonNull(consumer, "Consumer must not be null");
        consumer.accept(component);
        return this;
    }

    /**
     * Returns the managed component.
     *
     * @return the component
     */
    public T get() {
        return component;
    }
}
