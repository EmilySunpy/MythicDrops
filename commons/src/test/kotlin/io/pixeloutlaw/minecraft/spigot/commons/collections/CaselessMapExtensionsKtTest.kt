package io.pixeloutlaw.minecraft.spigot.commons.collections

import com.google.common.truth.Truth.assertThat
import org.bukkit.ChatColor
import org.junit.jupiter.api.Test

internal class CaselessMapExtensionsKtTest {
    private val colorMapTemplateReady = CaselessMap<String>().apply {
        ChatColor.values().forEach {
            put("%${it.name}%", "$it")
            put("%${it.name}%".replace("_", " "), "$it")
            put("%${it.name}%".replace("_", ""), "$it")
        }
    }
    private data class Meme(val dankness: Int)

    @Test
    fun `does mergeReduce add String keys and String values into returned map`() {
        val mapFromPairs = mapOf("hi" to "mom", "dank" to "memes")

        val mergedMaps = colorMapTemplateReady.mergeReduce(mapFromPairs)

        assertThat(mergedMaps).containsEntry("hi", "mom")
        assertThat(mergedMaps).containsEntry("dank", "memes")
    }

    @Test
    fun `does mergeReduce add String keys and non-String values into returned map`() {
        val mapFromPairs = mapOf("hi" to 4, "dank" to Meme(4))

        val mergedMaps = colorMapTemplateReady.mergeReduce(mapFromPairs)

        assertThat(mergedMaps).containsEntry("hi", "4")
        assertThat(mergedMaps).containsEntry("dank", "Meme(dankness=4)")
    }
}