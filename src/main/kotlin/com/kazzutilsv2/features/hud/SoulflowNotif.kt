package com.kazzutilsv2.features.hud

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.ContainerUtils
import com.kazzutilsv2.utils.RenderUtils
import com.kazzutilsv2.utils.TabUtils
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class SoulflowNotif {

    @SubscribeEvent
    fun onRenderText(event: RenderGameOverlayEvent.Text) {
        if(!KazzUtilsV2.config.misc.arrowSoulflowNotif.soulflow.soulflowNotif) return
        if (event.type === RenderGameOverlayEvent.ElementType.TEXT) {

            var amount = TabUtils.soulflow

            if(amount<= KazzUtilsV2.config.misc.arrowSoulflowNotif.soulflow.minSoulflow) RenderUtils.drawTitle("Soulflow",""+amount, EnumChatFormatting.RED)

            val scaledResolution = ScaledResolution(mc)
            val fontRenderer = mc.fontRendererObj

            val text : String = amount.toString()
            val width = fontRenderer.getStringWidth(text) + (KazzUtilsV2.config.misc.arrowSoulflowNotif.soulflow.soulflowDisplayX/1.5)
            val height = fontRenderer.FONT_HEIGHT + (KazzUtilsV2.config.misc.arrowSoulflowNotif.soulflow.soulflowDisplayY/1.5)
            val scaledX = scaledResolution.scaledWidth - width
            val scaledY = scaledResolution.scaledHeight - height  // Adjust y position based on loop iteratione
            val color = KazzUtilsV2.config.misc.arrowSoulflowNotif.soulflow.soulflowDisplayColor.toChromaColorInt()

            fontRenderer.drawString(text, scaledX.toFloat(), scaledY .toFloat(), color, true)



        }
    }

}