package io.pixeloutlaw.minecraft.spigot.commons.text

import com.google.common.truth.Truth.assertThat
import org.bukkit.ChatColor
import org.junit.jupiter.api.Test

internal class TextManipulatorTest {
    @Test
    fun `does color replace color names exactly`() {
        val testString = "%LIGHT_PURPLE%Hi"
        assertThat(TextManipulator.color(testString)).isEqualTo("${ChatColor.LIGHT_PURPLE}Hi")
    }

    @Test
    fun `does color replace color names with spaces instead of underscores`() {
        val testString = "%LIGHT PURPLE%Hi"
        assertThat(TextManipulator.color(testString)).isEqualTo("${ChatColor.LIGHT_PURPLE}Hi")
    }

    @Test
    fun `does color replace color names with underscores removed`() {
        val testString = "%LIGHTPURPLE%Hi"
        assertThat(TextManipulator.color(testString)).isEqualTo("${ChatColor.LIGHT_PURPLE}Hi")
    }

    @Test
    fun `does color replace color names exactly in lists`() {
        val testList = listOf("%LIGHT_PURPLE%Hi", "%AQUA%Mom")
        assertThat(TextManipulator.color(testList)).containsExactly(
            "${ChatColor.LIGHT_PURPLE}Hi",
            "${ChatColor.AQUA}Mom"
        )
    }

    @Test
    fun `does color replace color names with spaces instead of underscores in lists`() {
        val testList = listOf("%LIGHT PURPLE%Hi", "%AQUA%Mom")
        assertThat(TextManipulator.color(testList)).containsExactly(
            "${ChatColor.LIGHT_PURPLE}Hi",
            "${ChatColor.AQUA}Mom"
        )
    }

    @Test
    fun `does color replace color names with underscores removed in lists`() {
        val testList = listOf("%LIGHTPURPLE%Hi", "%AQUA%Mom")
        assertThat(TextManipulator.color(testList)).containsExactly(
            "${ChatColor.LIGHT_PURPLE}Hi",
            "${ChatColor.AQUA}Mom"
        )
    }

    @Test
    fun `does template replace all found keys`() {
        val str = "My memes are %quantifier% %descriptor%!"
        val keyValues = mapOf("quantifier" to "very", "descriptor" to "dank")
        assertThat(TextManipulator.template(str, keyValues)).isEqualTo("My memes are very dank!")
    }

    @Test
    fun `does template not replace non-found keys`() {
        val str = "My memes are %quantifier% cool and %quantifier% legal!"
        val keyValues = mapOf("quantifier" to "very", "descriptor" to "dank")

        val templated = TextManipulator.template(str, keyValues)
        assertThat(templated).isEqualTo("My memes are very cool and very legal!")
        assertThat(templated).doesNotContain("dank")
    }

    @Test
    fun `does template not replace colors`() {
        val testString = "%LIGHTPURPLE%Hi"
        assertThat(TextManipulator.template(testString)).isEqualTo(testString)
    }

    @Test
    fun `does template not replace colors in lists`() {
        val testList = listOf("%LIGHTPURPLE%Hi", "%AQUA%Mom")
        assertThat(TextManipulator.template(testList)).isEqualTo(testList)
    }

    @Test
    fun `does template replace all found keys in lists`() {
        val listStr = listOf("My memes are %quantifier% %descriptor%!", "My memes are not %quantifier% %descriptor%!")
        val keyValues = mapOf("quantifier" to "very", "descriptor" to "dank")
        assertThat(TextManipulator.template(listStr, keyValues)).containsExactly(
            "My memes are very dank!",
            "My memes are not very dank!"
        )
    }

    @Test
    fun `does template not replace non-found keys in lists`() {
        val listStr = listOf(
            "My memes are %quantifier% cool and %quantifier% legal!",
            "My memes are %quantifier% cool and not %quantifier% legal!"
        )
        val keyValues = mapOf("quantifier" to "very", "descriptor" to "dank")
        assertThat(TextManipulator.template(listStr, keyValues)).containsExactly(
            "My memes are very cool and very legal!",
            "My memes are very cool and not very legal!"
        )
    }

    @Test
    fun `does templateWithColor replace all found keys and colors`() {
        val str = "My memes are %LIGHTPURPLE%%quantifier% %descriptor%!"
        val keyValues = mapOf("quantifier" to "very", "descriptor" to "dank")
        assertThat(
            TextManipulator.templateWithColor(
                str,
                keyValues
            )
        ).isEqualTo("My memes are ${ChatColor.LIGHT_PURPLE}very dank!")
    }

    @Test
    fun `does templateWithColor not replace non-found keys and colors`() {
        val str = "My memes are %LIGHTPURPLE%%quantifier% cool and %quantifier% legal!"
        val keyValues = mapOf("quantifier" to "very", "descriptor" to "dank")

        val templateWithColord = TextManipulator.templateWithColor(str, keyValues)
        assertThat(templateWithColord).isEqualTo("My memes are ${ChatColor.LIGHT_PURPLE}very cool and very legal!")
        assertThat(templateWithColord).doesNotContain("dank")
    }

    @Test
    fun `does templateWithColor replace all found keys in lists and colors`() {
        val listStr = listOf("My memes are %LIGHTPURPLE%%quantifier% %descriptor%!", "My memes are not %LIGHTPURPLE%%quantifier% %descriptor%!")
        val keyValues = mapOf("quantifier" to "very", "descriptor" to "dank")
        assertThat(TextManipulator.templateWithColor(listStr, keyValues)).containsExactly(
            "My memes are ${ChatColor.LIGHT_PURPLE}very dank!",
            "My memes are not ${ChatColor.LIGHT_PURPLE}very dank!"
        )
    }

    @Test
    fun `does templateWithColor not replace non-found keys in lists and colors`() {
        val listStr = listOf(
            "My memes are %LIGHTPURPLE%%quantifier% cool and %quantifier% legal!",
            "My memes are %LIGHTPURPLE%%quantifier% cool and not %quantifier% legal!"
        )
        val keyValues = mapOf("quantifier" to "very", "descriptor" to "dank")
        assertThat(TextManipulator.templateWithColor(listStr, keyValues)).containsExactly(
            "My memes are ${ChatColor.LIGHT_PURPLE}very cool and very legal!",
            "My memes are ${ChatColor.LIGHT_PURPLE}very cool and not very legal!"
        )
    }
}