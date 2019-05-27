package io.pixeloutlaw.minecraft.spigot.commons.messaging

import com.google.common.base.Preconditions
import io.pixeloutlaw.minecraft.spigot.commons.text.TextManipulator
import org.apache.commons.lang.StringUtils
import org.bukkit.entity.Player

object MessageUtils {
    /**
     * Sends a [message] to the [player] after running it through [TextManipulator.templateWithColor].
     *
     * @param player Player to send the message to
     * @param message Message to send
     * @param args Arguments for the message
     */
    @JvmOverloads
    fun sendMessage(player: Player, message: String, args: Map<String, Any> = mapOf()) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(message))
        player.sendMessage(TextManipulator.templateWithColor(message, args))
    }

    fun sendMessage(player: Player, message: String, vararg args: Pair<String, Any>) =
        sendMessage(player, message, args.toMap())
}
