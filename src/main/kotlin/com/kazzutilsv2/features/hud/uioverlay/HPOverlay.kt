package com.kazzutilsv2.features.hud.uioverlay

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ChatUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import net.minecraft.util.EnumChatFormatting
import java.awt.Color

object HPOverlay {

    init {
        HPOverlayElement()
    }

    class HPOverlayElement : GuiElement("Health Overlay Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.misc.hud
        var message : String = ""
        var healthMax : Int = 0
        var healthRN : Int = 0
        var color = EnumChatFormatting.RED.toString()

        override fun render() {
            //if(!Utils.inSkyblock) return
            if(ChatUtils.health != "" && ChatUtils.health != null) {
                message = ChatUtils.health.toString()
                healthMax =
                    message.substring(message.indexOf(",")).replace(",", "").replace(" ", "").replace(")", "").toInt()
                healthRN =
                    message.substring(0, message.indexOf(",")).replace(",", "").replace(" ", "").replace("(", "")
                        .toInt()
                if (healthRN > healthMax) color = EnumChatFormatting.GOLD.toString() else color = EnumChatFormatting.RED.toString()
            }
            if(healthRN != 0 && healthMax != 0) message = color + healthRN + EnumChatFormatting.RED + "/" + healthMax
            if (toggled) {
                mc.fontRendererObj.drawStringWithShadow(message, x, y, Color.RED.rgb)
            }

        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Health", x, y, Color.RED.rgb)
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Health") + 50

        override val toggled: Boolean
            get() = config.hpOverlay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }

}