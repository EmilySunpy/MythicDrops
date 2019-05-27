package io.pixeloutlaw.minecraft.spigot.commons.templating

import com.google.common.base.Splitter
import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.math.NumberUtils
import java.util.regex.Pattern

class RandRomanTemplate : Template("randroman") {
    companion object {
        private val dashPattern = Pattern.compile("\\s*[-]\\s*")
        private const val largestRoman = 5000
        private const val smallestRoman = 1
        private val romanNumerals = mapOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I"
        )
    }

    override fun invoke(arguments: String): String {
        if (StringUtils.isEmpty(arguments)) {
            return arguments
        }
        val split = Splitter.on(dashPattern).trimResults().omitEmptyStrings().split(arguments).toList()
        return handleSplit(split)
    }

    private fun handleSplit(split: List<String>): String {
        if (split.isEmpty()) {
            return ""
        }
        val first = NumberUtils.toInt(split[0])
        val second = if (split.size > 1) NumberUtils.toInt(split[1]) else first
        val min = Math.min(first, second)
        val max = Math.max(first, second)
        val random = (min..max).random()
        return toRoman(random) ?: ""
    }

    private fun toRoman(number: Int): String? {
        if (number > largestRoman || number < smallestRoman) {
            return null
        }
        var num = number
        var result = ""
        for ((multiple, numeral) in romanNumerals.entries) {
            while (num >= multiple) {
                num -= multiple
                result += numeral
            }
        }
        return result
    }
}
