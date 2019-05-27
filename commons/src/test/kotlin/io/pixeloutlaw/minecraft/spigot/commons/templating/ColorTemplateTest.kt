package io.pixeloutlaw.minecraft.spigot.commons.templating

import com.google.common.truth.Truth
import org.bukkit.ChatColor
import org.junit.jupiter.api.Test

internal class ColorTemplateTest {
    @Test
    fun `does ColorTemplate not apply to rand`() {
        val colorTemplate = ColorTemplate()
        val argument = "rand"
        Truth.assertThat(colorTemplate.test(argument)).isFalse()
    }

    @Test
    fun `does ColorTemplate apply to color`() {
        val colorTemplate = ColorTemplate()
        val argument = "color"
        Truth.assertThat(colorTemplate.test(argument)).isTrue()
    }

    @Test
    fun `does ColorTemplate invoke with no argument return empty string`() {
        val colorTemplate = ColorTemplate()
        val argument = ""
        Truth.assertThat(colorTemplate.invoke(argument)).isEmpty()
    }

    @Test
    fun `does ColorTemplate invoke with LIGHT_PURPLE return LIGHT_PURPLE colored string`() {
        val colorTemplate = ColorTemplate()
        val argument = "LIGHT_PURPLE"
        Truth.assertThat(colorTemplate.invoke(argument)).isEqualTo("${ChatColor.LIGHT_PURPLE}")
    }

    @Test
    fun `does ColorTemplate invoke with "LIGHT PURPLE" return LIGHT_PURPLE colored string`() {
        val colorTemplate = ColorTemplate()
        val argument = "LIGHT PURPLE"
        Truth.assertThat(colorTemplate.invoke(argument)).isEqualTo("${ChatColor.LIGHT_PURPLE}")
    }

    @Test
    fun `does ColorTemplate invoke with LIGHTPURPLE return LIGHT_PURPLE colored string`() {
        val colorTemplate = ColorTemplate()
        val argument = "LIGHTPURPLE"
        Truth.assertThat(colorTemplate.invoke(argument)).isEqualTo("${ChatColor.LIGHT_PURPLE}")
    }
}