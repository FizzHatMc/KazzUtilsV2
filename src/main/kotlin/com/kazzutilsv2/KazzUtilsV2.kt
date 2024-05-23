package com.kazzutilsv2

import com.kazzutilsv2.commands.CommandManager
import com.kazzutilsv2.config.ConfigManager
import com.kazzutilsv2.config.KazzUtilsV2Config
import com.kazzutilsv2.core.GuiManager
import com.kazzutilsv2.core.PersistentSave
import com.kazzutilsv2.data.enumClass.DunClass

import com.kazzutilsv2.features.chatCommands.ChatCommands
import com.kazzutilsv2.features.deployable.DeployableHud
import com.kazzutilsv2.features.deployable.DeployableManager
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
import com.kazzutilsv2.features.hud.uioverlay.*
import com.kazzutilsv2.features.keyshortcut.KeyShortcuts
import com.kazzutilsv2.features.mining.CommissionTracker
import com.kazzutilsv2.features.mining.StarCultNotif
import com.kazzutilsv2.features.misc.items.GyroRange
import com.kazzutilsv2.features.test.render.TestClass
import com.kazzutilsv2.utils.CatacombsUtils
import com.kazzutilsv2.utils.ChatUtils
import com.kazzutilsv2.utils.TabUtils
import com.kazzutilsv2.utils.Utils
import com.kazzutilsv2.utils.colors.CustomColor
import com.kazzutilsv2.utils.graphics.ScreenRenderer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.entity.item.EntityArmorStand
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import java.io.File
import java.util.*

@Mod(modid = KazzUtilsV2.MOD_ID, version = "1.0.0", useMetadata = true)
class KazzUtilsV2 {

    @Mod.EventHandler
    fun init(@Suppress("UNUSED_PARAMETER") event: FMLInitializationEvent) {
        configManager = ConfigManager()
        MinecraftForge.EVENT_BUS.register(configManager)

        arrayOf(
            this,
            guiManager,
            MythoTrackerHud,
            KeyShortcuts,
            DefenseOverlay,
            ChatUtils,
            ScreenRenderer,
            EffectiveHPOverlay,
            SkillOverlay,
            HPOverlay,
            ManaOverlay,
            ArrowsNotif,
            SoulflowNotif,
            PetOverlay,
            CommissionTracker,
            GardenLevelHud,
            ContestHud,
            DeployableHud

        ).forEach(MinecraftForge.EVENT_BUS::register)
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        CommandManager()
        guiManager = GuiManager

        /**FEATURES*/
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
        reg(DeployableManager())




    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent){
        PersistentSave.loadData()
    }

    @SubscribeEvent
    fun onEntityEvent(event: LivingEvent.LivingUpdateEvent) {
        if(event.entity is EntityArmorStand) DeployableManager.instance.detectDeployables(event.entity as EntityArmorStand)
    }


    private var ticks = 0L

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
            Utils.checkSkyblock()




        }//each second

        if (displayScreen != null) {
            if (mc.thePlayer?.openContainer == mc.thePlayer?.inventoryContainer) {
                mc.displayGuiScreen(displayScreen)
                displayScreen = null
            }
        }
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

        val json = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            serializersModule = SerializersModule {
                include(serializersModule)
                contextual(CustomColor::class, CustomColor.Serializer)
                contextual(Regex::class, RegexAsString)
                contextual(UUID::class, UUIDAsString)
            }
        }

        @JvmField
        var displayScreen: GuiScreen? = null

        @JvmStatic
        lateinit var guiManager: GuiManager

        @JvmStatic
        val version: String
            get() = Loader.instance().indexedModList[MOD_ID]!!.version

        val config: KazzUtilsV2Config
            get() = configManager.config ?: error("config is null")
    }




    private fun reg(obj: Any){
        MinecraftForge.EVENT_BUS.register(obj)
    }

    object RegexAsString : KSerializer<Regex> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Regex", PrimitiveKind.STRING)
        override fun deserialize(decoder: Decoder): Regex = Regex(decoder.decodeString())
        override fun serialize(encoder: Encoder, value: Regex) = encoder.encodeString(value.pattern)
    }

    object UUIDAsString : KSerializer<UUID> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)
        override fun deserialize(decoder: Decoder): UUID = UUID.fromString(decoder.decodeString())
        override fun serialize(encoder: Encoder, value: UUID) = encoder.encodeString(value.toString())
    }

    private fun String.toDashedUUID(): String {
        if (this.length != 32) return this
        return buildString {
            append(this@toDashedUUID)
            insert(20, "-")
            insert(16, "-")
            insert(12, "-")
            insert(8, "-")
        }
    }



}
