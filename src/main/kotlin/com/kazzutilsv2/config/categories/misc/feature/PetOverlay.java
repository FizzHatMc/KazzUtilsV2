package com.kazzutilsv2.config.categories.misc.feature;

import com.google.gson.annotations.Expose;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorColour;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorSlider;
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption;

public class PetOverlay {

    @Expose
    @ConfigOption(name = "Pet Overlay ", desc = "")
    @ConfigEditorBoolean
    public boolean petOverlay = false;

    @Expose
    @ConfigOption(name = "Pet Overlay X", desc = "")
    @ConfigEditorSlider(minValue = 0, maxValue = 1920, minStep = 5)
    public int petOverlayX = 0;

    @Expose
    @ConfigOption(name = "Pet Overlay Y", desc = "")
    @ConfigEditorSlider(minValue = 0, maxValue = 1080, minStep = 5)
    public int petOverlayY = 0;

    @Expose
    @ConfigOption(name = "Pet Overlay Text Color", desc = "")
    @ConfigEditorColour
    public String petOverlayColor = "0:80:0:0:0";

}
