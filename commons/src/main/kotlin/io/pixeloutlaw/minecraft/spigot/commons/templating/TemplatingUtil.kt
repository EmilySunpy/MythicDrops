package io.pixeloutlaw.minecraft.spigot.commons.templating

import io.pixeloutlaw.minecraft.spigot.commons.logging.LoggerFactory
import org.apache.commons.lang.StringUtils
import java.util.regex.Pattern

object TemplatingUtil {
    private val logger = LoggerFactory.getLogger(TemplatingUtil::class.java)
    private val percentagePattern = Pattern.compile("%(?s)(.*?)%")
    private val templates = listOf(
        ColorTemplate(),
        RandTemplate(),
        RandSignTemplate(),
        RandRomanTemplate()
    )

    internal fun opsString(str: String): OpString {
        val opString = StringUtils.trimToEmpty(str).split("\\s+".toRegex(), 2).toTypedArray()
        val operation = if (opString.isNotEmpty()) opString[0] else ""
        val args = if (opString.size > 1) opString[1] else ""
        return OpString(operation, args)
    }

    fun template(string: String): String {
        var retString = string
        val m = percentagePattern.matcher(string)
        while (m.find()) {
            val check = m.group()
            val checkWithoutPercentages = check.replace("%", "")
            val opString = opsString(checkWithoutPercentages)
            logger.fine("opString=\"$opString\"")
            templates.find { it.test(opString.operation) }?.let {
                logger.fine("Templating using ${it.operation}")
                retString = StringUtils.replace(retString, check, it.invoke(opString.arguments))
            }
        }
        return retString
    }
}
