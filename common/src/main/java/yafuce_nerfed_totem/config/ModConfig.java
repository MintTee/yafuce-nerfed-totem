package yafuce_nerfed_totem.config;

import java.util.List;

public class ModConfig {

    /* =====================
       Totem cooldown system
       ===================== */

    /** Enable or disable totem cooldown checks */
    public boolean totemCooldownEnabled = true;

    /** If true, totems never activate, regardless of cooldowns */
    public boolean alwaysBlockTotemActivation = false;

    /** Stops raid spawned evokers from dropping totems. Obtaining a totem require a Woodland mansion Evoker, making totems unfarmable */
    public boolean unfarmableTotems = true;

    /** List of items with cooldown when a totem pops */
    public List<TotemCooldownEntry> cooldowns = List.of(
            new TotemCooldownEntry("minecraft:totem_of_undying", 30*20, true),
            new TotemCooldownEntry("minecraft:elytra", 10*20, true),
            new TotemCooldownEntry("minecraft:ender_pearl", 10*20, true),
            new TotemCooldownEntry("minecraft:chorus_fruit", 10*20, true),
            new TotemCooldownEntry("minecraft:trident", 10*20, true),
            new TotemCooldownEntry("minecraft:firework_rocket", 10*20, true),
            new TotemCooldownEntry("minecraft:wind_charge", 10*20, true)

    );

    /* =====================
       Totem status effects
       ===================== */

    public List<TotemEffectEntry> effects = List.of(
            new TotemEffectEntry("minecraft:weakness", 30*20, 1),
            new TotemEffectEntry("minecraft:mining_fatigue", 30*20, 2),
            new TotemEffectEntry("minecraft:blindness", 10*20, 3),
            new TotemEffectEntry("minecraft:hunger", 30*20, 0),
            new TotemEffectEntry("minecraft:slowness", 10*20, 2)
    );

    public ModConfig() {}
}
