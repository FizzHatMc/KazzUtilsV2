package com.kazzutilsv2.features.dungeon.M7

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.data.enumClass.DunClass
import com.kazzutilsv2.data.enumClass.WitherKingDragons
import com.kazzutilsv2.data.m7.coords.CauldronCoords
import com.kazzutilsv2.data.m7.coords.RelicCoords
import com.kazzutilsv2.utils.CatacombsUtils
import com.kazzutilsv2.utils.ChatUtils
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.RenderUtils
import com.kazzutilsv2.utils.TabUtils
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.entity.Entity
import net.minecraft.util.BlockPos
import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.awt.Color

class RelicWaypoints {

    @SubscribeEvent
    fun onWorldRender(event: RenderWorldLastEvent) {

        val player: EntityPlayerSP = mc.thePlayer
        val viewer: Entity = mc.renderViewEntity
        var playersClassAsInt : Int = 1

        for(s in DunClass.entries) {
            if(s.classNameAsString == TabUtils.playerClass) playersClassAsInt = s.classNum
        }
        
        //Relic
        if (player.posY < 50 && (CatacombsUtils.floor.contains("F7") || CatacombsUtils.inM7)) {
            
            val viewerX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * event.partialTicks
            val viewerY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * event.partialTicks
            val viewerZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * event.partialTicks
            
            val blockPos: BlockPos? = RelicCoords.getRelic(playersClassAsInt)
            val x = blockPos!!.x - viewerX
            val y = blockPos.y - viewerY
            val z = blockPos.z - viewerZ
            var drag: WitherKingDragons? = null

            

            when (playersClassAsInt) {
                0 -> drag = WitherKingDragons.APEX
                1 -> drag = WitherKingDragons.ICE
                2 -> drag = WitherKingDragons.SOUL
                3 -> drag = WitherKingDragons.FLAME
                4 -> drag = WitherKingDragons.POWER
            }

            val color = Color(KazzUtilsV2.config.dungeon.waypoints.m7f7.relic.relicWaypointColor.toChromaColorInt())

            if (KazzUtilsV2.config.dungeon.waypoints.m7f7.relic.relicWaypoints) {
                checkNotNull(drag)
                if (!drag.isDestroyed) {
                    ChatUtils.messageToChat(EnumChatFormatting.LIGHT_PURPLE.toString() + "Render next")
                    RenderUtils.renderBeaconBeam(x, y, z, color.rgb, color.alpha.toFloat(), event.partialTicks)
                }
            }
            if (KazzUtilsV2.config.dungeon.waypoints.m7f7.relic.relicText) {
                checkNotNull(drag)
                if (!drag.isDestroyed) {
                    RenderUtils.renderWaypointText(KazzUtilsV2.config.dungeon.waypoints.m7f7.relic.relicWaypointText, blockPos, event.partialTicks)
                }
            }
        }




        //Cauldron
        if (player.posY < 50 && (CatacombsUtils.floor.contains("F7") || CatacombsUtils.inM7)) {
            val viewerX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * event.partialTicks
            val viewerY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * event.partialTicks
            val viewerZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * event.partialTicks


            val blockPos: BlockPos? = RelicCoords.getRelic(playersClassAsInt)
            val x = blockPos!!.x - viewerX
            val y = blockPos.y - viewerY
            val z = blockPos.z - viewerZ
            var drag: WitherKingDragons? = null
            when (playersClassAsInt) {
                0 -> drag = WitherKingDragons.APEX
                1 -> drag = WitherKingDragons.ICE
                2 -> drag = WitherKingDragons.SOUL
                3 -> drag = WitherKingDragons.FLAME
                4 -> drag = WitherKingDragons.POWER
            }
            val pos: BlockPos? = CauldronCoords.getRelic(playersClassAsInt)

            if (KazzUtilsV2.config.dungeon.waypoints.m7f7.relic.cauldronHighlight) {
                checkNotNull(drag)
                if (!drag.picked) {
                    RenderUtils.drawCustomBox(pos!!.x.toDouble(), 1.0, pos.y + 1.toDouble(), 1.0, pos.z.toDouble(), 1.0, drag.color, 3f, true)
                }
            }
        }


        if (player.posY < 50 && (CatacombsUtils.floor.contains("F7") || CatacombsUtils.inM7) && KazzUtilsV2.config.dungeon.waypoints.m7f7.relic.renderDragonText) {
            for (drag in WitherKingDragons.entries) {
                RenderUtils.renderWaypointText(drag.textColor  ,
                    drag.dragonText,
                    event.partialTicks,
                    KazzUtilsV2.config.dungeon.waypoints.m7f7.relic.dragonTextScale
                )
                //RenderUtils.renderBoxOutline(drag.getCauldron(),1,1,1,event.partialTicks,3.69F,drag.getColor(),drag.getColor().getAlpha());
            }
        }

    }
    
}