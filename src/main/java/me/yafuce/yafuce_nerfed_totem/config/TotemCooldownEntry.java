package me.yafuce.yafuce_nerfed_totem.config;

/** Represents an item cooldown entry */
public class TotemCooldownEntry {
    public String itemId;
    public int cooldownTicks;
    public boolean enabled;

    public TotemCooldownEntry() {}

    public TotemCooldownEntry(String itemId, int cooldownTicks, boolean enabled) {
        this.itemId = itemId;
        this.cooldownTicks = cooldownTicks;
        this.enabled = enabled;
    }
}
