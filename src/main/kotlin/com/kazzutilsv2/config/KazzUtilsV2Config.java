package com.kazzutilsv2.config;


import com.google.gson.annotations.Expose;
import com.kazzutilsv2.KazzUtilsV2;
import com.kazzutilsv2.config.categories.dungeon.Dungeon;
import com.kazzutilsv2.config.categories.farming.Farming;
import com.kazzutilsv2.config.categories.mining.Mining;
import com.kazzutilsv2.config.categories.misc.Misc;
import io.github.notenoughupdates.moulconfig.Config;
import io.github.notenoughupdates.moulconfig.annotations.Category;


public class KazzUtilsV2Config extends Config {


    @Override
    public String getTitle(){
        return "KazzUtilsV2 "+ KazzUtilsV2.getVersion()+" by §aRealKazz§r, preset by §channibal22§r ";
    }

    @Override
    public void saveNow() {
        KazzUtilsV2.configManager.save();
    }

    @Expose
    @Category(name = "Dungeon", desc = "Dungeon shit")
    public Dungeon dungeon = new Dungeon();

    @Expose
    @Category(name = "Mining", desc = "Mining shit")
    public Mining mining = new Mining();

    @Expose
    @Category(name = "Farming", desc = "Farming")
    public Farming farming = new Farming();

    @Expose
    @Category(name = "Misc", desc = "Random Settings")
    public Misc misc = new Misc();




}
