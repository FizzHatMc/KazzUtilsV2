package com.kazzutilsv2.config.categories.dungeon;

import com.google.gson.annotations.Expose;
import com.kazzutilsv2.config.categories.dungeon.feature.DungeonClass;
import com.kazzutilsv2.config.categories.dungeon.feature.ItemConf;
import com.kazzutilsv2.config.categories.dungeon.feature.Waypoints;
import io.github.notenoughupdates.moulconfig.annotations.Category;


public class Dungeon {

    @Expose
    @Category(name = "Items", desc = "Settings for different Items")
    public ItemConf items = new ItemConf();

    @Expose
    @Category(name = "Class", desc = "Settings for the Classes")
    public DungeonClass dungeonClass = new DungeonClass();

    @Expose
    @Category(name = "Waypoints", desc = "Settings for Waypoints in Dungeon")
    public Waypoints waypoints = new Waypoints();

}