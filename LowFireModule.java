package dev.comic.module.impl;

import dev.comic.module.Module;

/** Flag only; the actual work is in mixin/ScreenEffectRendererMixin. */
public class LowFireModule extends Module {
    public static boolean active;
    public LowFireModule() { super("Low Fire", "Hides the fire overlay blocking your view", true); active = true; }
    @Override public void toggle() { super.toggle(); active = enabled; }
}
