package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders

import com.google.common.base.Preconditions
import ninja.leaping.configurate.ConfigurationNode
import ninja.leaping.configurate.loader.ConfigurationLoader
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException

abstract class ConfigurateIO<T>(plugin: Plugin, protected val fileName: String) : MythicIO<T> {
    protected val fileConfigurationLoader: ConfigurationLoader<out ConfigurationNode>
    protected val resourceConfigurationLoader: ConfigurationLoader<out ConfigurationNode>
    protected val dataFolder: File = plugin.dataFolder
    protected val file: File

    init {
        file = File(dataFolder, fileName)
        Preconditions.checkArgument(createFile(file), "file must exist or be created")
        val url = plugin.javaClass.classLoader.getResource(fileName)
        fileConfigurationLoader = YAMLConfigurationLoader.builder().setPath(file.toPath()).build()
        resourceConfigurationLoader = YAMLConfigurationLoader.builder().setURL(url).build()
    }

    private fun createFile(file: File): Boolean {
        // this is really gross but kinda cool too
        return if (file.exists()) {
            true
        } else if (!file.parentFile.exists() && !file.parentFile.mkdirs()) {
            false
        } else try {
            file.createNewFile()
        } catch (e: IOException) {
            false
        } catch (e: SecurityException) {
            false
        }
    }
}