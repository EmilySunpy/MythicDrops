package io.pixeloutlaw.minecraft.spigot.commons.templating

import org.apache.commons.lang.math.RandomUtils

class RandSignTemplate: Template("randsign") {
    override fun invoke(arguments: String): String = if (RandomUtils.nextBoolean()) "+" else "-"
}