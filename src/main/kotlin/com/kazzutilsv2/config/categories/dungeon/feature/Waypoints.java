package com.kazzutilsv2.config.categories.dungeon.feature;

import com.google.gson.annotations.Expose;
import com.kazzutilsv2.config.categories.dungeon.feature.waypoints.m7f7.m7f7;
import io.github.notenoughupdates.moulconfig.annotations.Accordion;
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption;

public class Waypoints {

    @Expose
    @ConfigOption(name = "M7/F7 Wayoints", desc = "")
    @Accordion
    public m7f7 m7f7 = new m7f7();

}
