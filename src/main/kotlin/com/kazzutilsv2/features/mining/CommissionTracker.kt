package com.kazzutilsv2.features.mining

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.TabUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import net.minecraft.client.gui.ScaledResolution
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object CommissionTracker {

    init {
        CommissionTrackerElement()
    }

    class CommissionTrackerElement : GuiElement("Commission Tracker Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.mining.commissionTracker
        var message : String? = ""

        override fun render() {
            //if(!TabUtils.area.contains("Dwarven Mines") || !TabUtils.area.contains("Crystal Hollows")) return

            val text = TabUtils.comms

            if (toggled) {
                for((i,com) in text.withIndex()) mc.fontRendererObj.drawStringWithShadow(com, x, y+ (i * height), config.commissionTrackerOverlayColor.toChromaColorInt())

            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Commission Tracker", x, y,  config.commissionTrackerOverlayColor.toChromaColorInt())
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Commission Tracker") + 50

        override val toggled: Boolean
            get() = config.commissionTrackerOverlay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }


}