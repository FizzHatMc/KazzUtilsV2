package com.kazzutilsv2.config.categories.mining.feature;

import com.google.gson.annotations.Expose;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorColour;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorSlider;
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption;

public class CommissionTracker {

    @Expose
    @ConfigOption(name = "Commission Tracker ", desc = "")
    @ConfigEditorBoolean
    public boolean commissionTrackerOverlay = false;

    @Expose
    @ConfigOption(name = "Commission Tracker X", desc = "")
    @ConfigEditorSlider(minValue = 0, maxValue = 1920, minStep = 5)
    public int commissionTrackerOverlayX = 0;

    @Expose
    @ConfigOption(name = "Commission Tracker Y", desc = "")
    @ConfigEditorSlider(minValue = 0, maxValue = 1080, minStep = 5)
    public int commissionTrackerOverlayY = 0;

    @Expose
    @ConfigOption(name = "Commission Tracker Text Color", desc = "")
    @ConfigEditorColour
    public String commissionTrackerOverlayColor = "0:80:0:0:0";


}
