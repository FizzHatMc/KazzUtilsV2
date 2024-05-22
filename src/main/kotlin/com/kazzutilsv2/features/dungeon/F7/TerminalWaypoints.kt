package com.kazzutilsv2.features.dungeon.F7

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.data.enumClass.DunClass
import com.kazzutilsv2.data.m7.coords.TermCoords
import com.kazzutilsv2.utils.CatacombsUtils
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.RenderUtils
import com.kazzutilsv2.utils.TabUtils
import net.minecraft.client.Minecraft
import net.minecraft.util.BlockPos
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.awt.Color

class TerminalWaypoints {

    @SubscribeEvent
    fun onWorldRender(event: RenderWorldLastEvent) {
        if(!KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypoints && !KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalText) return
        val player = Minecraft.getMinecraft().thePlayer

        var playersClassAsInt : Int = 6

        for(s in DunClass.entries) {
            //ChatUtils.messageToChat(TabUtils.playerClass + " | " + s.classNameAsString)
            if(s.classNameAsString == TabUtils.playerClass) playersClassAsInt = s.classNum
        }
        //ChatUtils.messageToChat("PlayerClassASInt: $playersClassAsInt")
        if (player.posY < 145 && player.posY > 100 && player.posX < 115 && player.posX > 0 && player.posZ < 150 && player.posZ > 20 && (CatacombsUtils.floor.contains("F7") || CatacombsUtils.inM7)) {
            val viewer = Minecraft.getMinecraft().renderViewEntity

            val viewerX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * event.partialTicks
            val viewerY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * event.partialTicks
            val viewerZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * event.partialTicks

            when (playersClassAsInt) {
                0 -> {
                    var i = 0
                    while (i < 4) {
                        val blockPos: BlockPos? = TermCoords.getTankCoords(i)
                        val x = blockPos?.x?.minus(viewerX)
                        val y = blockPos?.y?.minus(viewerY)
                        val z = blockPos?.z?.minus(viewerZ)

                        val color = Color(KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypointColor.toChromaColorInt())

                        if (KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypoints) RenderUtils.renderBeaconBeam(
                            x!!,
                            y!!,
                            z!!,
                            color.rgb,
                            color.alpha.toFloat(),
                            event.partialTicks
                        )
                        if (KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalText) RenderUtils.renderWaypointText(KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypointText, blockPos!!, event.partialTicks)
                        i++
                    }
                }

                1 -> {
                    var i = 0
                    while (i < 4) {
                        val blockPos: BlockPos? = TermCoords.getMageCoords(i)
                        val x = blockPos?.x?.minus(viewerX)
                        val y = blockPos?.y?.minus(viewerY)
                        val z = blockPos?.z?.minus(viewerZ)

                        val color = Color(KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypointColor.toChromaColorInt())

                        if (KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypoints) RenderUtils.renderBeaconBeam(
                            x!!,
                            y!!,
                            z!!,
                            color.rgb,
                            color.alpha.toFloat(),
                            event.partialTicks
                        )
                        if (KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalText) RenderUtils.renderWaypointText(KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypointText,
                            blockPos!!, event.partialTicks)
                        i++
                    }
                }

                2 -> {
                    var i = 0
                    while (i < 4) {
                        val blockPos: BlockPos? = TermCoords.getHealerCoords(i)
                        val x = blockPos?.x?.minus(viewerX)
                        val y = blockPos?.y?.minus(viewerY)
                        val z = blockPos?.z?.minus(viewerZ)

                        val color = Color(KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypointColor.toChromaColorInt())

                        if (KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypoints) RenderUtils.renderBeaconBeam(
                            x!!,
                            y!!,
                            z!!,
                            color.rgb,
                            color.alpha.toFloat(),
                            event.partialTicks
                        )
                        if (KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalText) RenderUtils.renderWaypointText(KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypointText, blockPos!!, event.partialTicks)
                        i++
                    }
                }

                3 -> {
                    var i = 0
                    while (i < 4) {
                        val blockPos: BlockPos? = TermCoords.getBersCoords(i)
                        val x = blockPos?.x?.minus(viewerX)
                        val y = blockPos?.y?.minus(viewerY)
                        val z = blockPos?.z?.minus(viewerZ)

                        val color = Color(KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypointColor.toChromaColorInt())

                        if (KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypoints) RenderUtils.renderBeaconBeam(
                            x!!,
                            y!!,
                            z!!,
                            color.rgb,
                            color.alpha.toFloat(),
                            event.partialTicks
                        )
                        if (KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalText) RenderUtils.renderWaypointText(KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypointText, blockPos!!, event.partialTicks)
                        i++
                    }
                }

                4 -> {
                    var i = 0
                    while (i < 4) {
                        val blockPos: BlockPos? = TermCoords.getArchCoords(i)
                        val x = blockPos?.x?.minus(viewerX)
                        val y = blockPos?.y?.minus(viewerY)
                        val z = blockPos?.z?.minus(viewerZ)

                        val color = Color(KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypointColor.toChromaColorInt())

                        if (KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypoints) RenderUtils.renderBeaconBeam(
                            x!!,
                            y!!,
                            z!!,
                            color.rgb,
                            color.alpha.toFloat(),
                            event.partialTicks
                        )
                        if (KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalText) RenderUtils.renderWaypointText(KazzUtilsV2.config.dungeon.waypoints.m7f7.terminal.terminalWaypointText, blockPos!!, event.partialTicks)
                        i++
                    }
                }

                else -> {}
            }
        }
    }



}