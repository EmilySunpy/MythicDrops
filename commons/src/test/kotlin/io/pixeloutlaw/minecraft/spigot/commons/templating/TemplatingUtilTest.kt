package io.pixeloutlaw.minecraft.spigot.commons.templating

import com.google.common.truth.Truth
import org.bukkit.ChatColor
import org.junit.jupiter.api.Test

internal class TemplatingUtilTest {
    @Test
    fun `does opsString properly parse "rand 2-4"`() {
        val randTemplateString = "rand 2-4"
        val randOpsString = TemplatingUtil.opsString(randTemplateString)
        Truth.assertThat(randOpsString.operation).isEqualTo("rand")
        Truth.assertThat(randOpsString.arguments).isEqualTo("2-4")
    }

    @Test
    fun `does opsString properly parse "randroman"`() {
        val randromanTemplateString = "randroman"
        val randromanOpsString = TemplatingUtil.opsString(randromanTemplateString)
        Truth.assertThat(randromanOpsString.operation).isEqualTo("randroman")
        Truth.assertThat(randromanOpsString.arguments).isEqualTo("")
    }

    @Test
    fun `does opsString properly parse "randsign"`() {
        val randsignTemplateString = "randsign"
        val randsignOpsString = TemplatingUtil.opsString(randsignTemplateString)
        Truth.assertThat(randsignOpsString.operation).isEqualTo("randsign")
        Truth.assertThat(randsignOpsString.arguments).isEqualTo("")
    }

    @Test
    fun `does "+%rand 2-4% Memes" return "+2 Memes", "+3 Memes", or "+4 Memes"`() {
        val randTemplateString = "+%rand 2-4% Memes"
        Truth.assertThat(TemplatingUtil.template(randTemplateString)).isIn(listOf(
            "+2 Memes",
            "+3 Memes",
            "+4 Memes"
        ))
    }

    @Test
    fun `does "+%randroman 2-4% Memes" return "+II Memes", "+III Memes", or "+IV Memes"`() {
        val randTemplateString = "+%randroman 2-4% Memes"
        Truth.assertThat(TemplatingUtil.template(randTemplateString)).isIn(listOf(
            "+II Memes",
            "+III Memes",
            "+IV Memes"
        ))
    }

    @Test
    fun `does "%randsign%3 Memes return "+3 Memes" or "-3 Memes"`() {
        val randsignTemplateString = "%randsign%3 Memes"
        Truth.assertThat(TemplatingUtil.template(randsignTemplateString)).isIn(listOf(
            "+3 Memes",
            "-3 Memes"
        ))
    }

    @Test
    fun `does "%randsign%%rand 2-4% Memes" return the appropriate values`() {
        val randsignTemplateString = "%randsign%%rand 2-4% Memes"
        Truth.assertThat(TemplatingUtil.template(randsignTemplateString)).isIn(listOf(
            "+2 Memes",
            "-2 Memes",
            "+3 Memes",
            "-3 Memes",
            "+4 Memes",
            "-4 Memes"
        ))
    }

    @Test
    fun `does "%color AQUA% Memes return "Memes" colored AQUA`() {
        val randsignTemplateString = "%color AQUA%3 Memes"
        Truth.assertThat(TemplatingUtil.template(randsignTemplateString)).isEqualTo("${ChatColor.AQUA}3 Memes")
    }
}