package me.yafuce.yafuce_nerfed_totem.mixin;

import me.yafuce.yafuce_nerfed_totem.config.TotemEffectEntry;
import me.yafuce.yafuce_nerfed_totem.config.TotemCooldownEntry;
import me.yafuce.yafuce_nerfed_totem.config.ConfigIO;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class TotemPopMixin {

    @Inject(method = "tryUseDeathProtector", at = @At("RETURN"))
    private void onTotemPop(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return; // Only trigger if totem actually popped

        LivingEntity entity = (LivingEntity) (Object) this;
        if (!(entity instanceof ServerPlayerEntity player)) return;

        /* -------- Status effects -------- */
        for (TotemEffectEntry entry : ConfigIO.CURRENT.effects) {
            Identifier id = Identifier.tryParse(entry.effectId);
            if (id == null) continue;

            Registries.STATUS_EFFECT.getEntry(id).ifPresent(effect ->
                    player.addStatusEffect(new StatusEffectInstance(
                            effect,
                            entry.durationTicks,
                            entry.amplifier
                    ))
            );
        }

        /* -------- Totem cooldowns -------- */
        if (!ConfigIO.CURRENT.totemCooldownEnabled) return;

        PlayerInventory inv = player.getInventory();

        // Apply cooldown to main inventory
        for (int i = 0; i < inv.size(); i++) {
            applyCooldown(player, inv.getStack(i));
        }

        // Apply cooldown to offhand
        applyCooldown(player, inv.getStack(PlayerInventory.OFF_HAND_SLOT));

        // Apply cooldown to chest slot (elytra)
        applyCooldown(player, player.getEquippedStack(EquipmentSlot.CHEST));
    }

    @Unique
    private void applyCooldown(ServerPlayerEntity player, ItemStack stack) {
        if (stack == null) return;

        for (TotemCooldownEntry entry : ConfigIO.CURRENT.cooldowns) {
            if (!entry.enabled) continue;

            Identifier id = Identifier.tryParse(entry.itemId);
            if (id == null) continue;

            if (stack.isOf(Registries.ITEM.get(id))) {
                player.getItemCooldownManager().set(stack, entry.cooldownTicks);
            }
        }
    }
}
