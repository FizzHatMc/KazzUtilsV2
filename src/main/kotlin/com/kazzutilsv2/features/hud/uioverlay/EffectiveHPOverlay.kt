package com.kazzutilsv2.features.hud.uioverlay

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ChatUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import kotlinx.coroutines.channels.Channel
import java.awt.Color

object EffectiveHPOverlay {

    init {
        EffectiveHPOverlayElement()
    }

    class EffectiveHPOverlayElement : GuiElement("Effective HP Overlay Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.misc.hud
        var message : String = ""
        var defense : Int = 0

        override fun render() {
            if(ChatUtils.health == "" || ChatUtils.health == null)return
            val health = ChatUtils.health ?: return
            val healthInt = health.substring(0, health.indexOf(',')).replace("(", "").replace(" ", "").toInt()
            if(ChatUtils.defense != "" && ChatUtils.defense != null) defense = ChatUtils.defense!!.toInt()
            message = (healthInt * (1+(defense/100))).toString()
            if (toggled) {
                mc.fontRendererObj.drawStringWithShadow(message, x, y, Color.RED.darker().rgb)
            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Effective HP", x, y, Color.RED.darker().rgb)
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Effective HP") + 50

        override val toggled: Boolean
            get() = config.effectiveHPOverlay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }

}