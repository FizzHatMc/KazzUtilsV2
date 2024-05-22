package com.kazzutilsv2.config.categories.dungeon.feature;

import com.google.gson.annotations.Expose;
import com.kazzutilsv2.config.categories.dungeon.feature.classes.Tank;
import com.kazzutilsv2.data.enumClass.DunClass;
import io.github.notenoughupdates.moulconfig.annotations.Accordion;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorDropdown;
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption;

public class DungeonClass {

    @Expose
    @ConfigOption(name = "Highlight Class", desc = "")
    @ConfigEditorDropdown
    public DunClass enumClass = null;


    @Expose
    @ConfigOption(name = "Tank", desc = "")
    @Accordion
    public Tank tank = new Tank();

}

