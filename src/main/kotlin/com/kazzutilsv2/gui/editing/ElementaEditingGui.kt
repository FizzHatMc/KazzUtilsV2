/*
 * Skytils - Hypixel Skyblock Quality of Life Mod
 * Copyright (C) 2020-2023 Skytils
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.kazzutilsv2.gui.editing

import com.kazzutilsv2.KazzUtilsV2
import com.kazzutilsv2.core.GuiManager
import com.kazzutilsv2.core.PersistentSave
import com.kazzutilsv2.gui.ReopenableGUI
import com.kazzutilsv2.gui.editing.components.LocationComponent
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.dsl.childOf


class ElementaEditingGui : WindowScreen(ElementaVersion.V2), ReopenableGUI {
    override fun initScreen(width: Int, height: Int) {
        super.initScreen(width, height)
        KazzUtilsV2.guiManager.elements.forEach { (_, element) ->
            if (!element.toggled) return@forEach
            LocationComponent(element) childOf window
        }
    }

    override fun onScreenClose() {
        super.onScreenClose()
        PersistentSave.markDirty<GuiManager>()
    }
}