// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Static utility class that provides methods to create collections.
 *
 * @author Daniel Ladendorfer
 */
public class CollectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(CollectionFactory.class);

    private CollectionFactory() {
        // static utility class - no instance needed
    }

    /**
     * Creates a list filled with all values between lowerValue and upperValue (inclusive).
     *
     * @param lowerValue the lower value (inclusive)
     * @param upperValue the upper value (inclusive)
     * @return the created list; never null
     */
    public static List<Integer> createFilledList(int lowerValue, int upperValue) {
        if (upperValue < lowerValue) {
            logger.error("Suspicious filled list creation. Upper < Lower: {} < {}", upperValue, lowerValue);
            return List.of();
        }

        return IntStream.rangeClosed(lowerValue, upperValue)
                        .boxed()
                        .toList();
    }

    /**
     * Creates an array filled with all values between lowerValue and upperValue (inclusive).
     *
     * @param lowerValue the lower value (inclusive)
     * @param upperValue the upper value (inclusive)
     * @return the created array; never null
     */
    public static Integer[] createFilledArray(int lowerValue, int upperValue) {
        if (upperValue < lowerValue) {
            logger.error("Suspicious filled list creation. Upper < Lower: {} < {}", upperValue, lowerValue);
            return new Integer[0];
        }

        return IntStream.rangeClosed(lowerValue, upperValue)
                        .boxed()
                        .toList()
                        .toArray(Integer[]::new);
    }
}
