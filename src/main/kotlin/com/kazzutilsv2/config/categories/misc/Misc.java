package com.kazzutilsv2.config.categories.misc;

import com.google.gson.annotations.Expose;
import com.kazzutilsv2.config.categories.hud.mythologicalEvent;
import com.kazzutilsv2.config.categories.misc.feature.*;
import io.github.notenoughupdates.moulconfig.annotations.Accordion;
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption;

public class Misc {



    @Expose
    @ConfigOption(name = "Items", desc = "")
    @Accordion
    public Items items = new Items();

    @Expose
    @ConfigOption(name = "PartyCommands", desc = "")
    @Accordion
    public PartyCommands partyCommands = new PartyCommands();

    @Expose
    @ConfigOption(name = "Mythological Event", desc = "")
    @Accordion
    public mythologicalEvent mythologicalEvent = new mythologicalEvent();

    @Expose
    @ConfigOption(name = "Pet Overlay", desc = "")
    @Accordion
    public PetOverlay petOverlay = new PetOverlay();

    @Expose
    @ConfigOption(name = "Arrows and Soulflow stuff", desc = "")
    @Accordion
    public ArrowSoulflowNotif arrowSoulflowNotif = new ArrowSoulflowNotif();

    @Expose
    @ConfigOption(name = "Item Animations", desc = "")
    @Accordion
    public ItemAnimations itemAnimations = new ItemAnimations();

}
