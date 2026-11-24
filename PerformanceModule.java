package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;

/** Applies a set of cheap client settings that reduce GPU/CPU load, and restores yours when disabled. */
public class PerformanceModule extends Module {
    private boolean applied;
    private int rd, sim, blend, fpsLimit;
    private double entityScale;
    private boolean shadows, vsync;

    public PerformanceModule() { super("Performance Mode", "Lowers render/simulation distance, shadows, vsync", false); }

    @Override public void onTick(Minecraft mc) {
        if (applied) return;
        Options o = mc.options;
        rd = o.renderDistance().get();            o.renderDistance().set(Math.min(rd, 10));
        sim = o.simulationDistance().get();       o.simulationDistance().set(Math.min(sim, 8));
        entityScale = o.entityDistanceScaling().get(); o.entityDistanceScaling().set(Math.min(entityScale, 0.75));
        shadows = o.entityShadows().get();        o.entityShadows().set(false);
        blend = o.biomeBlendRadius().get();       o.biomeBlendRadius().set(1);
        vsync = o.enableVsync().get();            o.enableVsync().set(false);
        fpsLimit = o.framerateLimit().get();      o.framerateLimit().set(260);
        applied = true;
    }

    @Override public void onDisable(Minecraft mc) {
        if (!applied) return;
        Options o = mc.options;
        o.renderDistance().set(rd);  o.simulationDistance().set(sim);
        o.entityDistanceScaling().set(entityScale); o.entityShadows().set(shadows);
        o.biomeBlendRadius().set(blend);
        o.enableVsync().set(vsync); o.framerateLimit().set(fpsLimit);
        applied = false;
    }
}
