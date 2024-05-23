package com.kazzutilsv2.data.enumClass

import net.minecraft.util.EnumChatFormatting
import net.minecraft.util.ResourceLocation

enum class Deployable(
    val display: String,
    val healthRegen: Double,
    val vitality: Int,
    val trueDefense : Int,
    val ferocity : Int,
    val attackSpeed : Double,
    val manaRegen: Double,
    val strength: Int,
    val mending : Double,
    val rangeSquared: Int,
    resourcePath: String,
    val color : EnumChatFormatting
) {
    RADIANT("§aRadiant", 0.01,0,0,0,0.0, 0.0, 0, 0.0 ,18 * 18, "radiant",EnumChatFormatting.GREEN),
    WARNINGFLARE("§aWarning Flare", 0.0, 10, 10, 0, 0.0,0.0,0,0.0, 40 * 40,"warning",EnumChatFormatting.GREEN),
    MANA_FLUX("§9Mana Flux", 0.02,0,0,0,0.0, 0.5, 10, 0.0, 18 * 18, "manaflux",EnumChatFormatting.AQUA),
    ALERTFLARE("$9Alert Flare",0.0,20,20,10,0.0,0.5,0,0.0,40 * 40, "alert",EnumChatFormatting.AQUA),
    OVERFLUX("§5Overflux", 0.025,0,0,0,0.0, 1.0, 25,5.0,  18 * 18, "overflux",EnumChatFormatting.LIGHT_PURPLE),
    PLASMAFLUX("§d§lPlasmaflux", 0.03,0,0,0,0.0, 1.25, 35, 7.5, 20 * 20, "plasmaflux",EnumChatFormatting.GOLD),
    SOSFLARE("§d§lSOS Flare",0.0,30,25,10,0.5,1.25,0,0.0,40 * 40, "sos",EnumChatFormatting.GOLD);


    val resourceLocation: ResourceLocation = ResourceLocation("kazzutilsv2", "powerorbs/$resourcePath.png")

    /**
     * Check if a distance is within this orb's radius.
     *
     * @param distanceSquared Squared distance from orb entity to player
     * @return Whether that distance is within radius
     */
    fun isInRadius(distanceSquared: Double): Boolean {
        return distanceSquared <= rangeSquared
    }

    companion object {
        /**
         * Match an entity display name against Power Orb entity names to get the corresponding type.
         *
         * @param displayName Entity display name
         * @return The matching type or null if none was found
         */
        fun getByDisplayname(displayName: String): Deployable? {
            return entries.find { displayName.startsWith(it.display) }
        }
    }
}

//data class ResourceLocation(val namespace: String, val path: String)