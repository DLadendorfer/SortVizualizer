// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.registry;

import aero.sort.vizualizer.annotation.meta.Justification;
import aero.sort.vizualizer.data.options.IOptions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Class that holds different instance of options which can be retrieved globally.
 *
 * @author Daniel Ladendorfer
 */
public class DataRegistry {

    private static final Map<Class<? extends IOptions>, Supplier<IOptions>> registry = new ConcurrentHashMap<>();

    /**
     * Registers an option supplier.
     *
     * @param clazz    the concrete Options class
     * @param supplier the supplier
     */
    public static void registerOptionsSupplier(Class<? extends IOptions> clazz, Supplier<IOptions> supplier) {
        registry.put(clazz, supplier);
    }


    @SuppressWarnings("unchecked")
    @Justification("This cast cannot fail")
    public static <T extends IOptions> T get(Class<T> clazz) {
        return (T) registry.get(clazz).get();
    }
}
