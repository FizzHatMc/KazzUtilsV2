package com.kazzutilsv2.features.misc.items

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.utils.ColorUtils.toChromaColorInt
import com.kazzutilsv2.utils.ItemUtils
import com.kazzutilsv2.utils.RenderUtils
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraft.util.Vec3
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.awt.Color

class GyroRange {

    @SubscribeEvent
    fun onRenderWorldLast(event: RenderWorldLastEvent) {
        val mc = Minecraft.getMinecraft()
        val heldItem: ItemStack? = ItemUtils.heldItem
        val itemId: String? = if (heldItem != null) ItemUtils.itemId(heldItem) else null
        val color: Color = Color(KazzUtilsV2.config.misc.items.gyroColor.toChromaColorInt())

        if (!KazzUtilsV2.config.misc.items.gyroRange) return
        if (itemId != "GYROKINETIC_WAND") return
        val pos = mc.thePlayer.rayTrace(25.0, event.partialTicks).blockPos
        val block = mc.theWorld.getBlockState(pos).block
        if (block.isAir(mc.theWorld, pos)) return

        RenderUtils.drawCylinder(
            Vec3(pos).addVector(0.5, 1.0, 0.5),
            10f, 10f - KazzUtilsV2.config.misc.items.gyroRangeScale, 0.2f, 40, 1, 0f, 0f, 0f,
            color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f,
            false, false
        )
    }

}