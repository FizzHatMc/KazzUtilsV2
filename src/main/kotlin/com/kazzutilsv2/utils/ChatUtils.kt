package com.kazzutilsv2.utils

import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.utils.Utils.removeMinecraftColorCodes
import net.minecraft.util.ChatComponentText
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object ChatUtils {

    val hpRegex = Regex("""(\d+)/(\d+)❤""")
    val defenseRegex = Regex("""(\d+)❈ Defense""")
    val areaRegex = Regex("""⏣ ([^\d]+?)\s{2,}""")
    val manaUseRegex = Regex("""-(\d+) Mana""")
    val manaRegex = Regex("""(\d+)/(\d+)✎ Mana""")
    val skillRegex = Regex("""\+(\d+[\d,.]*) ([a-zA-Z]+) \((\d+[\d,.]*)/(\d+[\d,.]*)\)""")

    var health: String? = null
    var area: String? = null
    var manaUse: String? = null
    var mana: String? = null
    var skill: List<String>? = null
    var defense: String? = null


    fun messageToChat(message: String) {
        mc.thePlayer.addChatMessage(ChatComponentText(message))
    }

    fun userMessage(message: String){
        mc.thePlayer.sendChatMessage(message)
    }

    @SubscribeEvent
    fun onChat(event: ClientChatReceivedEvent){
        if (event.type.toInt() != 2) return
        event.isCanceled = true
        val text = event.message.unformattedTextForChat.removeMinecraftColorCodes()

        health = hpRegex.find(text)?.groupValues?.let { Pair(it[1], it[2]) }.toString()
        defense = defenseRegex.find(text)?.groupValues?.get(1)
        area = areaRegex.find(text)?.groupValues?.get(1)
        manaUse = manaUseRegex.find(text)?.groupValues?.get(1)
        mana = manaRegex.find(text)?.groupValues?.let { Pair(it[1], it[2]) }.toString()
        skill = skillRegex.find(text)?.groupValues

        //defense?.let { ChatUtils.messageToChat(it)}


    }


}