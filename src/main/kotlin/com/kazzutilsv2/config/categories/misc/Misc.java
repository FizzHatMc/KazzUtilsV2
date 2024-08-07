package com.kazzutilsv2.config.categories.misc;

import com.google.gson.annotations.Expose;
import com.kazzutilsv2.KazzUtilsV2;
import com.kazzutilsv2.config.categories.event.mythologicalEvent;
import com.kazzutilsv2.config.categories.misc.feature.*;
import com.kazzutilsv2.gui.KeyShortcutsGui;
import com.kazzutilsv2.gui.editing.ElementaEditingGui;
import io.github.notenoughupdates.moulconfig.annotations.Accordion;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorButton;
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption;

public class Misc {


    @ConfigOption(name = "Edit Gui", desc = "Opens GUI Editor")
    @ConfigEditorButton(buttonText = "OPEN")
    public Runnable openGui = () -> KazzUtilsV2.displayScreen = new ElementaEditingGui();

    @ConfigOption(name = "Hotkeys", desc = "Opens Hotkeys Editor")
    @ConfigEditorButton(buttonText = "OPEN")
    public Runnable hotkeys = () -> KazzUtilsV2.displayScreen = new KeyShortcutsGui();


    @Expose
    @ConfigOption(name = "Deployables", desc = "")
    @Accordion
    public DeploayAble deployables = new DeploayAble();

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

    @Expose
    @ConfigOption(name = "Hud", desc = "")
    @Accordion
    public Hud hud = new Hud();



}
