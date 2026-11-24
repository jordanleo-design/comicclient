package dev.comic.module.impl;

import dev.comic.module.Module;

/** Flag only; see ComicClient's AttackEntityCallback. WARNING: many servers' anti-cheat flag this. */
public class CrystalOptimizerModule extends Module {
    public static boolean active;
    public CrystalOptimizerModule() { super("Crystal Optimizer", "Removes end crystals client-side the instant you hit them (can desync / be banned on some servers)", false); }
    @Override public void toggle() { super.toggle(); active = enabled; }
}
