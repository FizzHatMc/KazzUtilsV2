package com.kazzutilsv2.features.keyshortcut

import com.kazzutilsv2.utils.ChatUtils
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse

class KeyShortcuts {

    @SubscribeEvent
    fun onInput(event: InputEvent) {
        val key =
            when {
                event is InputEvent.KeyInputEvent && Keyboard.getEventKeyState() -> Keyboard.getEventKey()
                event is InputEvent.MouseInputEvent && Mouse.getEventButtonState() -> Mouse.getEventButton() - 100
                else -> return
            }
        ChatUtils.messageToChat(key.toString())
    }

}