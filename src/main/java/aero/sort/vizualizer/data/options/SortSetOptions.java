// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options;

/**
 * All sort set options.
 *
 * @param identical  whether the sets of the frames are identical
 * @param size       the size of the sets
 * @param duplicates the duplicates options
 * @param setType    the set type
 */
public record SortSetOptions(boolean identical, int size, Duplicates duplicates, SetType setType) implements IOptions {
}
