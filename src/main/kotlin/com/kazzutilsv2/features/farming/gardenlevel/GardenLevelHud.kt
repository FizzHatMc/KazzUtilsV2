package com.kazzutilsv2.features.farming.gardenlevel

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.core.structure.GuiElement
import com.kazzutilsv2.data.farming.GardenXP
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.TabUtils
import com.kazzutilsv2.utils.graphics.ScreenRenderer

object GardenLevelHud {

    init {
        GardenLevelHudElement()
    }

    class GardenLevelHudElement : GuiElement("Garden Level Display", 1f, 10,10) {
        val config = KazzUtilsV2.config.farming.gardenLevel
        var message : String? = "N/A"


        override fun render() {
            if(TabUtils.area != "Garden") return


            if (TabUtils.gardenLevel == 15) {
                message = "15"
            } else if (KazzUtilsV2.config.farming.gardenLevel.gardenLevelPercentage) {
                message = ""+TabUtils.gardenLevel + " §e" + TabUtils.gardenPercent + "§7%"
            } else {
                val xp: Int = GardenXP.getGardenXp(TabUtils.gardenLevel, TabUtils.gardenPercent)
                message = ""+TabUtils.gardenLevel + " §7(§6" + xp + "§7/§6" + GardenXP.getMaxXp(TabUtils.gardenLevel) + "§7)§r"

            }

            if (toggled) {
                mc.fontRendererObj.drawStringWithShadow(message, x, y, config.gardenLevelColor.toChromaColorInt())
            }
        }

        override fun demoRender() {
            mc.fontRendererObj.drawStringWithShadow("Garden Level", x, y, config.gardenLevelColor.toChromaColorInt())
        }

        override val height: Int
            get() = ScreenRenderer.fontRenderer.FONT_HEIGHT
        override val width: Int
            get() = ScreenRenderer.fontRenderer.getStringWidth("Garden Level") + 50

        override val toggled: Boolean
            get() = config.gardenLevelDisplay

        init {
            KazzUtilsV2.guiManager.registerElement(this)
        }
    }


}