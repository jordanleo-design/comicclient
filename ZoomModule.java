package dev.comic.module.impl;

import dev.comic.ComicClient;
import dev.comic.module.Module;
import net.minecraft.client.Minecraft;

public class ZoomModule extends Module {
    private Integer original;
    public ZoomModule() { super("Zoom", "Hold C to zoom", true); }

    @Override public void onTick(Minecraft mc) {
        boolean held = enabled && ComicClient.zoomKey.isDown() && mc.gui.screen() == null;
        if (held && original == null) {
            original = mc.options.fov().get();
            mc.options.fov().set(30);
        } else if (!held && original != null) {
            mc.options.fov().set(original);
            original = null;
        }
    }
}
