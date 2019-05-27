package io.pixeloutlaw.minecraft.spigot.commons.templating

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class RandTemplateTest {
    @Test
    fun `does RandTemplate apply to rand`() {
        val randTemplate = RandTemplate()
        val argument = "rand"
        assertThat(randTemplate.test(argument)).isTrue()
    }

    @Test
    fun `does RandTemplate not apply to randsign`() {
        val randTemplate = RandTemplate()
        val argument = "randsign"
        assertThat(randTemplate.test(argument)).isFalse()
    }

    @Test
    fun `does RandTemplate invoke return empty string for empty string`() {
        val randTemplate = RandTemplate()
        val argument = ""
        assertThat(randTemplate.invoke(argument)).isEqualTo("")
    }

    @Test
    fun `does RandTemplate invoke return "1" for "1"`() {
        val randTemplate = RandTemplate()
        val argument = "1"
        assertThat(randTemplate.invoke(argument)).isEqualTo("1")
    }

    @Test
    fun `does RandTemplate invoke return "1" for "1-1"`() {
        val randTemplate = RandTemplate()
        val argument = "1-1"
        assertThat(randTemplate.invoke(argument)).isEqualTo("1")
    }

    @Test
    fun `does RandTemplate invoke return "1", "2", or "3" for "1-3"`() {
        val randTemplate = RandTemplate()
        val argument = "1-3"
        assertThat(randTemplate.invoke(argument)).isIn(listOf("1", "2", "3"))
    }
}