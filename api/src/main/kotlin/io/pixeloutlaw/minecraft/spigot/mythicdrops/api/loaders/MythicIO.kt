package io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders

/**
 * An interface defining an easy way to load and save objects.
 *
 * @param T type of object to load/save
 */
interface MythicIO<T> {
    /**
     * Loads the object(s) it is configured to load.
     *
     * @return loaded object(s)
     */
    fun load(): T

    /**
     * Save the object(s) it is configured to save.
     *
     * @param t object(s) to save
     */
    fun save(t: T)
}