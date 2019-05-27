package io.pixeloutlaw.minecraft.spigot.commons.templating

import java.util.function.Predicate

/**
 * Represents a string template.
 */
abstract class Template(val operation: String) : Predicate<String>, Function1<String, String> {
    /**
     * Tests to see if the template handles the given operation.
     *
     * @param testString operation to test against this template's [operation]
     *
     * @return true if it handles, false otherwise
     */
    override fun test(testString: String): Boolean {
        return operation.equals(testString, true)
    }
}
