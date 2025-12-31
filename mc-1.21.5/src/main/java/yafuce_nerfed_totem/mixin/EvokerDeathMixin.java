package yafuce_nerfed_totem.mixin;

import yafuce_nerfed_totem.config.ConfigIO;

import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(LivingEntity.class)
public abstract class EvokerDeathMixin {

    @Inject(method = "dropLoot", at = @At("HEAD"), cancellable = true)
    private void onDropLoot(ServerWorld world, DamageSource damageSource, boolean causedByPlayer, CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;

        if (!ConfigIO.CURRENT.unfarmableTotems) return;
        if (!(self instanceof EvokerEntity evoker)) return;
        if (evoker.hasRaid()) { ci.cancel(); }
    }
}