package yafuce_nerfed_totem.mixin;

import yafuce_nerfed_totem.config.TotemCooldownEntry;
import yafuce_nerfed_totem.config.ConfigIO;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class TotemCooldownCheckMixin {

    @Inject(method = "tryUseDeathProtector", at = @At("HEAD"), cancellable = true)
    private void beforeTryUseDeathProtector(DamageSource source, CallbackInfoReturnable<Boolean> cir) {

        LivingEntity entity = (LivingEntity) (Object) this;
        if (!(entity instanceof ServerPlayerEntity player)) return;
        if (ConfigIO.CURRENT.alwaysBlockTotemActivation) {cir.setReturnValue(false); return;}
        if (!ConfigIO.CURRENT.totemCooldownEnabled) return;

        PlayerInventory inv = player.getInventory();

        for (TotemCooldownEntry entry : ConfigIO.CURRENT.cooldowns) {
            if (!entry.enabled) continue;

            Identifier id = Identifier.tryParse(entry.itemId);
            if (id == null) continue;

            Item item = Registries.ITEM.get(id);

            // main inventory
            for (int i = 0; i < inv.size(); i++) {
                ItemStack stack = inv.getStack(i);
                if (stack.isOf(item) && player.getItemCooldownManager().isCoolingDown(stack)) {
                    cir.setReturnValue(false);
                    return;
                }
            }

            // offhand
            ItemStack offhand = inv.getStack(PlayerInventory.OFF_HAND_SLOT);
            if (offhand.isOf(item) && player.getItemCooldownManager().isCoolingDown(offhand)) {
                cir.setReturnValue(false);
                return;
            }

            // chest piece (elytra)
            ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
            if (chest.isOf(item) && player.getItemCooldownManager().isCoolingDown(chest)) {
                cir.setReturnValue(false);
                return;
            }
        }
    }
}