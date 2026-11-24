package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import java.util.List;

public class SpeedModule extends Module {
    public SpeedModule() { super("Speed", "Horizontal speed in blocks/s", false); }
    @Override public List<HudLine> hudLines(Minecraft mc) {
        if (mc.player == null) return List.of();
        return List.of(new HudLine(String.format("Speed: %.2f b/s", mc.player.getDeltaMovement().horizontalDistance() * 20)));
    }
}
