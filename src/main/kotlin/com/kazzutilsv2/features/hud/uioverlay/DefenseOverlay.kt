package com.kazzutilsv2.features.hud.uioverlay

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ChatUtils
import com.kazzutilsv2.utils.TabUtils
import com.kazzutilsv2.utils.Utils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import com.kazzutilsv2.utils.graphics.SmartFontRenderer
import com.kazzutilsv2.utils.graphics.colors.CommonColors
import java.awt.Color

object DefenseOverlay {

    init {
        DefenseOverlayElement()
    }

    class DefenseOverlayElement : GuiElement("Defense Overlay Display", x = 10, y = 10) {
        val config = KazzUtilsV2.config.misc.hud
        var message = ""

        override fun render() {
            ChatUtils.messageToChat("In Skyblock : ${Utils.inSkyblock}")
            message = ChatUtils.defense ?: return
            if (toggled) {
                mc.fontRendererObj.drawStringWithShadow(
                    message,
                    x,
                    y,
                    Color.GREEN.darker().rgb
                )

            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow(
                "Test",
                x,
                y,
                Color.GREEN.darker().rgb
            )
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Test") + 50

        override val toggled: Boolean
            get() = config.defenseOverlay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }

}