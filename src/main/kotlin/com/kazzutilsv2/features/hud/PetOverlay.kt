package com.kazzutilsv2.features.hud

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.TabUtils
import net.minecraft.client.gui.ScaledResolution
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class PetOverlay {

    @SubscribeEvent
    fun onRenderText(event: RenderGameOverlayEvent.Text) {
        if(!KazzUtilsV2.config.misc.petOverlay.petOverlay) return
        if (event.type === RenderGameOverlayEvent.ElementType.TEXT) {
            val scaledResolution = ScaledResolution(mc)
            val fontRenderer = mc.fontRendererObj

            var text = TabUtils.petName
            val width = fontRenderer.getStringWidth(text) + (KazzUtilsV2.config.misc.petOverlay.petOverlayX/1.5)
            val height = fontRenderer.FONT_HEIGHT + (KazzUtilsV2.config.misc.petOverlay.petOverlayY/1.5)
            val scaledX = scaledResolution.scaledWidth - width
            val scaledY = scaledResolution.scaledHeight - height  // Adjust y position based on loop iteratione
            val color = KazzUtilsV2.config.misc.petOverlay.petOverlayColor.toChromaColorInt()

            fontRenderer.drawString(text, scaledX.toFloat(), scaledY .toFloat(), color, true)
            text = TabUtils.petXp.toString()
            fontRenderer.drawString(text, scaledX.toFloat(), (scaledY+fontRenderer.FONT_HEIGHT) .toFloat(), color, true)



        }
    }

}