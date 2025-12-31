package yafuce_nerfed_totem.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.packet.s2c.play.PlayerAbilitiesS2CPacket;
import yafuce_nerfed_totem.mixin.accessor.EntityAccessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityFallFlyingMixin {

    @Unique
    private static final int GLIDING_FLAG_INDEX = 7;

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void cancelGlideOnCooldown(CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;

        if (!(self instanceof ServerPlayerEntity player)) return;

        boolean isFlying = ((EntityAccessor) self).callGetFlag(GLIDING_FLAG_INDEX);
        if (!isFlying) return;

        ItemStack chestStack = player.getEquippedStack(EquipmentSlot.CHEST);
        if (player.getItemCooldownManager().isCoolingDown(chestStack.getItem())) {

            ((EntityAccessor) self).callSetFlag(GLIDING_FLAG_INDEX, false);

            player.networkHandler.sendPacket(new PlayerAbilitiesS2CPacket(player.getAbilities()));
        }
    }
}
