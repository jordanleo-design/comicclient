package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import java.util.List;

public class ToggleSprintModule extends Module {
    public ToggleSprintModule() { super("Toggle Sprint", "Always sprint while moving forward", true); }

    @Override public void onTick(Minecraft mc) {
        LocalPlayer p = mc.player;
        if (p == null || mc.gui.screen() != null) return;
        if (mc.options.keyUp.isDown() && !p.isUsingItem() && !p.horizontalCollision && !p.isCrouching()
                && p.getFoodData().getFoodLevel() > 6) {
            p.setSprinting(true);
        }
    }
    @Override public List<HudLine> hudLines(Minecraft mc) { return List.of(new HudLine("[Sprinting (Toggled)]", 0xFFAAAAAA)); }
}
