package com.kazzutilsv2.features.farming.gardenlevel

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.data.farming.GardenXP
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.TabUtils
import net.minecraft.client.gui.ScaledResolution
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class GardenLevelHud {

    @SubscribeEvent
    fun onRenderText(event: RenderGameOverlayEvent.Text) {
        if(!KazzUtilsV2.config.farming.gardenLevel.gardenLevelDisplay) return
        if(TabUtils.area != "Garden") return
        if (event.type === RenderGameOverlayEvent.ElementType.TEXT) {
            val scaledResolution = ScaledResolution(mc)
            val fontRenderer = mc.fontRendererObj

            var text = "N/A"

            if (TabUtils.gardenLevel == 15) {
                text = "15"
            } else if (KazzUtilsV2.config.farming.gardenLevel.gardenLevelPercentage) {
                text = ""+TabUtils.gardenLevel + " §e" + TabUtils.gardenPercent + "§7%"
            } else {
                val xp: Int = GardenXP.getGardenXp(TabUtils.gardenLevel, TabUtils.gardenPercent)
                text = ""+TabUtils.gardenLevel + " §7(§6" + xp + "§7/§6" + GardenXP.getMaxXp(TabUtils.gardenLevel) + "§7)§r"

            }


            val width = fontRenderer.getStringWidth(text) + (KazzUtilsV2.config.farming.gardenLevel.gardenLevelDisplayX/1.5)
            val height = fontRenderer.FONT_HEIGHT + (KazzUtilsV2.config.farming.gardenLevel.gardenLevelDisplayY/1.5)
            val scaledX = scaledResolution.scaledWidth - width
            val scaledY = scaledResolution.scaledHeight - height
            val color = KazzUtilsV2.config.farming.gardenLevel.gardenLevelColor.toChromaColorInt()

            fontRenderer.drawString(text, scaledX.toFloat(), scaledY.toFloat() , color, true)



        }
    }

}