package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import java.util.List;

public class CoordsModule extends Module {
    public CoordsModule() { super("Coordinates", "Shows XYZ position", true); }
    @Override public List<HudLine> hudLines(Minecraft mc) {
        if (mc.player == null) return List.of();
        return List.of(new HudLine(String.format("XYZ: %.1f / %.1f / %.1f", mc.player.getX(), mc.player.getY(), mc.player.getZ())));
    }
}
