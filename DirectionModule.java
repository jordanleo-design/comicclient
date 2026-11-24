package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import java.util.List;

public class DirectionModule extends Module {
    public DirectionModule() { super("Direction", "Shows facing direction", false); }
    @Override public List<HudLine> hudLines(Minecraft mc) {
        if (mc.player == null) return List.of();
        return List.of(new HudLine("Facing: " + mc.player.getDirection().getName()));
    }
}
