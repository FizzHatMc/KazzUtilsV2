package com.kazzutilsv2.features.events.mythological

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.TabUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import net.minecraft.client.gui.ScaledResolution
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


object MythoTrackerHud {

    init {
         MythoTrackerHudElement()
    }

    class MythoTrackerHudElement : GuiElement("Mythological Event Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.misc.mythologicalEvent
        var message : String? = ""

        override fun render() {
            val mythos = arrayOf("inq", "champ", "mino", "gaia", "lynx", "hunter") // List of mythos names

            for (i in mythos.indices) {
                var trackerName = ""
                when (mythos[i]) {
                    "mino" -> trackerName = "Minotaur: "
                    "gaia" -> trackerName = "Gaia Constructor: "
                    "lynx" -> trackerName = "Siamese Lynxes: "
                    "hunter" -> trackerName = "Minos Hunter: "
                    "inq" -> trackerName = "Minos Inquisitor: "
                    "champ" -> trackerName = "Minos Champion: "
                }
                message = trackerName + MythoTracker.getVarByName(mythos[i])// Get tracker value using a method

                if (toggled) {
                    mc.fontRendererObj.drawStringWithShadow(message, x, y + (i * height), config.mythoColor.toChromaColorInt())
                }
            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Mythological Event Tracker", x, y,  config.mythoColor.toChromaColorInt())
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Mythological Event Tracker") + 50

        override val toggled: Boolean
            get() = config.mytholocialTracker

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }
}