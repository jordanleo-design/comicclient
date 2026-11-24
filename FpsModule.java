package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import java.util.List;

public class FpsModule extends Module {
    public FpsModule() { super("FPS", "Shows your framerate", true); }
    @Override public List<HudLine> hudLines(Minecraft mc) { return List.of(new HudLine("FPS: " + mc.getFps())); }
}
