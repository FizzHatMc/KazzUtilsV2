package com.kazzutilsv2.config.categories.farming.feature.contest;

import com.google.gson.annotations.Expose;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorColour;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorSlider;
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption;

public class Contest {

    @Expose
    @ConfigOption(name = "Contest Display", desc = "")
    @ConfigEditorBoolean
    public boolean contestDisplay = false;

    @Expose
    @ConfigOption(name = "Contest Display  X", desc = "")
    @ConfigEditorSlider(minValue = 0, maxValue = 1920, minStep = 5)
    public int contestDisplayX = 0;

    @Expose
    @ConfigOption(name = "Contest Display Y", desc = "")
    @ConfigEditorSlider(minValue = 0, maxValue = 1080, minStep = 5)
    public int contestDisplayY = 0;

    @Expose
    @ConfigOption(name = "Contest Display Text Color", desc = "")
    @ConfigEditorColour
    public String contestDisplayColor = "0:80:0:0:0";

}
