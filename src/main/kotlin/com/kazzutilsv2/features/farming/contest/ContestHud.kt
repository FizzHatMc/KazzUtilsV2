package com.kazzutilsv2.features.farming.contest

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.TabUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer

object ContestHud {

    init {
        ContestHudElement()
    }

    class ContestHudElement : GuiElement("Contest Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.farming.contest
        var message : String? = ""


        override fun render() {
            if(TabUtils.area != "Garden") return
            message = TabUtils.time
            for(i in TabUtils.contest){
                message += " $i"
            }


            if (toggled) {
                mc.fontRendererObj.drawStringWithShadow(message, x, y, config.contestDisplayColor.toChromaColorInt())
            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Contest Overlay", x, y, config.contestDisplayColor.toChromaColorInt())
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Contest Overlay") + 50

        override val toggled: Boolean
            get() = config.contestDisplay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }




}