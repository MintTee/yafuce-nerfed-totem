package me.yafuce.yafuce_nerfed_totem.config;

/** Represents a status effect to apply when a totem pops */
public class TotemEffectEntry {
    public String effectId;
    public int durationTicks;
    public int amplifier;

    public TotemEffectEntry() {}

    public TotemEffectEntry(String effectId, int durationTicks, int amplifier) {
        this.effectId = effectId;
        this.durationTicks = durationTicks;
        this.amplifier = amplifier;
    }
}
