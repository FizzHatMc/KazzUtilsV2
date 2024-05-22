package com.kazzutilsv2.features.events.mythological

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.TabUtils
import net.minecraft.client.gui.ScaledResolution
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


object MythoTrackerHud {

    init {

    }

    @SubscribeEvent
    fun onRenderText(event: RenderGameOverlayEvent.Text) {
        if(!KazzUtilsV2.config.misc.mythologicalEvent.mytholocialTracker) return
        if(TabUtils.area != "Hub") return
        if (event.type === RenderGameOverlayEvent.ElementType.TEXT) {
            val scaledResolution = ScaledResolution(mc)
            val fontRenderer = mc.fontRendererObj


            val mythos = arrayOf("inq", "champ", "mino", "gaia", "lynx", "hunter") // List of mythos names

            for (i in mythos.indices) {
                var trackerName = ""
                when(mythos[i]){
                    "mino" -> trackerName = "Minotaur: "
                    "gaia" -> trackerName = "Gaia Constructor: "
                    "lynx" -> trackerName = "Siamese Lynxes: "
                    "hunter" -> trackerName = "Minos Hunter: "
                    "inq" -> trackerName = "Minos Inquisitor: "
                    "champ" -> trackerName = "Minos Champion: "
                }
                val text = trackerName + MythoTracker.getVarByName(mythos[i])// Get tracker value using a method
                val width = fontRenderer.getStringWidth(text) + KazzUtilsV2.config.misc.mythologicalEvent.mytholocialTrackerX
                val height = fontRenderer.FONT_HEIGHT + KazzUtilsV2.config.misc.mythologicalEvent.mytholocialTrackerY
                val scaledX = scaledResolution.scaledWidth - width
                val scaledY = scaledResolution.scaledHeight - height  // Adjust y position based on loop iteration
                val color = KazzUtilsV2.config.misc.mythologicalEvent.mythoColor.toChromaColorInt()


                fontRenderer.drawString(text, scaledX.toFloat(), (scaledY + i * fontRenderer.FONT_HEIGHT).toFloat(), color, true)
            }


        }
    }

}