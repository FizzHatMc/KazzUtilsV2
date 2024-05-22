package com.kazzutilsv2.commands

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import com.kazzutilsv2.commands.SimpleCommand.ProcessCommandRunnable
import com.kazzutilsv2.features.keyshortcut.KeyShortcuts
import com.kazzutilsv2.gui.KeyShortcutsGui
import com.kazzutilsv2.gui.editing.ElementaEditingGui

import com.kazzutilsv2.utils.ChatUtils
import gg.essential.elementa.ElementaVersion
import net.minecraft.command.ICommandSender
import net.minecraft.util.BlockPos
import net.minecraftforge.client.ClientCommandHandler

class CommandManager {

    init {
        //registerCommand("testcommand") {


        //}
        registerCommand("kazzutils") {
            KazzUtilsV2.configManager.openConfigGui()
        }
        registerCommand("kazz"){ args ->
            val arg = args.firstOrNull()
            if (arg != null) {
                if(arg.contains("gui",false)){
                    KazzUtilsV2.displayScreen = ElementaEditingGui()
                    return@registerCommand
                }else if(arg.contains("hotkeys",false)){
                    KazzUtilsV2.displayScreen = KeyShortcutsGui()
                    return@registerCommand
                }else{
                    ChatUtils.messageToChat("Error: Invalid usage: /kazz <gui|hotkeys>")
                }


            }
        }
        /*
        registerCommand("formatmessage") { args ->
            val colorName = args.firstOrNull()
            if (colorName == null) {
                ChatUtils.messageToChat("Error: Invalid usage: /formatmessage <color> <message>")
            }
            val colorCode = when (colorName) {
                "red" -> "§c"
                "blue" -> "§9"
                "green" -> "§a"
                "yellow" -> "§e"
                "pink" -> "§d"
                else -> {
                    ChatUtils.messageToChat("Error: Invalid color '$colorName'!")
                    ChatUtils.messageToChat("Possible Colors are red, blue, green, yellow and pink")
                    return@registerCommand
                }
            }

            val rest = args.drop(1)
            if (rest.isEmpty()) {
                ChatUtils.messageToChat("Error: Message can not be empty!")
                return@registerCommand
            }

            ChatUtils.messageToChat(colorCode + rest.joinToString(" "))
        }*/
    }



    private fun registerCommand(name: String, function: (Array<String>) -> Unit) {
        ClientCommandHandler.instance.registerCommand(SimpleCommand(name, createCommand(function)))
    }

    private fun registerCommand0(
        name: String,
        function: (Array<String>) -> Unit,
        autoComplete: ((Array<String>) -> List<String>) = { listOf() }
    ) {
        val command = SimpleCommand(
            name,
            createCommand(function),
            object : SimpleCommand.TabCompleteRunnable {
                override fun tabComplete(sender: ICommandSender?, args: Array<String>?, pos: BlockPos?): List<String> {
                    return autoComplete(args ?: emptyArray())
                }
            }
        )
        ClientCommandHandler.instance.registerCommand(command)
    }

    private fun createCommand(function: (Array<String>) -> Unit) = object : ProcessCommandRunnable() {
        override fun processCommand(sender: ICommandSender?, args: Array<String>?) {
            if (args != null) function(args.asList().toTypedArray())
        }
    }
}