package io.pixeloutlaw.minecraft.spigot.commons.logging

import java.util.logging.Logger

object MythicLoggerFactory {
    private val loggerCache = mutableMapOf<String, Logger>()

    fun getLogger(clazz: Class<*>): Logger = loggerCache.getOrPut(clazz.canonicalName) {
        Logger.getLogger(clazz.canonicalName)
    }
}