package dev.comic.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;

public class ComicTitleScreen extends Screen {
    public ComicTitleScreen() { super(Component.literal("Comic Client")); }

    @Override protected void init() {
        Minecraft mc = Minecraft.getInstance();
        int cx = width / 2, w = 200, h = 22, y = height / 2 - 30;

        centered(cx, y - 50, Component.literal("§b§lCOMIC CLIENT"));
        centered(cx, y - 32, Component.literal("§7Minecraft 26.3"));

        addRenderableWidget(Button.builder(Component.literal("Singleplayer"),
                b -> mc.gui.setScreen(new SelectWorldScreen(this))).bounds(cx - w / 2, y, w, h).build());
        addRenderableWidget(Button.builder(Component.literal("Multiplayer"),
                b -> mc.gui.setScreen(new JoinMultiplayerScreen(this))).bounds(cx - w / 2, y + 26, w, h).build());
        addRenderableWidget(Button.builder(Component.literal("§bModules"),
                b -> mc.gui.setScreen(new ModulesScreen(this))).bounds(cx - w / 2, y + 52, w, h).build());
        addRenderableWidget(Button.builder(Component.literal("Options"),
                b -> mc.gui.setScreen(new OptionsScreen(this, mc.options))).bounds(cx - w / 2, y + 78, w / 2 - 2, h).build());
        addRenderableWidget(Button.builder(Component.literal("Quit"),
                b -> mc.stop()).bounds(cx + 2, y + 78, w / 2 - 2, h).build());
    }

    private void centered(int cx, int y, Component text) {
        int tw = font.width(text);
        addRenderableWidget(new StringWidget(cx - tw / 2, y, tw, 12, text, font));
    }

    @Override public boolean shouldCloseOnEsc() { return false; }
}
