package com.kazzutilsv2.features.farming.contest

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.TabUtils
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ContestHud {

    @SubscribeEvent
    fun onRenderText(event: RenderGameOverlayEvent.Text) {
        if(!KazzUtilsV2.config.farming.contest.contestDisplay) return
        if(TabUtils.area != "Garden") return
        if (event.type === RenderGameOverlayEvent.ElementType.TEXT) {
            val scaledResolution = ScaledResolution(mc)
            val fontRenderer = mc.fontRendererObj
            var text = TabUtils.time
            for(i in TabUtils.contest){
                text += " $i"
            }
            val width = fontRenderer.getStringWidth(text) + (KazzUtilsV2.config.farming.contest.contestDisplayX/1.5)
            val height = fontRenderer.FONT_HEIGHT + (KazzUtilsV2.config.farming.contest.contestDisplayY/1.5)
            val scaledX = scaledResolution.scaledWidth - width
            val scaledY = scaledResolution.scaledHeight - height  // Adjust y position based on loop iteratione
            val color = KazzUtilsV2.config.farming.contest.contestDisplayColor.toChromaColorInt()

            fontRenderer.drawString(text, scaledX.toFloat(), scaledY .toFloat(), color, true)



        }
    }

}