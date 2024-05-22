package com.kazzutilsv2.utils

import com.kazzutilsv2.KazzUtilsV2.Companion.mc
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.ContainerChest
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

object ContainerUtils {


    fun getLore(stack: ItemStack?): List<String> {
        val lore: MutableList<String> = ArrayList()
        if (stack == null) return lore
        // Überprüfen, ob der ItemStack eine Lore hat
        if (stack.hasTagCompound() && stack.tagCompound.hasKey("display", 10)) {
            val display = stack.tagCompound.getCompoundTag("display")
            if (display.hasKey("Lore", 9)) {
                val loreList = display.getTagList("Lore", 8)
                for (i in 0 until loreList.tagCount()) {
                    lore.add(loreList.getStringTagAt(i))
                }
            }
        }


        return lore
    }


    fun checkInventoryForName(itemName: String?, inventory2: IInventory): ItemStack? {
        for (i in 0 until inventory2.sizeInventory) {
            if (inventory2.getStackInSlot(i) == null) continue
            if (inventory2.getStackInSlot(i).displayName.contains(itemName!!)) return inventory2.getStackInSlot(i)
        }
        return null
    }

    fun getItemsInOpenChest(): List<Slot> {
        val slots: MutableList<Slot> = ArrayList()
        val screen: Any = mc.currentScreen
        if (screen is GuiChest) {
            val container = screen.inventorySlots
            if (container is ContainerChest) {
                for (slotObj in container.inventorySlots) {
                    if (slotObj is Slot) {
                        val slot = slotObj
                        if (slot.inventory is InventoryPlayer) {
                            break
                        }
                        if (slot.stack != null) {
                            slots.add(slot)
                        }
                    }
                }
            }
        }
        return slots
    }


    fun openInventoryName(): String {
        val screen: Any = mc.currentScreen
        if (screen is GuiChest) {
            val container = screen.inventorySlots
            if (container is ContainerChest) {
                return container.lowerChestInventory.displayName.formattedText.trim { it <= ' ' }
            }
        }
        return ""
    }


    fun getCurrentScreen(): GuiContainer? {
        if (mc.currentScreen is GuiContainer) {
            return mc.currentScreen as GuiContainer
        }
        return null
    }

}