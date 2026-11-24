package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import java.util.*;

public class PotionEffectsModule extends Module {
    public PotionEffectsModule() { super("Potion Effects", "Active effects with timers", true); }
    @Override public List<HudLine> hudLines(Minecraft mc) {
        if (mc.player == null) return List.of();
        List<HudLine> out = new ArrayList<>();
        for (MobEffectInstance e : mc.player.getActiveEffects()) {
            String name = e.getEffect().value().getDisplayName().getString() + " " + (e.getAmplifier() + 1);
            String time = e.isInfiniteDuration() ? "∞" : String.format("%d:%02d", e.getDuration() / 1200, (e.getDuration() / 20) % 60);
            out.add(new HudLine(name + " (" + time + ")", 0xFFFFAA00));
        }
        return out;
    }
}
