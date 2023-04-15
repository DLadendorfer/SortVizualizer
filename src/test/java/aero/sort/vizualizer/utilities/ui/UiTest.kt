// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities.ui

import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import javax.swing.*

/**
 * Tests [Ui].
 *
 * @author Daniel Ladendorfer
 */
internal class UiTest {
    @ParameterizedTest(name = "Component [{0}] can be 'used' by Ui")
    @MethodSource("componentProvider")
    fun `Tests if component of type X creates an instance of FluentUi`(component: JComponent) {
        val com = Ui.using(component).get()
        assertSame(component, com) {
            "Ui managed component is not the same as the passed component"
        }
    }

    companion object {
        @JvmStatic
        fun componentProvider(): Stream<Arguments> {
            return Stream.of(
                *arrayOf<JComponent>(
                    JPanel(), JButton(), JLabel(), JComboBox(arrayOf("t"))
                ).map { Arguments.of(it) }.toTypedArray()
            )
        }
    }
}