package com.kazzutilsv2.features.mining

import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.handler.ScoreboardHandler
import com.kazzutilsv2.utils.ChatUtils

import net.minecraft.client.Minecraft
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumChatFormatting
import net.minecraft.world.World

object StarCultNotif {
    private var tick = false;

    fun checkCult() {
        val scoreboard: List<String> = ScoreboardHandler.getSidebarLines()
        for (s in scoreboard) {
            val sCleaned: String = ScoreboardHandler.cleanSB(s)
            if (sCleaned.contains("11:50pm")) {
                checkDate()
            }
        }
    }

    fun checkDate() {
        val world: World = Minecraft.getMinecraft().theWorld
        val player: EntityPlayer = Minecraft.getMinecraft().thePlayer
        val scoreboard: List<String> = ScoreboardHandler.getSidebarLines()
        for (t in scoreboard) {
            val sCleaned: String = ScoreboardHandler.cleanSB(t)
            if (sCleaned.contains("6th") || sCleaned.contains("13th") || sCleaned.contains("20th") || sCleaned.contains("27th")) {
                tick = true

                ChatUtils.messageToChat(EnumChatFormatting.BLUE.toString() + "---------------------------------")
                ChatUtils.messageToChat(EnumChatFormatting.RED.toString() + "------------STAR CULT------------")
                ChatUtils.messageToChat(EnumChatFormatting.BLUE.toString() + "--------------------------------")

                mc.ingameGUI.displayTitle("Star Cult","",0,2,0)
                world.playSound(player.posX, player.posY, player.posZ, "random.orb", 1f, 1f, false)
                break
            } else {
                tick = false
            }
        }
    }

    fun test(title: String){
        mc.ingameGUI.displayTitle(title,"",0,2,0)
    }
}