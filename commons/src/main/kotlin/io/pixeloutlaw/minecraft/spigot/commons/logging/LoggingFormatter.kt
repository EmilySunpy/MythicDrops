package io.pixeloutlaw.minecraft.spigot.commons.logging

import java.util.Date
import java.util.logging.LogRecord
import java.util.logging.SimpleFormatter

class LoggingFormatter : SimpleFormatter() {
    private val loggingFormat: String = "[%1\$tF %1\$tT] [%2\$-7s] %3\$s - %4\$s %n"

    override fun format(record: LogRecord?): String {
        val time = record?.millis ?: System.currentTimeMillis()
        val level = record?.level?.localizedName ?: ""
        val sourceClass = record?.sourceClassName ?: ""
        val sourceMethod = record?.sourceMethodName ?: ""
        val source = "$sourceClass#$sourceMethod"
        val message = record?.message ?: ""
        return String.format(loggingFormat, Date(time), level, source, message)
    }
}