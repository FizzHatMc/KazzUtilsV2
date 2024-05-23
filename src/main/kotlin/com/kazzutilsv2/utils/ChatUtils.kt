package com.kazzutilsv2.utils

import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import net.minecraft.util.ChatComponentText

object ChatUtils {

    val hpRegex = Regex("""(\d+)/(\d+)❤""")
    val defenseRegex = Regex("""(\d+)❈ Defense""")
    val areaRegex = Regex("""⏣ ([^\d]+?)\s{2,}""")
    val manaUseRegex = Regex("""-(\d+) Mana""")
    val manaRegex = Regex("""(\d+)/(\d+)✎ Mana""")
    val skillRegex = Regex("""\+(\d+[\d,.]*) ([a-zA-Z]+) \((\d+[\d,.]*)/(\d+[\d,.]*)\)""")

    var hp : String? = ""
    var defense : String? = ""
    var area : String? = ""
    var manaUse : String? = ""
    var mana : String? = ""
    var skill : String? = ""


    fun messageToChat(message: String) {
        mc.thePlayer.addChatMessage(ChatComponentText(message))
    }

    fun userMessage(message: String){
        mc.thePlayer.sendChatMessage(message)
    }

    fun checkRegex(text: String){
        hp = hpRegex.find(text)?.groupValues?.let { Pair(it[1], it[2]) }.toString()
        defense = defenseRegex.find(text)?.groupValues?.get(1)
        area = areaRegex.find(text)?.groupValues?.get(1)
        manaUse = manaUseRegex.find(text)?.groupValues?.get(1)
        mana = manaRegex.find(text)?.groupValues?.let { Pair(it[1], it[2]) }.toString()
        skill = skillRegex.find(text)?.groupValues?.let { "+${it[1]} ${it[2]} (${it[3]}/${it[4]})" }
    }
}