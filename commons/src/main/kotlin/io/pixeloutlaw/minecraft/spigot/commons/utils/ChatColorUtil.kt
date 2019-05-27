package io.pixeloutlaw.minecraft.spigot.commons.utils

import com.google.common.base.Preconditions
import org.bukkit.ChatColor

object ChatColorUtil {
    /**
     * Returns the first [ChatColor] found in the given [string], null otherwise.
     *
     * @param string String to check in
     */
    fun findFirstColor(string: String): ChatColor? {
        val charArray = string.toCharArray()
        val firstIndex = charArray.indexOfFirst { it == '\u00A7' }
        return if (firstIndex < 0 || (firstIndex + 1) >= charArray.size) {
            null
        } else {
            ChatColor.getByChar(charArray[firstIndex + 1])
        }
    }

    /**
     * Returns the [ChatColor] associated with the given [str] with an optional fallback.
     *
     * @param str String to convert to [ChatColor]
     * @param fallback fallback [ChatColor], defaults to null
     */
    @JvmOverloads
    fun getChatColor(str: String?, fallback: ChatColor? = null): ChatColor? {
        if (str == null) {
            return fallback
        }
        return try {
            ChatColor.valueOf(str)
        } catch (e: IllegalArgumentException) {
            fallback
        }
    }

    /**
     * Returns a random [ChatColor] from a non-empty [Collection].
     *
     * @param chatColors [Collection] of [ChatColor]s
     * @throws IllegalArgumentException if [chatColors] is empty
     */
    fun getRandomChatColor(chatColors: Collection<ChatColor>): ChatColor {
        Preconditions.checkArgument(!chatColors.isEmpty())
        return chatColors.random()
    }

    /**
     * Returns a random [ChatColor] from [ChatColor.values].
     */
    fun getRandomChatColor(): ChatColor = ChatColor.values().random()
}
