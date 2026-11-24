package dev.comic.screen;

import dev.comic.ComicClient;
import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ModulesScreen extends Screen {
    private final Screen parent;
    public ModulesScreen(Screen parent) { super(Component.literal("Modules")); this.parent = parent; }

    private static Component label(Module m) {
        return Component.literal(m.name + ": " + (m.enabled ? "§aON" : "§cOFF"));
    }

    @Override protected void init() {
        Component t = Component.literal("§b§lComic Client Modules");
        int tw = font.width(t);
        addRenderableWidget(new StringWidget(width / 2 - tw / 2, 12, tw, 12, t, font));

        List<Module> mods = ComicClient.modules.all();
        int colW = 150, gap = 6, top = 34;
        int startX = width / 2 - colW - gap / 2;
        for (int i = 0; i < mods.size(); i++) {
            Module m = mods.get(i);
            int x = startX + (i % 2) * (colW + gap);
            int y = top + (i / 2) * 24;
            addRenderableWidget(Button.builder(label(m), b -> {
                m.toggle();
                b.setMessage(label(m));
                ComicClient.modules.save();
            }).bounds(x, y, colW, 20).tooltip(net.minecraft.client.gui.components.Tooltip.create(Component.literal(m.description))).build());
        }
        addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, b -> onClose())
                .bounds(width / 2 - 100, height - 28, 200, 20).build());
    }

    @Override public void onClose() {
        ComicClient.modules.save();
        Minecraft.getInstance().gui.setScreen(parent);
    }
}
