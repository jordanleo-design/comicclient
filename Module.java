package dev.comic.module;

import net.minecraft.client.Minecraft;
import java.util.List;

public abstract class Module {
    public final String name;
    public final String description;
    public boolean enabled;

    protected Module(String name, String description, boolean enabledByDefault) {
        this.name = name;
        this.description = description;
        this.enabled = enabledByDefault;
    }

    public void toggle() {
        enabled = !enabled;
        if (enabled) onEnable(Minecraft.getInstance()); else onDisable(Minecraft.getInstance());
    }

    public void onEnable(Minecraft mc) {}
    public void onDisable(Minecraft mc) {}
    public void onTick(Minecraft mc) {}

    /** HUD modules return lines to draw in the stacked HUD panel; others return empty. */
    public List<HudLine> hudLines(Minecraft mc) { return List.of(); }

    public record HudLine(String text, int color) {
        public HudLine(String text) { this(text, 0xFFFFFFFF); }
    }
}
