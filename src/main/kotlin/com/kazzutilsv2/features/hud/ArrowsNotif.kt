package com.kazzutilsv2.features.hud

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.ContainerUtils
import com.kazzutilsv2.utils.RenderUtils
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ArrowsNotif {

    @SubscribeEvent
    fun onRenderText(event: RenderGameOverlayEvent.Text) {
        if(!KazzUtilsV2.config.misc.arrowSoulflowNotif.arrow.ArrowDisplay) return
        if (event.type === RenderGameOverlayEvent.ElementType.TEXT) {

            val quiver: ItemStack? = ContainerUtils.checkInventoryForName("Quiver", mc.thePlayer.inventory)
            val quiverLore: List<String> = ContainerUtils.getLore(quiver)
            var arrowType: String = ""
            var amount: Int = 0

            for (tag in quiverLore) {
                if (tag.contains("Active Arrow:")) {
                    arrowType = tag.substring(tag.indexOf("Active Arrow: ") + "Active Arrow: ".length, tag.indexOf('('))
                    amount = tag.substring(tag.indexOf('(') + 3, tag.indexOf(')') - 2).toInt()
                }
            }
           //max 2880
            if(amount<=KazzUtilsV2.config.misc.arrowSoulflowNotif.arrow.minArrow) RenderUtils.drawTitle("Arrows",""+amount,EnumChatFormatting.RED)


            val scaledResolution = ScaledResolution(mc)
            val fontRenderer = mc.fontRendererObj

            val text = arrowType + " / " + amount + "x"
            val width = fontRenderer.getStringWidth(text) + (KazzUtilsV2.config.misc.arrowSoulflowNotif.arrow.ArrowDisplayX/1.5)
            val height = fontRenderer.FONT_HEIGHT + (KazzUtilsV2.config.misc.arrowSoulflowNotif.arrow.ArrowDisplayY/1.5)
            val scaledX = scaledResolution.scaledWidth - width
            val scaledY = scaledResolution.scaledHeight - height  // Adjust y position based on loop iteratione
            val color = KazzUtilsV2.config.misc.arrowSoulflowNotif.arrow.ArrowwDisplayColor.toChromaColorInt()

            fontRenderer.drawString(text, scaledX.toFloat(), scaledY .toFloat(), color, true)



        }
    }

}