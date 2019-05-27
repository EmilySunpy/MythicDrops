package io.pixeloutlaw.minecraft.spigot.commons.templating

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class RandRomanTemplateTest {
    @Test
    fun `does RandRomanTemplate apply to rand`() {
        val randTemplate = RandRomanTemplate()
        val argument = "randroman"
        assertThat(randTemplate.test(argument)).isTrue()
    }

    @Test
    fun `does RandRomanTemplate not apply to randsign`() {
        val randTemplate = RandRomanTemplate()
        val argument = "randsign"
        assertThat(randTemplate.test(argument)).isFalse()
    }

    @Test
    fun `does RandRomanTemplate invoke return empty string for empty string`() {
        val randTemplate = RandRomanTemplate()
        val argument = ""
        assertThat(randTemplate.invoke(argument)).isEqualTo("")
    }

    @Test
    fun `does RandRomanTemplate invoke return "I" for "1"`() {
        val randTemplate = RandRomanTemplate()
        val argument = "1"
        assertThat(randTemplate.invoke(argument)).isEqualTo("I")
    }

    @Test
    fun `does RandRomanTemplate invoke return "1" for "1-1"`() {
        val randTemplate = RandRomanTemplate()
        val argument = "1-1"
        assertThat(randTemplate.invoke(argument)).isEqualTo("I")
    }

    @Test
    fun `does RandRomanTemplate invoke return "1", "2", or "3" for "1-3"`() {
        val randTemplate = RandRomanTemplate()
        val argument = "1-3"
        assertThat(randTemplate.invoke(argument)).isIn(listOf("I", "II", "III"))
    }
}