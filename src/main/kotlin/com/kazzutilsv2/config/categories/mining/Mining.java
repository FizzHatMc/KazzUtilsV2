package com.kazzutilsv2.config.categories.mining;

import com.google.gson.annotations.Expose;
import com.kazzutilsv2.config.categories.mining.feature.CommissionTracker;
import io.github.notenoughupdates.moulconfig.annotations.Accordion;
import io.github.notenoughupdates.moulconfig.annotations.Category;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean;
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption;

public class Mining {

    @Expose
    @ConfigOption(name = "Star Cult Notification", desc =  "")
    @ConfigEditorBoolean
    public boolean starCult = false;

    @Expose
    @ConfigOption(name = "Commmision Trakcer", desc = "")
    @Accordion
    public CommissionTracker commissionTracker = new CommissionTracker();


}
