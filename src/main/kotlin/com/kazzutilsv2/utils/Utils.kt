package com.kazzutilsv2.utils

import com.kazzutilsv2.utils.graphics.colors.ColorFactory.web
import com.kazzutilsv2.utils.graphics.colors.CustomColor
import com.kazzutilsv2.utils.graphics.colors.CyclingTwoColorGradient
import com.kazzutilsv2.utils.graphics.colors.RainbowColor
import net.minecraft.client.settings.GameSettings
import java.awt.Color
import java.io.File

object Utils {

    fun getKeyDisplayStringSafe(keyCode: Int): String =
        runCatching { GameSettings.getKeyDisplayString(keyCode) }.getOrNull() ?: "Key $keyCode"

    fun File.ensureFile() = (parentFile.exists() || parentFile.mkdirs()) && createNewFile()

    private fun getCustomColorFromColor(color: Color) = CustomColor.fromInt(color.rgb)

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