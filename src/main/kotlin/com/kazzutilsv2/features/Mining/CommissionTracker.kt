package com.kazzutilsv2.features.Mining

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.TabUtils
import net.minecraft.client.gui.ScaledResolution
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class CommissionTracker {

    @SubscribeEvent
    fun onRenderText(event: RenderGameOverlayEvent.Text) {
        if(!KazzUtilsV2.config.mining.commissionTracker.commissionTrackerOverlay) return
        if(TabUtils.area != "Dwarven") return
        if (event.type === RenderGameOverlayEvent.ElementType.TEXT) {
            val scaledResolution = ScaledResolution(mc)
            val fontRenderer = mc.fontRendererObj

            var text = TabUtils.comms


            for(com in text.indices){

                val width = fontRenderer.getStringWidth(text[com]) + (KazzUtilsV2.config.mining.commissionTracker.commissionTrackerOverlayX/1.5)
                val height = fontRenderer.FONT_HEIGHT + (KazzUtilsV2.config.mining.commissionTracker.commissionTrackerOverlayY/1.5)
                val scaledX = scaledResolution.scaledWidth - width
                val scaledY = scaledResolution.scaledHeight - height
                val color = KazzUtilsV2.config.mining.commissionTracker.commissionTrackerOverlayColor.toChromaColorInt()

                fontRenderer.drawString(text[com], scaledX.toFloat(),(scaledY  + (com  * fontRenderer.FONT_HEIGHT) ).toFloat(), color, true)
            }




        }
    }

}