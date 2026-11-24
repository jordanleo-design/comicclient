package dev.comic.hud;

import dev.comic.ComicClient;
import dev.comic.module.Module;
import dev.comic.module.impl.MinimapModule;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

public class HudRenderer {
    public static void register() {
        HudElementRegistry.attachElementAfter(VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(ComicClient.ID, "hud"), HudRenderer::render);
    }

    private static void render(GuiGraphicsExtractor g, net.minecraft.client.DeltaTracker delta) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.gui.hud.isHidden() || mc.player == null) return;
        int x = 6, y = 6;
        for (Module m : ComicClient.modules.all()) {
            if (!m.enabled) continue;
            for (Module.HudLine line : m.hudLines(mc)) {
                int w = mc.font.width(line.text());
                Draw.rect(g, x - 2, y - 2, x + w + 2, y + 10, 0x88000000);
                Draw.text(g, mc.font, line.text(), x, y, line.color());
                y += 13;
            }
        }
        MinimapModule map = (MinimapModule) ComicClient.modules.get("Minimap");
        if (map.enabled) drawMinimap(g, mc, map);
    }

    private static void drawMinimap(GuiGraphicsExtractor g, Minecraft mc, MinimapModule map) {
        int c = MinimapModule.CELL_PX, n = MinimapModule.CELLS, size = n * c;
        int ox = g.guiWidth() - size - 8, oy = 8;
        Draw.rect(g, ox - 2, oy - 2, ox + size + 2, oy + size + 2, 0xFF000000);
        for (int cz = 0; cz < n; cz++)
            for (int cx = 0; cx < n; cx++)
                Draw.rect(g, ox + cx * c, oy + cz * c, ox + (cx + 1) * c, oy + (cz + 1) * c, map.colors[cz * n + cx]);
        int mid = MinimapModule.RADIUS * c;
        Draw.rect(g, ox + mid - 1, oy + mid - 1, ox + mid + c + 1, oy + mid + c + 1, 0xFFFFFFFF);
        Draw.text(g, mc.font, "N", ox + size / 2 - 2, oy + 1, 0xFFFF5555);
    }
}
