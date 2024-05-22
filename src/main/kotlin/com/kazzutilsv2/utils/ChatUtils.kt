package com.kazzutilsv2.utils

import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import net.minecraft.util.ChatComponentText

object ChatUtils {

    fun messageToChat(message: String) {
        mc.thePlayer.addChatMessage(ChatComponentText(message))
    }

    fun userMessage(message: String){
        mc.thePlayer.sendChatMessage(message)
    }
}