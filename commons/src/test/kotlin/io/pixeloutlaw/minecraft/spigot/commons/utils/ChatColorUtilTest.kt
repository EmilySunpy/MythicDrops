package io.pixeloutlaw.minecraft.spigot.commons.utils

import com.google.common.truth.Truth.assertThat
import org.bukkit.ChatColor
import org.junit.jupiter.api.Test

internal class ChatColorUtilTest {
    @Test
    fun `does findFirstColor not find color if it does not exist`() {
        val testString = "Hi"
        assertThat(ChatColorUtil.findFirstColor(testString)).isNull()
    }

    @Test
    fun `does findFirstColor find color if it exists`() {
        val testColor = ChatColor.AQUA
        val testString = "${testColor}Hi"
        val foundColor = ChatColorUtil.findFirstColor(testString)
        assertThat(foundColor).isNotNull()
        assertThat(foundColor).isEqualTo(testColor)
    }

    @Test
    fun `does getRandomChatColor return random enough ChatColors from ChatColor#values()`() {
        val mutableChatColorMap = mutableMapOf<ChatColor, Int>()
        repeat(ChatColor.values().size * 100) {
            val chatColor = ChatColorUtil.getRandomChatColor()
            mutableChatColorMap[chatColor] = mutableChatColorMap.getOrDefault(chatColor, 0) + 1
        }
        assertThat(mutableChatColorMap).hasSize(ChatColor.values().size)
        ChatColor.values().forEach {
            assertThat(mutableChatColorMap[it]).isAtLeast(10)
        }
    }

    @Test
    fun `does getRandomChatColor return random enough ChatColors from passed in values`() {
        val chatColorList = listOf(ChatColor.AQUA, ChatColor.BLACK, ChatColor.GREEN)
        val mutableChatColorMap = mutableMapOf<ChatColor, Int>()
        repeat(chatColorList.size * 100) {
            val chatColor = ChatColorUtil.getRandomChatColor(chatColorList)
            mutableChatColorMap[chatColor] = mutableChatColorMap.getOrDefault(chatColor, 0) + 1
        }
        assertThat(mutableChatColorMap).hasSize(chatColorList.size)
        chatColorList.forEach {
            assertThat(mutableChatColorMap[it]).isAtLeast(10)
        }
    }

    @Test
    fun `does getChatColor return null if str is null and fallback is null`() {
        assertThat(ChatColorUtil.getChatColor(null)).isNull()
    }

    @Test
    fun `does getChatColor return null if no matching color found and fallback is null`() {
        assertThat(ChatColorUtil.getChatColor("dankmemes")).isNull()
    }

    @Test
    fun `does getChatColor return fallback if no matching color found and fallback is not null`() {
        assertThat(ChatColorUtil.getChatColor("dankmemes", ChatColor.GOLD)).isNotNull()
    }

    @Test
    fun `does getChatColor return found color`() {
        assertThat(ChatColorUtil.getChatColor("GOLD")).isNotNull()
    }
}