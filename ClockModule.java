package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClockModule extends Module {
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("HH:mm:ss");
    public ClockModule() { super("Clock", "Real-world time", false); }
    @Override public List<HudLine> hudLines(Minecraft mc) { return List.of(new HudLine(LocalTime.now().format(F))); }
}
