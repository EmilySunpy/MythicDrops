package io.pixeloutlaw.minecraft.spigot.commons.templating

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class RandSignTemplateTest {
    @Test
    fun `does RandSignTemplate not apply to rand`() {
        val randTemplate = RandSignTemplate()
        val argument = "rand"
        assertThat(randTemplate.test(argument)).isFalse()
    }

    @Test
    fun `does RandSignTemplate apply to randsign`() {
        val randTemplate = RandSignTemplate()
        val argument = "randsign"
        assertThat(randTemplate.test(argument)).isTrue()
    }

    @Test
    fun `does RandSignTemplate invoke return "+" or "-"`() {
        val randTemplate = RandSignTemplate()
        val argument = ""
        assertThat(randTemplate.invoke(argument)).isIn(listOf("+", "-"))
    }
}