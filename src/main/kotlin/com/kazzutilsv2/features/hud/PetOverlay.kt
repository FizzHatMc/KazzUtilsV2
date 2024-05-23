package com.kazzutilsv2.features.hud

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.RenderUtils
import com.kazzutilsv2.utils.TabUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object PetOverlay {

    init {
        PetOverlayElement()
    }

    class PetOverlayElement : GuiElement("Pet Overlay Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.misc.petOverlay
        var message : String? = ""
        var xp : String? = ""

        override fun render() {

            message = TabUtils.petName
            xp = TabUtils.petXp

            if (toggled) {
                mc.fontRendererObj.drawStringWithShadow(message, x, y, config.petOverlayColor.toChromaColorInt())
                mc.fontRendererObj.drawStringWithShadow(xp, x, y+height, config.petOverlayColor.toChromaColorInt())
            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Pet Overlay", x, y, config.petOverlayColor.toChromaColorInt())
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Pet Overlay") + 50

        override val toggled: Boolean
            get() = config.petOverlay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }


}