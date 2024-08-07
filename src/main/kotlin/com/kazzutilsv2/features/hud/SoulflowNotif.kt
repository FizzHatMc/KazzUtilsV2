package com.kazzutilsv2.features.hud

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.RenderUtils
import com.kazzutilsv2.utils.TabUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import net.minecraft.util.EnumChatFormatting

object SoulflowNotif {

    init {
        SoulflowNotifElement()
    }

    class SoulflowNotifElement : GuiElement("Soulflow Notif Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.misc.arrowSoulflowNotif.soulflow
        var message : String? = ""

        override fun render() {
            var amount = TabUtils.soulflow
            message = amount.toString()
            if(amount<= config.minSoulflow && config.soulflowNotif) RenderUtils.drawTitle("Soulflow",""+amount, EnumChatFormatting.RED)
            if (toggled) {
                mc.fontRendererObj.drawStringWithShadow(message, x, y, config.soulflowDisplayColor.toChromaColorInt())
            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Soulflow", x, y, config.soulflowDisplayColor.toChromaColorInt())
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Soulflow") + 50

        override val toggled: Boolean
            get() = config.soulflowDisplay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }



}