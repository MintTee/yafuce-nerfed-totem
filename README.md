Totems of Undying are ridiculously overpowered in Minecraft, trivializing hardcore survival and PvP/PvE encounters. This mod aims to rebalance them.

---

## Features

- **Cooldowns** _(configurable)_

  Popping a Totem now triggers cooldowns on items (Totems themselves, Elytras, Tridents, Ender Pearls, Chorus Fruits).  


- **Negative Effects**  _(configurable)_

  Popping a Totem applies penalties such as Weakness, Mining Fatigue, Blindness, Hunger, and Slowness.  


- **Totem scarcity** _(optional, on by default)_

  - Evokers in raids no longer drop Totems (making them unfarmable using raid farms).
  - Only Evokers in Woodland Mansions drop Totems, making them rare and precious.


- **Disable Totems altogether** _(optional, off by default)_

  Disable Totems / any death-protecting items entirely.

---

### Configuring the Mod

Edit ```config/yafuce_nerfed_totem.json``` to configure the mod.

<details>
<summary>Default config</summary>

```json
{
  "totemCooldownEnabled": true,
  "alwaysBlockTotemActivation": false,
  "unfarmableTotems": true,
  "cooldowns": [
    {
      "itemId": "minecraft:totem_of_undying",
      "cooldownTicks": 600,
      "enabled": true
    },
    {
      "itemId": "minecraft:elytra",
      "cooldownTicks": 200,
      "enabled": true
    },
    {
      "itemId": "minecraft:ender_pearl",
      "cooldownTicks": 200,
      "enabled": true
    },
    {
      "itemId": "minecraft:chorus_fruit",
      "cooldownTicks": 200,
      "enabled": true
    },
    {
      "itemId": "minecraft:trident",
      "cooldownTicks": 200,
      "enabled": true
    }
  ],
  "effects": [
    {
      "effectId": "minecraft:weakness",
      "durationTicks": 600,
      "amplifier": 1
    },
    {
      "effectId": "minecraft:mining_fatigue",
      "durationTicks": 600,
      "amplifier": 2
    },
    {
      "effectId": "minecraft:blindness",
      "durationTicks": 200,
      "amplifier": 3
    },
    {
      "effectId": "minecraft:hunger",
      "durationTicks": 600,
      "amplifier": 0
    },
    {
      "effectId": "minecraft:slowness",
      "durationTicks": 200,
      "amplifier": 2
    }
  ]
}

```

</details>