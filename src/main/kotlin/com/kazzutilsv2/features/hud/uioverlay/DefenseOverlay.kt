package com.kazzutilsv2.features.hud.uioverlay

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ChatUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import java.awt.Color

object DefenseOverlay {

    init {
        DefenseOverlayElement()
    }

    class DefenseOverlayElement : GuiElement("Defense Overlay Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.misc.hud
        var message : String? = ""

        override fun render() {
            if (toggled) {
            if(ChatUtils.defense != "" && ChatUtils.defense != null) message = ChatUtils.defense

                mc.fontRendererObj.drawStringWithShadow(message, x, y, Color.GREEN.darker().rgb)
            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Defense", x, y, Color.GREEN.darker().rgb)
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Defense") + 50

        override val toggled: Boolean
            get() = config.defenseOverlay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }

}