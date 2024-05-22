package com.kazzutilsv2.utils

import com.google.common.collect.ComparisonChain
import com.google.common.collect.Ordering
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import net.minecraft.client.Minecraft
import net.minecraft.client.network.NetworkPlayerInfo
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.WorldSettings.GameType
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.regex.Pattern
import java.util.stream.Collectors

object TabUtils {

    private val areaPattern: Pattern = Pattern.compile("Area: (.+)")
    private var tablist: List<String>? = null
    var playerClass = ""
    var area: String = ""
    var explosivity: Boolean = false
    var maxVisitors: Boolean = false
    var emptyComposter: Boolean = false
    var gardenMilestone: String = ""
    var timeTillNextVisitor: String = ""
    var numVisitors: Int = 0
    var archerName: String = ""
    var tankName: String = ""
    var healerName: String = ""
    var mageName: String = ""
    var berserkerName: String = ""
    var gardenLevel: Int = 0
    var gardenPercent: Double = 0.0
    var trim: String = ""
    var contest : ArrayList<String?> = ArrayList()
    var time: String = ""
    var p: playerInfoOrdering = playerInfoOrdering()
    private var petFlag = false
    private var petFlagCount = 0
    var petName: String = ""
    var petXp: String? = ""
    var petNameUnform: String = ""

    //public static String petXp = "";
    var gardenLevelBool: Boolean = false
    var gardenLevelString: String = ""
    var comms: ArrayList<String?> = ArrayList()
    var soulflow : Int = 0

    fun getPlayerByName(playerName: String): EntityPlayer? {
        val players: List<EntityPlayer> = mc.theWorld.playerEntities // Assuming mc is your Minecraft instance

        for (player in players) {
            if (player.name == playerName) {
                return player
            }
        }

        return null // Player not found
    }

    fun getPlayerNameByClass(playerClass: String): String {
        when (playerClass) {
            "Archer" -> return archerName
            "Tank" -> return tankName
            "Healer" -> return healerName
            "Mage" -> return mageName
            "Berserker" -> return berserkerName
            else -> return ""
        }
    }

    @SideOnly(Side.CLIENT)
    class playerInfoOrdering : Ordering<NetworkPlayerInfo?>() {
        override fun compare(info1: NetworkPlayerInfo?, info2: NetworkPlayerInfo?): Int {
            if (info1 == null) return -1
            if (info2 == null) return 0
            return ComparisonChain.start()
                .compareTrueFirst(!isSpectator(info1.gameType), !isSpectator(info2.gameType))
                .compare(
                    if (info1.playerTeam != null) info1.playerTeam.registeredName else "",
                    if (info2.playerTeam != null) info2.playerTeam.registeredName else ""
                )
                .compare(info1.gameProfile.name, info2.gameProfile.name)
                .result()
        }
    }


    private fun fetchTabEntries(): List<NetworkPlayerInfo> {
        var entries = emptyList<NetworkPlayerInfo>()
        if (mc.thePlayer != null) {
            entries = p.sortedCopy(mc.thePlayer.sendQueue.getPlayerInfoMap())
        }
        return entries
    }


