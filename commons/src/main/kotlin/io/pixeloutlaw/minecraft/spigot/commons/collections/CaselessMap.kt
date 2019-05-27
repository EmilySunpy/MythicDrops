package io.pixeloutlaw.minecraft.spigot.commons.collections

import java.util.TreeMap

class CaselessMap<V>: TreeMap<String, V>(String.CASE_INSENSITIVE_ORDER)
