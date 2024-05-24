package com.kazzutilsv2.features.hud.uioverlay

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ChatUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import net.minecraft.util.EnumChatFormatting
import java.awt.Color

object ManaOverlay {

    init {
        ManaOverlayElement()
    }

    class ManaOverlayElement : GuiElement("Mana Overlay Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.misc.hud
        var message : String? = ""
        var healthMax : Int = 0
        var healthTest : String = ""
        var healthMaxTest : String = ""
        var healthRN : Int = 0
        var color = EnumChatFormatting.BLUE.toString()

        override fun render() {
            if (toggled) {
            //if(!Utils.inSkyblock) return
            if(ChatUtils.mana != "" && ChatUtils.mana != null) {
                message = ChatUtils.mana.toString()

                message = message?.replace(",","")
                healthMaxTest = message?.substring(message!!.indexOf(" ")) ?: return
                healthMaxTest = healthMaxTest.replace(",", "")
                healthMaxTest = healthMaxTest.replace(" ", "")
                healthMaxTest = healthMaxTest.replace(")", "")
                healthMax = healthMaxTest.toInt()

                healthTest = message?.substring(0, message!!.indexOf(" ")) ?: return
                healthTest = healthTest.replace(",", "")
                healthTest = healthTest.replace(" ", "")
                healthTest = healthTest.replace("(", "")
                healthRN = healthTest.toInt()
            }
            if(healthRN != 0 && healthMax != 0) message = "$color$healthRN/$healthMax"

                mc.fontRendererObj.drawStringWithShadow(message, x, y, Color.BLUE.rgb)
            }

        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Mana", x, y, Color.BLUE.rgb)
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Mana") + 50

        override val toggled: Boolean
            get() = config.hpOverlay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }

}