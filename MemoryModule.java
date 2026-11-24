package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import java.util.List;

public class MemoryModule extends Module {
    public MemoryModule() { super("Memory", "RAM usage", false); }
    @Override public List<HudLine> hudLines(Minecraft mc) {
        Runtime r = Runtime.getRuntime();
        long used = (r.totalMemory() - r.freeMemory()) >> 20, max = r.maxMemory() >> 20;
        return List.of(new HudLine("RAM: " + used + "/" + max + " MB"));
    }
}
