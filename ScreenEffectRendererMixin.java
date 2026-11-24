package dev.comic.mixin;

import dev.comic.module.impl.LowFireModule;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {
    // require = 0: if Mojang changes this method, the mixin is skipped instead of crashing the game.
    @Inject(method = "renderFire", at = @At("HEAD"), cancellable = true, require = 0)
    private static void comic$noFire(CallbackInfo ci) {
        if (LowFireModule.active) ci.cancel();
    }
}
