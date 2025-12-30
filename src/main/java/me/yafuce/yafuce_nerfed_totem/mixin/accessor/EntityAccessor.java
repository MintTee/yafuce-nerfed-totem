package me.yafuce.yafuce_nerfed_totem.mixin.accessor;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface EntityAccessor {
    @Invoker("setFlag")
    void callSetFlag(int index, boolean value);

    @Invoker("getFlag")
    boolean callGetFlag(int index);
}
