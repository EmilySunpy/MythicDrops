package io.pixeloutlaw.minecraft.spigot.mythicdrops.plugin

import com.google.common.base.Strings
import io.pixeloutlaw.minecraft.spigot.commons.logging.LoggerFactory
import io.pixeloutlaw.minecraft.spigot.commons.logging.LoggingFormatter
import io.pixeloutlaw.minecraft.spigot.hilt.getDisplayName
import io.pixeloutlaw.minecraft.spigot.hilt.setDisplayName
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.MythicDrops
import io.pixeloutlaw.minecraft.spigot.mythicdrops.plugin.di.components.DaggerPluginComponent
import io.pixeloutlaw.minecraft.spigot.mythicdrops.plugin.di.components.PluginComponent
import io.pixeloutlaw.mythicdrops.plugin.BuildConfig
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.annotation.plugin.ApiVersion
import org.bukkit.plugin.java.annotation.plugin.Description
import org.bukkit.plugin.java.annotation.plugin.Plugin
import org.bukkit.plugin.java.annotation.plugin.author.Author
import java.util.logging.FileHandler
import java.util.logging.Handler
import java.util.logging.Logger

@Plugin(name = BuildConfig.NAME, version = BuildConfig.VERSION)
@Description("A Spigot plugin for RPG-style drops.")
@Author("Richard Harrah")
@ApiVersion(ApiVersion.Target.v1_13)
class MythicDropsPlugin : JavaPlugin(), MythicDrops, Listener {
    private val mythicLogger = LoggerFactory.getLogger(javaClass)
    private val pluginComponent: PluginComponent by lazy {
        DaggerPluginComponent.builder().mythicDrops(this).build()
    }
    private lateinit var logHandler: Handler

    override fun onEnable() {
        if (!dataFolder.exists() && !dataFolder.mkdirs()) {
            logger.severe("Unable to create data folder!")
            Bukkit.getPluginManager().disablePlugin(this)
            return
        }

        setupLogging()

        pluginComponent.inject(this)

        server.pluginManager.registerEvents(this, this)
        mythicLogger.info("onEnable - plugin ${BuildConfig.VERSION} api ${io.pixeloutlaw.mythicdrops.api.BuildConfig.VERSION}")
    }

    override fun onDisable() {
        mythicLogger.info("onDisable")
        teardownLogging()
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onBlockDamageEvent(event: BlockDamageEvent) {
        val itemInHand = event.itemInHand
        itemInHand.setDisplayName("${Strings.nullToEmpty(itemInHand.getDisplayName())}d")
        event.player.updateInventory()
    }

    private fun setupLogging() {
        val pathToLogOutput = String.format("%s/mythicdrops.log", dataFolder.absolutePath)
        logHandler = FileHandler(pathToLogOutput, true)
        logHandler.formatter = LoggingFormatter()
        Logger.getLogger("io.pixeloutlaw.minecraft.spigot").also {
            it.useParentHandlers = false
            it.addHandler(logHandler)
        }
    }

    private fun teardownLogging() {
        if (::logHandler.isInitialized) {
            Logger.getLogger("io.pixeloutlaw.minecraft.spigot").removeHandler(logHandler)
        }
    }
}