package io.pixeloutlaw.minecraft.spigot.commons.collections

fun CaselessMap<String>.mergeReduce(
    other: Map<String, Any>,
    reduce: (String, String) -> String = { _, b -> b }
): Map<String, String> {
    val result = LinkedHashMap<String, String>(this)
    for ((key, value) in other) {
        result.merge(key, value.toString(), reduce)
    }
    return result
}