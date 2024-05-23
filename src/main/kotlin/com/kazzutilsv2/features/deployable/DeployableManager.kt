package com.kazzutilsv2.features.deployable

import com.kazzutilsv2.data.enumClass.Deployable
import com.kazzutilsv2.utils.Utils
import com.kazzutilsv2.utils.Utils.removeMinecraftColorCodes
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.WorldClient
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityArmorStand
import net.minecraft.util.AxisAlignedBB
import net.minecraft.world.WorldSettings
import net.minecraft.world.WorldType
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class DeployableManager {
    private val deployablePattern = Pattern.compile("[A-Za-z ]* (?<seconds>[0-9]*)s")

    companion object{
        val DUMMY_POWER_ORB_ENTRY: DeployableEntry = DeployableEntry(Deployable.RADIANT, 20, null)
        val instance = DeployableManager()
    }

    private val powerOrbEntryMap: MutableMap<Deployable, DeployableEntry> = HashMap()

    /**
     * Put any detected orb into the list of active orbs.
     *
     * @param powerOrb Detected PowerOrb type
     * @param seconds Seconds the orb has left before running out
     * @param uuid UUID of the orb
     */
    private fun put(powerOrb: Deployable, seconds: Int, uuid: UUID) {
        powerOrbEntryMap[powerOrb] = DeployableEntry(powerOrb, seconds, uuid)
    }



    fun getActivePowerOrb(): DeployableEntry? {
        val currentTime = System.currentTimeMillis()
        val max = powerOrbEntryMap.entries
            .asSequence()
            .filter { it.value.timestamp + 100 > currentTime }
            .maxByOrNull { it.key }

        return max?.value
    }

    fun detectPowerOrb(entity: Entity) {
        val customNameTag: String = entity.customNameTag
        val powerOrb: Deployable? = Deployable.getByDisplayname(customNameTag)

        if (powerOrb != null && powerOrb.isInRadius(entity.getDistanceSqToEntity(Minecraft.getMinecraft().thePlayer))) { // TODO Make sure this works...
            val matcher: Matcher = deployablePattern.matcher(customNameTag.removeMinecraftColorCodes())

            if (matcher.matches()) {
                val seconds: Int
                try {
                    // Apparently they don't have a second count for moment after spawning, that's what this try-catch is for
                    seconds = matcher.group("seconds").toInt()
                } catch (ex: NumberFormatException) {
                    // It's okay, just don't add the power orb I guess...
                    return
                }

                val surroundingArmorStands = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(
                    EntityArmorStand::class.java,
                    AxisAlignedBB(
                        entity.posX - 0.1,
                        entity.posY - 3,
                        entity.posZ - 0.1,
                        entity.posX + 0.1,
                        entity.posY,
                        entity.posZ + 0.1
                    )
                )
                if (surroundingArmorStands.isNotEmpty()) {
                    var orbArmorStand: EntityArmorStand? = null

                    for (surroundingArmorStand in surroundingArmorStands) {
                        val helmet = surroundingArmorStand.getCurrentArmor(3)
                        if (helmet != null) {
                            orbArmorStand = surroundingArmorStand
                        }
                    }

                    orbArmorStand?.uniqueID?.let { put(powerOrb, seconds, it) }
                }
            }
        }
    }

    fun createVirtualArmorStand(armorStandToClone: EntityArmorStand): EntityArmorStand {
        val virtualArmorStand = EntityArmorStand(getDummyWorld())

        virtualArmorStand.setCurrentItemOrArmor(4, armorStandToClone.getEquipmentInSlot(4))

        return virtualArmorStand
    }


    val DUMMY_WORLD: WorldClient = WorldClient(
        null, WorldSettings(
            0L, WorldSettings.GameType.SURVIVAL,
            false, false, WorldType.DEFAULT
        ), 0, null, null
    )

    fun getDummyWorld(): WorldClient {
        return DUMMY_WORLD
    }

}


class DeployableEntry(
    deployable: Deployable,
    seconds: Int,
    uuid: UUID?
) {
    /** The PowerOrb type.  */
    val deployable: Deployable = deployable

    /** Seconds the orb has left before running out  */
    val seconds = seconds

    val timestamp = System.currentTimeMillis()

    val uuid: UUID? = uuid
}