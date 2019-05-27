package io.pixeloutlaw.minecraft.spigot.commons.templating

import io.pixeloutlaw.minecraft.spigot.commons.collections.CaselessMap
import org.apache.commons.lang.StringUtils
import org.bukkit.ChatColor

class ColorTemplate : Template("color") {
    private val chatColors = CaselessMap<ChatColor>().apply {
        ChatColor.values().forEach {
            put(it.name, it)
            put(it.name.replace("_", " "), it)
            put(it.name.replace("_", ""), it)
        }
    }

    override fun invoke(arguments: String): String {
        if (StringUtils.isEmpty(arguments)) {
            return arguments
        }
        return "${chatColors[arguments]}"
    }
}