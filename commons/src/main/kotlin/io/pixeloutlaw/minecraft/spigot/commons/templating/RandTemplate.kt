package io.pixeloutlaw.minecraft.spigot.commons.templating

import com.google.common.base.Splitter
import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.math.NumberUtils
import java.util.regex.Pattern

class RandTemplate : Template("rand") {
    companion object {
        private val dashPattern = Pattern.compile("\\s*[-]\\s*")
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
        return random.toString()
    }
}
