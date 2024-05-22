package com.kazzutilsv2.event

import com.kazzutilsv2.KazzUtilsV2
import gg.essential.universal.UChat
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.Event

abstract class KazzUtilsV2Event : Event() {
    val eventName by lazy {
        this::class.simpleName
    }

    fun postAndCatch(): Boolean {
        return runCatching {
            MinecraftForge.EVENT_BUS.post(this)
        }.onFailure {
            it.printStackTrace()
            UChat.chat("§cKazzUtilsV2 ${KazzUtilsV2.version} caught and logged an ${it::class.simpleName ?: "error"} at ${eventName}.")
        }.getOrDefault(isCanceled)
    }
}