    fun parseTabEntries() {
        comms.clear()
        contest.clear()
        if (mc.thePlayer == null) return
        if (mc.theWorld == null) return

        val scoreboardList = mapNotNull(fetchTabEntries()) ?: return

        for (line in scoreboardList) {
            trim = line!!.trim { it <= ' ' }

            val matcher = areaPattern.matcher(trim)
            if (matcher.find()) {
                area = matcher.group(1)
            }

            if (petFlag && petFlagCount <= 1) {
                if (petFlagCount == 0) petName = line.substring(trim.indexOf(']') + 2)
                if (petFlagCount == 1) petXp = line

                petFlagCount++
            } else {
                petFlagCount = 0
                petFlag = false
            }


            if (trim.contains("Contest:")) {
                val index = scoreboardList.indexOf(line) + 1
                //time = scoreboardList[index]!!.substring(scoreboardList[index]!!.indexOf(": ") + 1) ?: return
                time = scoreboardList[index]?.substring((scoreboardList[index]?.indexOf(": ") )?.plus(1) ?: return).toString()

                for(i in 1..3){
                    contest.add(scoreboardList[index+i])
                }

            } else if (trim.contains("Garden Level:")) {
                val split = trim.substring(trim.indexOf(":") + 1)
                var lvl = split.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                lvl = lvl.replace(" ", "")
                val test: Int? = NumberUtils.getNumber(lvl)

                if (test != null) {
                    //gardenLevel = Integer.parseInt(trim.substring(trim.indexOf(":") + 2 /*trim.indexOf(":") + 4*/).replace(" ", ""));
                    gardenLevel = test
                    if (gardenLevel != 15) {
                        gardenPercent = trim.substring(trim.indexOf("(") + 1, trim.indexOf(")") - 7).toDouble()
                    }
                } else {
                    if (lvl != "XV") {
                        //String percent = split.split(" ")[1];
                        gardenLevel = NumberUtils.toInteger(lvl)
                        gardenPercent = trim.substring(trim.indexOf("(") + 1, trim.indexOf(")") - 1).toDouble()
                    } else gardenLevel = NumberUtils.toInteger("XV")
                }
            } else if (trim.contains("Pet:")) {
                petFlag = true
            } else if (trim.contains("Dungeon: Catacombs")) {
                area = "Catacombs"
            } else if (trim.contains("Commissions:")) {
                area = "Dwarven"
                var index = scoreboardList.indexOf(line)
                for(i in 1..5){
                    if(scoreboardList[index+i] == "") return
                    comms.add(scoreboardList[index+i])
                }
            }

            if(trim.contains("Soulflow: ")){
                soulflow = trim.substring( trim.indexOf('S')+"Soulflow: ".length).replace(",","").toInt()
            }

            if (line.contains("(Archer")) {
                archerName = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                if(archerName.contains(mc.thePlayer.name)) playerClass = "Archer"
                //ChatUtils.messageToChat("Player: $archerName class: $playerClass Name: "+mc.thePlayer.name)
            } else if (line.contains("(Tank")) {
                tankName = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                if(tankName.contains(mc.thePlayer.name)) playerClass = "Tank"
                //ChatUtils.messageToChat("Player: $tankName class: $playerClass"+mc.thePlayer.name)
            } else if (line.contains("(Mage")) {
                mageName = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                if(mageName .contains(mc.thePlayer.name)) playerClass = "Mage"
                //ChatUtils.messageToChat("Player: $mageName class: $playerClass"+mc.thePlayer.name)
            } else if (line.contains("(Healer")) {
                healerName = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                if(healerName.contains(mc.thePlayer.name)) playerClass = "Healer"
                //ChatUtils.messageToChat("Player: $healerName class: $playerClass"+mc.thePlayer.name)
            } else if (line.contains("(Berserk")) {
                berserkerName = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                if(berserkerName.contains(mc.thePlayer.name)) playerClass = "Berserk"
                //ChatUtils.messageToChat("Player: $berserkerName class: $playerClass"+mc.thePlayer.name)
            }
        }
    }

    private fun isSpectator(gameType: GameType): Boolean {
        return gameType == GameType.SPECTATOR
    }

    private fun mapNotNull(tabEntries: List<NetworkPlayerInfo>): List<String?> {
        return tabEntries.stream()
            .map { info: NetworkPlayerInfo ->
                if (info.displayName != null) info.displayName.unformattedText else null
            }
            .filter { displayName: String? -> displayName != null }
            .collect(Collectors.toList())
    }

    var playerOrdering: Collection<NetworkPlayerInfo>? = null

    fun readTabList(): List<String> {
        val thePlayer = Minecraft.getMinecraft().thePlayer
        playerOrdering = thePlayer.sendQueue.playerInfoMap
        val result: MutableList<String> = ArrayList()
        for (info in (playerOrdering as MutableCollection<NetworkPlayerInfo>?)!!) {
            val name: String = mc.ingameGUI.getTabList().getPlayerName(info)
            result.add(stripVanillaMessage(name))
        }
        if (!result.isEmpty()) {
            result.removeAt(result.size - 1)
        }
        return result
    }

    private fun stripVanillaMessage(orignalMessage: String): String {
        var message = orignalMessage
        while (message.startsWith("§r")) {
            message = message.substring(2)
        }
        while (message.endsWith("§r")) {
            message = message.substring(0, message.length - 2)
        }
        return message
    }




}