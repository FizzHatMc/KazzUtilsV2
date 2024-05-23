package com.kazzutilsv2.utils

import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.utils.colors.ColorFactory.web
import com.kazzutilsv2.utils.colors.CustomColor
import com.kazzutilsv2.utils.colors.CyclingTwoColorGradient
import com.kazzutilsv2.utils.colors.RainbowColor
import net.minecraft.client.settings.GameSettings
import java.awt.Color
import java.io.File

object Utils {

    var inSkyblock = false

    fun getKeyDisplayStringSafe(keyCode: Int): String =
        runCatching { GameSettings.getKeyDisplayString(keyCode) }.getOrNull() ?: "Key $keyCode"

    fun File.ensureFile() = (parentFile.exists() || parentFile.mkdirs()) && createNewFile()

    private fun getCustomColorFromColor(color: Color) = CustomColor.fromInt(color.rgb)

    fun String.removeMinecraftColorCodes() : String{
        return Regex("§[0-9A-FK-ORa-fk-or]").replace(this, "")
    }

    fun checkSkyblock() {
        val player = mc.thePlayer ?: return

        val scoreboard = player.worldScoreboard
        val objective = scoreboard.getObjectiveInDisplaySlot(1) // Get the sidebar objective

        if (objective != null) {
            val displayName = objective.displayName.toString()
            if (displayName.contains("SKYBLOCK", true)) {
                inSkyblock = true
                // Perform actions specific to Skyblock here
            } else {
                inSkyblock = false
            }
        }
    }

    fun customColorFromString(string: String?): CustomColor {
        if (string == null) throw NullPointerException("Argument cannot be null!")
        return if (string.startsWith("rainbow(")) {
            RainbowColor.fromString(string)
        } else if (string.startsWith("cyclingtwocolorgradient(")) {
            CyclingTwoColorGradient.fromString(string)
        } else try {
            getCustomColorFromColor(web(string))
        } catch (e: IllegalArgumentException) {
            try {
                CustomColor.fromInt(string.toInt())
            } catch (ignored: NumberFormatException) {
                throw e
            }
        }
    }

    fun colorFromString(string: String): Color {
        return try {
            web(string)
        } catch (e: IllegalArgumentException) {
            try {
                Color(string.toInt(), true)
            } catch (ignored: NumberFormatException) {
                throw e
            }
        }
    }

}