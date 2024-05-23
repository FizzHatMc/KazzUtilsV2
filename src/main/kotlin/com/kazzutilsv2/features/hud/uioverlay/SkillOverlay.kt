package com.kazzutilsv2.features.hud.uioverlay

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ChatUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import java.awt.Color

object SkillOverlay {

    init {
        SkillOverlayElement()
    }

    class SkillOverlayElement : GuiElement("Skill Overlay Display", x = 10, y = 10) {
        val config = KazzUtilsV2.config.misc.hud
        var message : String? = ""

        override fun render() {
            if(ChatUtils.skill == null)return
            for(s in ChatUtils.skill!!){
                message += s
            }


            if (toggled) {
                mc.fontRendererObj.drawStringWithShadow(message, x, y, Color.GRAY.darker().rgb)
            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Skill", x, y, Color.GRAY.darker().rgb)
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Skill") + 50

        override val toggled: Boolean
            get() = config.skillOverlay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }

}