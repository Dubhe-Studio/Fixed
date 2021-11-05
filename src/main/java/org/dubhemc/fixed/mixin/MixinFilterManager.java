package org.dubhemc.fixed.mixin;

import net.minecraft.client.gui.social.FilterManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(FilterManager.class)
public class MixinFilterManager {
    @Inject(method = "func_244757_e", at = @At("HEAD"), cancellable = true, remap = false)
    public void disableBlocklistCheck(UUID uuid, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
