package com.kazzutilsv2.config.categories.hud;

import com.google.gson.annotations.Expose;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorColour;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorSlider;
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption;

public class mythologicalEvent {

    @Expose
    @ConfigOption(name = "Mythological Tracker ", desc = "")
    @ConfigEditorBoolean
    public boolean mytholocialTracker = false;

    @Expose
    @ConfigOption(name = "Mythological Tracker X", desc = "")
    @ConfigEditorSlider(minValue = 0, maxValue = 1920, minStep = 5)
    public int mytholocialTrackerX = 0;

    @Expose
    @ConfigOption(name = "Mythological Tracker Y", desc = "")
    @ConfigEditorSlider(minValue = 0, maxValue = 1080, minStep = 5)
    public int mytholocialTrackerY = 0;

    @Expose
    @ConfigOption(name = "Mythological Tracker Text Color", desc = "")
    @ConfigEditorColour
    public String mythoColor = "0:80:0:0:0";

}
