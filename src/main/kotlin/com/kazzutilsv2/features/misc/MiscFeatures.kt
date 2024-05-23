package com.kazzutilsv2.features.misc

import com.kazzutilsv2.KazzUtilsV2
import net.minecraftforge.event.entity.living.EnderTeleportEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class MiscFeatures {

    @SubscribeEvent
    fun onEndermanTeleport(event: EnderTeleportEvent){
        if(KazzUtilsV2.config.combat.slayer.voidgloom.stopEndermanFakeTeleportation) event.isCanceled = true
    }

}