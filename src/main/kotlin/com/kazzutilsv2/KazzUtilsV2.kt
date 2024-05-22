package com.kazzutilsv2

import com.kazzutilsv2.commands.CommandManager
import com.kazzutilsv2.config.ConfigManager
import com.kazzutilsv2.config.KazzUtilsV2Config
import com.kazzutilsv2.config.categories.misc.feature.arrowsoulflowhud.Soulflow
import com.kazzutilsv2.data.enumClass.DunClass
import com.kazzutilsv2.features.Mining.CommissionTracker
import com.kazzutilsv2.features.Mining.StarCultNotif
import com.kazzutilsv2.features.chatCommands.ChatCommands
import com.kazzutilsv2.features.dungeon.F7.CrystalWaypoints
import com.kazzutilsv2.features.dungeon.F7.TerminalWaypoints
import com.kazzutilsv2.features.dungeon.HighlightClass
import com.kazzutilsv2.features.dungeon.M7.RelicWaypoints
import com.kazzutilsv2.features.dungeon.MaskTimer
import com.kazzutilsv2.features.dungeon.PlayerClass
import com.kazzutilsv2.features.dungeon.TankRange
import com.kazzutilsv2.features.events.mythological.MythoTracker
import com.kazzutilsv2.features.events.mythological.MythoTrackerHud
import com.kazzutilsv2.features.farming.contest.ContestHud
import com.kazzutilsv2.features.farming.gardenlevel.GardenLevelHud
import com.kazzutilsv2.features.hud.ArrowsNotif
import com.kazzutilsv2.features.hud.PetOverlay
import com.kazzutilsv2.features.hud.SoulflowNotif
import com.kazzutilsv2.features.keyshortcut.KeyShortcuts
import com.kazzutilsv2.features.misc.items.GyroRange
import com.kazzutilsv2.features.test.render.TestClass
import com.kazzutilsv2.utils.CatacombsUtils
import com.kazzutilsv2.utils.TabUtils
import net.minecraft.client.Minecraft
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import java.io.File

@Mod(modid = KazzUtilsV2.MOD_ID, version = "1.0.0", useMetadata = true)
class KazzUtilsV2 {

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        configManager = ConfigManager()
        MinecraftForge.EVENT_BUS.register(configManager)

        arrayOf(
            MythoTrackerHud,
            ContestHud,
            GardenLevelHud,
            PetOverlay,
            CommissionTracker,
            ArrowsNotif,
            SoulflowNotif

        ).forEach(MinecraftForge.EVENT_BUS::register)
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        CommandManager()


        /**FEATURES*/
        reg(this)
        reg(PlayerClass())
        reg(MaskTimer())
        reg(CrystalWaypoints())
        reg(TerminalWaypoints())
        reg(RelicWaypoints())
        reg(HighlightClass())
        reg(TankRange())
        reg(GyroRange())
        reg(TestClass())
        reg(ChatCommands())
        reg(MythoTracker())
        reg(KeyShortcuts())


    }


    var ticks = 0L

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (event.phase != TickEvent.Phase.START || mc.thePlayer == null) return

        ticks++

        if(ticks % 2 == 0L) {
            TabUtils.parseTabEntries()
            CatacombsUtils.checkCata()
            DunClass.setupName()
        }//each 1/10th second
        if(ticks % 20 == 0L) {
            if(config.mining.starCult) StarCultNotif.checkCult()


        }//each second
        if(ticks % 600 == 0L) {

        }//30 sec
        if(ticks % 6000 == 0L) {

        }//5 min


    }

    companion object {
        lateinit var configManager: ConfigManager
        const val MOD_ID = "kazzutilsv2"
        val mc: Minecraft = Minecraft.getMinecraft()
        val modDir by lazy {
            File(File(mc.mcDataDir, "config"), "kazzutilsv2").also {
                it.mkdirs()
                File(it, "trackers").mkdirs()
            }
        }


        @JvmStatic
        val version: String
            get() = Loader.instance().indexedModList[MOD_ID]!!.version

        val config: KazzUtilsV2Config
            get() = configManager.config ?: error("config is null")
    }




    private fun reg(obj: Any){
        MinecraftForge.EVENT_BUS.register(obj);
    }
}
