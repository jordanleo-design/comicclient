package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class FullbrightModule extends Module {
    public FullbrightModule() { super("Fullbright", "Client-side night vision", false); }

    @Override public void onTick(Minecraft mc) {
        if (mc.player == null) return;
        // Visual-only: effect is applied to the local player entity, not sent to the server.
        MobEffectInstance cur = mc.player.getEffect(MobEffects.NIGHT_VISION);
        if (cur == null || cur.getDuration() < 300)
            mc.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 0, false, false, false));
    }
    @Override public void onDisable(Minecraft mc) {
        if (mc.player != null) mc.player.removeEffect(MobEffects.NIGHT_VISION);
    }
}
