// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller;

import aero.sort.vizualizer.annotation.meta.Justification;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Manages access to the UI controllers.
 *
 */
public class Controllers {

    private static final Map<Class<? extends IController>, Supplier<IController>> registerdControllers = new ConcurrentHashMap<>();

    private Controllers() {
        // static utility class - no instance needed
    }

    /**
     * Registers a controller supplier.
     *
     * @param clazz    the concrete controller class
     * @param supplier the supplier
     */
    public static <T extends IController> void registerControllerSupplier(Class<T> clazz, Supplier<IController> supplier) {
        registerdControllers.put(clazz, supplier);
    }


    /**
     * Fetches the current data of the specified controller class.
     *
     * @param clazz the controller class
     * @param <T>   the concrete type of {@link IController}
     * @return the current data
     */
    @SuppressWarnings("unchecked")
    @Justification("This cast cannot fail")
    public static <T extends IController> T fetch(Class<T> clazz) {
        return (T) registerdControllers.get(clazz).get();
    }

}
