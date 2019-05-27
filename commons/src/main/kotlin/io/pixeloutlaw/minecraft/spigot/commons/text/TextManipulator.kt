package io.pixeloutlaw.minecraft.spigot.commons.text

import io.pixeloutlaw.minecraft.spigot.commons.collections.CaselessMap
import io.pixeloutlaw.minecraft.spigot.commons.templating.TemplatingUtil
import org.bukkit.ChatColor

/**
 * Adds some extra utility over [TemplatingUtil].
 */
object TextManipulator {
    private val colorMapTemplateReady = CaselessMap<String>().apply {
        ChatColor.values().forEach {
            put(it.name, "$it")
            put(it.name.replace("_", " "), "$it")
            put(it.name.replace("_", ""), "$it")
        }
    }

    private const val startDelimiter = "%"
    private const val endDelimiter = "%"

    /**
     * Colorizes a list of Strings using multiple mappings of potential color names.
     *
     * A couple of examples that will be replaced:
     * %AQUA%
     * %LIGHT_PURPLE%
     * %LIGHTPURPLE%
     * %LIGHT PURPLE%
     *
     * @param listOfStrings list of Strings to colorize
     *
     * @return colorized Strings
     */
    fun color(listOfStrings: List<String>): List<String> {
        return template(listOfStrings, colorMapTemplateReady)
    }

    /**
     * Colorizes a String using multiple mappings of potential color names.
     *
     * A couple of examples that will be replaced:
     * %AQUA%
     * %LIGHT_PURPLE%
     * %LIGHTPURPLE%
     * %LIGHT PURPLE%
     *
     * @param string String to colorize
     *
     * @return colorized String
     */
    fun color(string: String): String {
        return template(string, colorMapTemplateReady)
    }

    /**
     * Templates a list of Strings. Keys are case sensitive.
     *
     * Replaces everything in [values] and then runs it through [TemplatingUtil.template].
     *
     * @param listOfStrings list of Strings to template
     * @param values key value mapping of things to replace in [listOfStrings]
     */
    fun template(listOfStrings: List<String>, values: Map<String, Any> = mapOf()): List<String> =
        listOfStrings.map { template(it, values) }

    /**
     * Templates a String. Keys are case sensitive.
     *
     * Replaces everything in [values] and then runs it through [TemplatingUtil.template]
     *
     * @param string String to template
     * @param values key value mapping of things to replace in [string]
     */
    fun template(
        string: String,
        values: Map<String, Any> = mapOf()
    ): String {
        var retStr = string
        values.entries.map { it.key to it.value.toString() }
            .forEach { retStr = retStr.replace("$startDelimiter${it.first}$endDelimiter", it.second) }
        return TemplatingUtil.template(retStr)
    }

    /**
     * Templates and colorizes a list of Strings. Keys are case sensitive.
     *
     * Replaces everything in [values], colorizes it, and then runs it through [TemplatingUtil.template].
     *
     * @param listOfStrings list of Strings to template
     * @param values key value mapping of things to replace in [listOfStrings]
     */
    fun templateWithColor(listOfStrings: List<String>, values: Map<String, Any> = mapOf()): List<String> =
        listOfStrings.map { templateWithColor(it, values) }

    /**
     * Templates and colorizes a String. Keys are case sensitive.
     *
     * Replaces everything in [values], colorizes it, and then runs it through [TemplatingUtil.template]
     *
     * @param string String to template
     * @param values key value mapping of things to replace in [string]
     */
    fun templateWithColor(
        string: String,
        values: Map<String, Any> = mapOf()
    ): String = TemplatingUtil.template(template(color(string), values))
}
