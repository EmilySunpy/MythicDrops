package io.pixeloutlaw.minecraft.spigot.commons.collections

import com.google.common.truth.Truth.assertThat
import org.bukkit.ChatColor
import org.junit.jupiter.api.Test

internal class CaselessMapTest {
    private val colorMapTemplateReady = CaselessMap<String>().apply {
        ChatColor.values().forEach {
            put("%${it.name}%", "$it")
            put("%${it.name}%".replace("_", " "), "$it")
            put("%${it.name}%".replace("_", ""), "$it")
        }
    }

    @Test
    fun `does it find entries ignoring case`() {
        assertThat(colorMapTemplateReady).containsKey("%AQUA%")
        assertThat(colorMapTemplateReady).containsKey("%Aqua%")
        assertThat(colorMapTemplateReady).containsKey("%aqua%")
        assertThat(colorMapTemplateReady).containsKey("%aQuA%")
    }
}