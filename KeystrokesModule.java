package dev.comic.module.impl;

import dev.comic.module.Module;
import net.minecraft.client.Minecraft;
import java.util.List;

public class KeystrokesModule extends Module {
    public KeystrokesModule() { super("Keystrokes", "Shows WASD / jump / sneak state", false); }
    private static HudLine k(String n, boolean down) { return new HudLine(n, down ? 0xFF55FF55 : 0xFF888888); }
    @Override public List<HudLine> hudLines(Minecraft mc) {
        var o = mc.options;
        return List.of(
            k("[W] Forward", o.keyUp.isDown()),
            k("[A] Left", o.keyLeft.isDown()),
            k("[S] Back", o.keyDown.isDown()),
            k("[D] Right", o.keyRight.isDown()),
            k("[_] Jump", o.keyJump.isDown()),
            k("[Shift] Sneak", o.keyShift.isDown()));
    }
}
