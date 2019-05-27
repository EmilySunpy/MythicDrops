package io.pixeloutlaw.minecraft.spigot.mythicdrops.plugin.di.components

import dagger.BindsInstance
import dagger.Component
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.MythicDrops
import io.pixeloutlaw.minecraft.spigot.mythicdrops.plugin.MythicDropsPlugin

@Component
@MythicScope
interface PluginComponent {
    fun inject(mythicDropsPlugin: MythicDropsPlugin)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun mythicDrops(mythicDrops: MythicDrops): Builder

        fun build(): PluginComponent
    }
}