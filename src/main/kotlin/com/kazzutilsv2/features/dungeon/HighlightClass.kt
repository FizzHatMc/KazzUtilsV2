package com.kazzutilsv2.features.dungeon

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.utils.RenderUtils
import com.kazzutilsv2.utils.TabUtils
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.awt.Color

class HighlightClass {

    @SubscribeEvent
    fun onRender(event: RenderWorldLastEvent){
        if(mc.thePlayer == null || mc.theWorld == null) return
        if(KazzUtilsV2.config.dungeon.dungeonClass.enumClass == null) return
        val classPlayerName = KazzUtilsV2.config.dungeon.dungeonClass.enumClass.playerName ?: return

        //if (classPlayerName == null) return
        val highlight = classPlayerName?.let { TabUtils.getPlayerByName(it) } ?: return
        if (highlight === mc.thePlayer) return
        if(highlight == null) return
        val boundingBox = highlight.entityBoundingBox
        RenderUtils.drawOutlinedBoundingBox(boundingBox, Color.GREEN, 3.69f, event.partialTicks)

    }



}