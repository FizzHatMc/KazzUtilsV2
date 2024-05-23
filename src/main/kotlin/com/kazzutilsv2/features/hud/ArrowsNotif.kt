package com.kazzutilsv2.features.hud

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.ContainerUtils
import com.kazzutilsv2.utils.RenderUtils
import com.kazzutilsv2.utils.TabUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object ArrowsNotif {

    init {
        ArrowsNotifElement()
    }

    class ArrowsNotifElement : GuiElement("Arrow Notif Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.misc.arrowSoulflowNotif.arrow!!
        var message : String? = ""

        override fun render() {
            val quiver: ItemStack? = ContainerUtils.checkInventoryForName("Quiver", mc.thePlayer.inventory)
            val quiverLore: List<String> = ContainerUtils.getLore(quiver)
            var arrowType = ""
            var amount = 0

            for (tag in quiverLore) {
                if (tag.contains("Active Arrow:")) {
                    arrowType = tag.substring(tag.indexOf("Active Arrow: ") + "Active Arrow: ".length, tag.indexOf('('))
                    amount = tag.substring(tag.indexOf('(') + 3, tag.indexOf(')') - 2).toInt()
                }
            }
            //max 2880
            if(amount<=config.minArrow && config.ArrowNotif) RenderUtils.drawTitle("Arrows",""+amount,EnumChatFormatting.RED)

            message = arrowType + " / " + amount + "x"
            if(arrowType == "") return
            if (toggled) {
                mc.fontRendererObj.drawStringWithShadow(message, x, y, config.ArrowDisplayColor.toChromaColorInt())
            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Arrows", x, y, config.ArrowDisplayColor.toChromaColorInt())
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Arrows") + 50

        override val toggled: Boolean
            get() = config.ArrowDisplay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }

}